<%
/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/29/02
 *
 *
 *Modified By         When       ?
 *
 */
%>
<%
 
   /*
   //ObjectConfigs
   final String ABOUTTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "about/aboutObjectConfig.xml";
   final String CONTACTTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "contact/contactObjectConfig.xml";
   final String SUPPORTTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "support/supportObjectConfig.xml";
   final String HELPTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "help/helpObjectConfig.xml";
   final String LINKSTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "links/linksObjectConfig.xml";
   final String AUCTIONSTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "auctions/auctionsObjectConfig.xml";
   final String NEWSTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "news/newsObjectConfig.xml";
   final String SERVICESTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "services/servicesObjectConfig.xml";
   final String PORTFOLIOTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "portfolio/portfolioObjectConfig.xml";
   
   final String BASKETTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "basket/basketObjectConfig.xml";
   final String EMPTYBASKETTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "basket/emptyBasketObjectConfig.xml";
   final String SEARCHTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "search/searchObjectConfig.xml";
   
   final String PRODUCTSTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "products/productsObjectConfig.xml";
   final String SUMMARYTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "products/summaryObjectConfig.xml";
   
   final String REVIEWTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "customer/review/reviewObjectConfig.xml";
   
   final String CUSTOMERPROFILETEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "customer/proCUSTOMIZEROBJECTCONFIGFILE/proCUSTOMIZEROBJECTCONFIGFILEObjectConfig.xml";
   
   final String REGISTERTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "register/registerObjectConfig.xml";
   final String LOGINTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "login/loginObjectConfig.xml";
   final String LOGOUTTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "logout/logoutObjectConfig.xml";

   final String QUOTEREQUESTTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "quote/requestObjectConfig.xml";
   final String QUOTEREQUESTCOMPLETETEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "quote/loginObjectConfig.xml";

   final String ERRORTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "error/errorObjectConfig.xml";
   final String JSPERRORTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "error/jsperrorObjectConfig.xml";
   final String LICENSINGERRORTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "error/licensingerrorObjectConfig.xml";
   
   final String MAKEPAYMENTTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "payment/makePaymentObjectConfig.xml";
   final String FINISHTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "payment/finishObjectConfig.xml";
   
   final String PAYMENTOPTIONSTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + "options/optionsObjectConfig.xml";   
   
   final String PAYPALCHECKOUTTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + 
      PAYPALDIR + "checkoutObjectConfig.xml";
   final String PAYPALSHIPPINGTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + 
      PAYPALDIR + "shipping/shippingObjectConfig.xml";
   final String PAYPALSHIPPINGADDRESSTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + 
      PAYPALDIR + "address/shipping/shippingAddressObjectConfig.xml";
   final String PAYPALSHIPPINGADDRESSACTIONTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + 
      PAYPALDIR + "address/shipping/shippingAddressActionObjectConfig.xml";
   
   final String VERISIGNPAYFLOWPROAUTHORIZETEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + 
      VERISIGNPAYFLOWPRODIR + "authorizeObjectConfig.xml";
   final String VERISIGNPAYFLOWPROBILLINGADDRESSTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + 
      VERISIGNPAYFLOWPRODIR + "address/billing/billingAddressObjectConfig.xml";
   final String VERISIGNPAYFLOWPROCHECKOUTTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + 
      VERISIGNPAYFLOWPRODIR + "checkoutObjectConfig.xml";
   final String VERISIGNPAYFLOWPROPAYMENTTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + 
      VERISIGNPAYFLOWPRODIR + "payment/paymentObjectConfig.xml";
   final String VERISIGNPAYFLOWPROSHIPPINGTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR +
      VERISIGNPAYFLOWPRODIR + "shipping/shippingObjectConfig.xml";
   final String VERISIGNPAYFLOWPROSHIPPINGADDRESSTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + 
      VERISIGNPAYFLOWPRODIR + "address/shipping/shippingAddressObjectConfig.xml";
   final String VERISIGNPAYFLOWPROSHIPPINGADDRESSACTIONTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + 
      VERISIGNPAYFLOWPRODIR + "address/shipping/shippingAddressActionObjectConfig.xml";
   
   final String INDEXTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "index/indexObjectConfig.xml";
   
   final String CUSTOMBODYTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + CUSTOMBODYDIR + "custom/customBodyObjectConfig.xml";

   //Custom Object Configs 
   HashMap viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap = new HashMap();
   
/*   

   //Insert normal compound pages
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(ABOUTPAGE,ABOUTTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(CONTACTPAGE,CONTACTTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(SUPPORTPAGE,SUPPORTTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(HELPPAGE,HELPTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(LINKSPAGE,LINKSTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(AUCTIONSPAGE,AUCTIONSTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(NEWSPAGE,NEWSTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(SERVICESPAGE,SERVICESTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(PORTFOLIOPAGE,PORTFOLIOTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(BASKETPAGE,BASKETTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(EMPTYBASKETPAGE,EMPTYBASKETTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(SEARCHPAGE,SEARCHTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(PRODUCTSPAGE,PRODUCTSTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(SUMMARYPAGE,SUMMARYTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(REVIEWPAGE,REVIEWTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(CUSTOMERPROFILEPAGE,CUSTOMERPROFILETEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(REGISTERPAGE,REGISTERTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(LOGINPAGE,LOGINTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(LOGOUTPAGENAME,LOGOUTTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(QUOTEREQUESTPAGE,QUOTEREQUESTTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(QUOTEREQUESTCOMPELETEPAGE,QUOTEREQUESTCOMPELETETEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(ERRORPAGE,ERRORTEMPLATECUSTOMIZEROBJECTCONFIGFILE);   
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(JSPERRORPAGE,JSPERRORTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(LICENSINGERRORPAGE,LICENSINGERRORTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(PaymentGatewayPageData.MAKEPAYMENT,MAKEPAYMENTTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(PaymentGatewayPageData.FINISH,FINISHTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   
   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.put(PaymentGatewayPageData.PAYMENTOPTIONS,PAYMENTOPTIONSTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   */
%>
