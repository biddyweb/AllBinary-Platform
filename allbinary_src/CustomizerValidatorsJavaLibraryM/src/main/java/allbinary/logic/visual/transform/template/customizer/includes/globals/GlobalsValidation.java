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
package allbinary.logic.visual.transform.template.customizer.includes.globals;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.modules.storefront.StoreFrontFactory;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.context.modules.storefront.StoreFrontView;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.logic.control.validate.ValidationInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class GlobalsValidation implements ValidationInterface, DomNodeInterface
{   
   private StoreFrontInterface storeFrontInterface;
   
   //new
   public GlobalsValidation(String storeName)
   {
      this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
   }
   
   //from db
   public GlobalsValidation(Document document) throws Exception
   {
      throw new Exception("Not Implemented");
      //this.storeFrontInterface = new StoreFront(document);
   }
   
   //with request data
   /*
   public GlobalsValidation(HashMap hashMap)
   {
      
   }
     */
   
   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;

         /*
         if(!this.isValid().booleanValue() && !this.logo.isValid().booleanValue())
         {
            valid = Boolean.FALSE;
         }
         */

         return valid;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form", this, "isValid()", e));
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();

         /*
         if(!this.isValid().booleanValue() && !this.isValid().booleanValue())
         {
            stringBuffer.append("Include a title or logo for the header.");
         }
         */

         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info", this, "validationInfo()", e));
         }
         return "Error Validating Form";
      }
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
 
   public Node toXmlNode(Document document) throws Exception
   {
      Node node = document.createElement(GlobalsData.NAME);
      node.appendChild(new StoreFrontView(
         this.storeFrontInterface).toXmlNode(document));
      return node;
   }
}