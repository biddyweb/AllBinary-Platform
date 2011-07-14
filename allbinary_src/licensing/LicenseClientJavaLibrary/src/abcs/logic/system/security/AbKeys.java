/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package abcs.logic.system.security;


import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.security.licensing.AbeLicenseInterfaceFactory;
import abcs.logic.system.security.licensing.LicensingException;

public class AbKeys
{
	private static final AbKeys instance = new AbKeys();
   private AbKeys()
   {
   }

   public synchronized static String getKey(String keyName) throws LicensingException
   {
      try
      {
         //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LOADER))
         //{
            LogUtil.put(LogFactory.getInstance("Getting Key: " + keyName, instance, "getKey"));
         //}

         //TWB - Replace with key from server
         if(keyName.compareTo("DirectX 8") == 0 ||
             keyName.compareTo("DirectX") == 0 ||
             keyName.compareTo("Low Level") == 0 ||
             keyName.compareTo("System Drivers") == 0)
         {
             return "Temp For Input Library";
         }
         
         return AbeLicenseInterfaceFactory.getInstance().getLicenseInstance().getKey(keyName);
      }
      catch (LicensingException e)
      {
         throw e;
      }
      catch (Exception e)
      {
         //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSINGERROR))
         //{
            LogUtil.put(LogFactory.getInstance("Licensing Failure", instance, "getKey()", e));
         //}
         throw new LicensingException("Unknown License Failure");
      }
   }
}
