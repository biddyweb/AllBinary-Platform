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
package views.admin.payment;




import abcs.logic.communication.log.LogFactory;
import allbinary.data.tree.dom.DomNodeInterface;

import allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;

import abcs.logic.communication.log.LogUtil;

import allbinary.business.user.commerce.money.payment.gateway.processor.PaymentProcessorInterface;
import allbinary.business.user.commerce.money.payment.gateway.processor.PaymentProcessorInterfaceFactory;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class PaymentProcessorComponent 
   extends HttpStoreComponentView
{
   public PaymentProcessorComponent(TransformInfoInterface transformInfoInterface) 
      throws Exception
   {
      super(transformInfoInterface);
   }

   public void addDomNodeInterfaces() throws Exception
   {
      PaymentProcessorInterface paymentProcessorInterface =
         PaymentProcessorInterfaceFactory.getInstance().getInstance(
            this.getTransformInfoInterface());
      
      paymentProcessorInterface.process();
      //If Processing works the empty basket
      
      TransformInfoHttpInterface httpTransformInfoInterface = 
         (TransformInfoHttpInterface) this.getTransformInfoInterface();

      httpTransformInfoInterface.getWeblisketSession().removeBasket();

      this.addDomNodeInterface((DomNodeInterface) paymentProcessorInterface);
   }
   
   public String view() throws Exception
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         String error = "Failed to process payment";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "view()", e));
         }
         throw e;
      }
   }
}
