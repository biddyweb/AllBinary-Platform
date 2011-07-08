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
package admin.tags;

import java.lang.reflect.Method;

import abcs.logic.system.security.licensing.LicensingException;

import abcs.logic.communication.log.LogUtil;

import admin.taghelpers.PaymentGatewayHelperFactory;

import abcs.logic.communication.http.request.AbResponseHandler;
import abcs.logic.communication.log.LogFactory;
import javax.servlet.jsp.JspTagException;

public class PaymentGatewayTag extends TableTag
{
   public PaymentGatewayTag()
   {
      this.setTagHelperFactory(new PaymentGatewayHelperFactory());
      this.setTagRequestHelperFactory(new PaymentGatewayHelperFactory());
   }
   
   public String process() throws LicensingException, Exception
   {
      try
      {
         Object object = new PaymentGatewayHelperFactory().getInstance(
         this.getPropertiesHashMap(), this.pageContext);
         
         Class helperClass = object.getClass();
         
         Method method = helperClass.getMethod("process",null);
         
         String result = (String) method.invoke(object,null);
         
         return result;
      }
      catch(LicensingException e)
      {
         LogUtil.put(LogFactory.getInstance("LicensingException",this,"process()",e));
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to process a gateway to a store";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"process()",e));
         }
         throw e;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.isEnabled())
         {
            if(this.getCommand().compareTo(allbinary.globals.GLOBALS.AUTHORIZEORDEREVALBODYONERROR)==0 ||
            this.getCommand().compareTo(allbinary.globals.GLOBALS.AUTHORIZEORDERANDEVALBODY)==0 ||
            this.getCommand().compareTo(allbinary.globals.GLOBALS.AUTHORIZEFORMEVALBODYONERROR)==0 )
               //AUTHORIZEFORMEVALBODYONERROR is used for form payment processing instead of order in session
            {
               String output = this.process();
               //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAGEXTRAOUTPUT))
               {
                  this.pageContext.getOut().print(output + "<br />");
               }
               
               if(this.getCommand().compareTo(allbinary.globals.GLOBALS.AUTHORIZEORDEREVALBODYONERROR)==0)
               {
                  return this.SKIP_BODY;
               }
               else 
               {
                  return this.EVAL_BODY_INCLUDE;
               }
            }
            else
            {
               return super.doStartTag();
            }
         }
         return SKIP_BODY;
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
}
