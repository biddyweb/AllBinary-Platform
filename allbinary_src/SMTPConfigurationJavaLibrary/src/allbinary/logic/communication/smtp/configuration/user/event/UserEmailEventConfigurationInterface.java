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
package allbinary.logic.communication.smtp.configuration.user.event;

public interface UserEmailEventConfigurationInterface
{
   public String getName();

   public void setName(String name);

   public String getEventListenerClassPath();

   public void setEventListenerClassPath(String eventListenerClassPath);
   
   public String log();
}
