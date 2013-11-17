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
package views.admin.user;


import abcs.logic.communication.log.LogFactory;
import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

   
import abcs.logic.communication.log.LogUtil;


import allbinary.business.user.UsersData;
import allbinary.business.user.UserDomNode;
import allbinary.business.user.modules.User;

import allbinary.data.tree.dom.DomNodeInterface;



import allbinary.logic.visual.transform.info.TransformInfoInterface;

import views.business.context.HttpContextView;

public class UsersView extends HttpContextView implements DomNodeInterface
{
   protected Vector userVector;
   
   public UsersView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
      
   public Node toXmlNode(Document document)
   {
      try
      {
         Node usersNode = document.createElement(UsersData.NAME);                  
         
         Iterator iter = userVector.iterator();
         
         while(iter.hasNext())
         {
            //String product = new String((String) iter.next());
            //InventoryEntityFactory.getInstance().getItem(product);
            User userInterface = (User) iter.next();
            if(userInterface!=null)
            {
               Node node = new UserDomNode(userInterface).toXmlNode(document);
               usersNode.appendChild(node);
            }
            else
            {
               LogUtil.put(LogFactory.getInstance("UserHelper",this,"toXmlNode"));
            }
         }
         
         return usersNode;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.XSLLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "toXmlNode", e));
         }
         return null;
      }
   }

   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) this);
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
         String error = "Failed to view Mini Basket";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }   
}