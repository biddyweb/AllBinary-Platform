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
package admin.taghelpers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletRequest;

import abcs.business.user.role.BasicUserRole;
import abcs.logic.communication.log.LogFactory;

import allbinary.business.user.UserInterface;
import allbinary.business.user.UserData;
import allbinary.logic.communication.smtp.email.NewPasswordEmail;
import allbinary.business.user.password.Password;


import allbinary.data.tables.user.UserEntityFactory;
import allbinary.data.tables.user.UserEntityInterface;


import allbinary.logic.communication.http.request.session.WeblisketSession;
import allbinary.logic.communication.http.request.session.WeblisketSessionData;

import abcs.logic.communication.log.LogUtil;
import allbinary.logic.communication.http.request.session.WeblisketSessionInterface;

public class AuthenticationRequestHelper
    implements TagHelperInterface
{
   private WeblisketSession weblisketSession;
   
   private HttpServletRequest request;
   
   public AuthenticationRequestHelper(HashMap hashMap, PageContext pageContext)
   {
      this.weblisketSession = new WeblisketSession(hashMap, pageContext);

      this.request = (HttpServletRequest) pageContext.getRequest();
   }

   public AuthenticationRequestHelper(HashMap hashMap, HttpServletRequest httpServletRequest)
   {
      this.weblisketSession = new WeblisketSession(hashMap, httpServletRequest);

      this.request = httpServletRequest;
   }
   
   private String generateNewPassword() throws Exception
   {
      //Remove this method after complete rebuild
      if(this.weblisketSession != null && 
         this.weblisketSession.getId() != null)
      {
         int startIndex = this.weblisketSession.getId().length();
         if(startIndex >= 8)
         {
            return this.weblisketSession.getId().substring(startIndex - 8);
         }
         else
         {
            throw new Exception("Error Generating New Password");
         }
      }
      else
      {
         throw new Exception("No Session Available For Generating New Password");
      }
   }
   
   public Boolean newPassword()
   {
      try
      {
         String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
         String email = request.getParameter(UserData.MAINEMAIL);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
         {
            LogUtil.put(LogFactory.getInstance("Generating New Password For: " + userName, this, "newPassword()"));
         }
         
         /*
         //Add back in if adding restriction based on role
         UserRole role = this.weblisketSession.getRole();
         if(role == null)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
            {
               LogUtil.put(LogFactory.getInstance("Trying role from request for: " + userName, this, "newPassword()"));
            }
            String roleString = request.getParameter(UserRoleData.NAME);
            if(role != null)
            {
               role = UserRole.getRole(roleString);
            }
         }
          */
         
         UserEntityInterface userEntityInterface = UserEntityFactory.getInstance();
         UserInterface userInterface = userEntityInterface.getUser(userName);
         
         if(userInterface.getMainEmail().compareTo(email) != 0)
         {
            if(userInterface.getSecondaryEmail() != null &&
               userInterface.getSecondaryEmail().compareTo(email) != 0)
            {
               if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
               {
                  LogUtil.put(LogFactory.getInstance("Requested Email Is Not In User Profile", this, "newPassword()"));
               }
               return Boolean.FALSE;
            }
         }
         
         String newPassword = this.generateNewPassword();
         userInterface.setPassword(newPassword);
         
         //update db with new password
         HashMap newPasswordHashMap = userInterface.toPasswordHashMap();
         UserEntityFactory.getInstance().update(userName, newPasswordHashMap);
         
         //Send Communication with new password
         new NewPasswordEmail(userInterface, newPassword).process();
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
         {
            LogUtil.put(LogFactory.getInstance("Generated New Password For: " + userName, this, "newPassword()"));
         }
         
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed", this, "newPassword()",e));
         }
         
         return Boolean.FALSE;
      }
   }
   
   public Boolean changePassword()
   {
      try
      {
         String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
         String passwordString = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);
         String newPassword = request.getParameter(WeblisketSessionData.REMOVABLENEWPASSWORD);
         String newReenteredPassword = request.getParameter(WeblisketSessionData.REMOVABLEREENTERNEWPASSWORD);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
         {
            LogUtil.put(LogFactory.getInstance("Changing Password For User: " + userName, this, "changePassword()"));
         }
         
         /*
         //Add back in if adding restriction based on role
         UserRole role = this.weblisketSession.getRole();
         if(role == null)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
            {
               LogUtil.put(LogFactory.getInstance("Trying role from request for: " + userName, this, "newPassword()"));
            }
            String roleString = request.getParameter(UserRoleData.NAME);
            if(role != null)
            {
               role = UserRole.getRole(roleString);
            }
         }
          */
         
         Password password = new Password(newPassword);
         if(!password.isValid().booleanValue())
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
            {
               LogUtil.put(LogFactory.getInstance("New Password Is Not Valid", this, "changePassword()"));
            }
            
            return Boolean.FALSE;
         }
         
         if(newPassword.compareTo(newReenteredPassword) != 0)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
            {
               LogUtil.put(LogFactory.getInstance("New Password Fields Do Not Match", this, "changePassword()"));
            }
            
            return Boolean.FALSE;
         }
         
         String sessionPassword = this.weblisketSession.getPassword();
         
         UserEntityInterface userEntityInterface = UserEntityFactory.getInstance();
         UserInterface userInterface = userEntityInterface.getUser(userName);
         String login = userEntityInterface.login(userName, passwordString);
         
         if(login.compareTo(allbinary.globals.GLOBALS.LOGINSUCCESS)==0)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
            {
               LogUtil.put(LogFactory.getInstance("Authentication Successful", this, "changePassword()"));
            }
            
            HashMap newPasswordHashMap = password.toHashMap(null);
            UserEntityFactory.getInstance().update(userName, newPasswordHashMap);
            
            //Send Communication with new password
            new NewPasswordEmail(userInterface, newPassword).process();
            
            return Boolean.TRUE;
         }
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
         {
            LogUtil.put(LogFactory.getInstance("Authentication Failed", this, "changePassword()"));
         }
         
         return Boolean.FALSE;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance("Authentication Failed", this, "changePassword()",e));
         }
         return Boolean.FALSE;
      }
   }
   
   //Add role to session if it is a valid role
   public Boolean isRoleValid(String userName,String password, Vector roles)
   {
      try
      {
         UserEntityInterface userEntityInterface = UserEntityFactory.getInstance();
         UserInterface userInterface = userEntityInterface.getUser(userName);
         
         if(userInterface.getRole() == null)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
            {
               LogUtil.put(LogFactory.getInstance("Role is null: " + userInterface.getRole() +
                  " Valid Roles: " + roles.toString(),
                  this, "isRoleValid()"));
            }
            
            return Boolean.FALSE;
         }
         
         Iterator iter = roles.iterator();
         while(iter.hasNext())
         {
            BasicUserRole nextRole = (BasicUserRole) iter.next();
            if(userInterface.getRole().getBasicUserRole().equals(nextRole))
            {
               //role is verified
               
               //validate permissions to set session
               //this should be seperated into hasPermissions method
               
               /*if(userInterface.hasPermission(new RequestParams(this.request)))
               {
                  userInterface.validateSession(weblisketSession,new RequestParams(this.request));
               }
               else
               {
                  if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
                  {
                     LogUtil.put(LogFactory.getInstance("Role is not valid: " + userInterface.getRole() +
                     " Valid Roles: " + roles.toString(),
                     this,"isRoleValid()"));
                  }
                
                  return Boolean.FALSE;
               }
                */
               
               userInterface.validateSession((WeblisketSessionInterface) weblisketSession);
               
               this.request.removeAttribute(WeblisketSessionData.REMOVABLEUSERNAME);
               this.request.removeAttribute(WeblisketSessionData.REMOVABLEPASSWORD);
               return Boolean.TRUE;
            }
         }
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
         {
        	 StringBuffer stringBuffer = new StringBuffer();
        	 
        	 stringBuffer.append("Role is not valid: ");
        	 stringBuffer.append(userInterface.getRole());
        	 stringBuffer.append(" Valid Roles: ");
        	 stringBuffer.append(roles.toString());
        	 
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "isRoleValid()"));
         }
         //on userInterface.getRole() failure
         return Boolean.FALSE;
      }
      catch(Exception e)
      {
         String error = "Failed to check if Role is valid";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "isRoleValid()", e));
         }
         return Boolean.FALSE;
      }
   }
}
