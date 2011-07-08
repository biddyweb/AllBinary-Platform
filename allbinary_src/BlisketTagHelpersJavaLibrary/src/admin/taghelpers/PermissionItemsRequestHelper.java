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

import abcs.logic.communication.log.LogFactory;
import java.util.*;

import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletRequest;

import abcs.logic.communication.log.LogUtil;

import allbinary.business.entry.EntryData;

import allbinary.business.user.commerce.inventory.item.BasicItemData;
import allbinary.business.user.commerce.inventory.item.permission.PermissionItemData;

import allbinary.data.tables.user.commerce.inventory.item.permissions.PermissionItemsEntityFactory;

public class PermissionItemsRequestHelper implements ModifyTableInterface
{

   private HttpServletRequest request;
     
   private String id;
   private String enabled;
   private String number;   
   private String who;
   private String what;
   private String type;
   private String remoteAddr;      
   
   private String startTime;
   private String endTime;
   
   private String price;
   
   private String timeEntered;
   private String lastModified;
   
   public PermissionItemsRequestHelper(HashMap hashMap, PageContext pageContext)
   {
      this.request = (HttpServletRequest) pageContext.getRequest();
      this.getFormData();
   }
   
   public void getFormData()
   {
      this.id = request.getParameter(BasicItemData.ID);
      this.number = request.getParameter(BasicItemData.NUMBER);
      this.enabled = request.getParameter(EntryData.getInstance().ENABLE);
      this.who = request.getParameter(PermissionItemData.WHO);
      this.what = request.getParameter(PermissionItemData.WHAT);
      this.type = request.getParameter(PermissionItemData.TYPE);
      this.remoteAddr = request.getParameter(PermissionItemData.REMOTE_ADDR);
      this.startTime = request.getParameter(PermissionItemData.START_TIME);
      this.endTime = request.getParameter(PermissionItemData.END_TIME);
      this.price = request.getParameter(BasicItemData.PRICE);         
      this.timeEntered = request.getParameter(EntryData.getInstance().TIMECREATED);
      this.lastModified = request.getParameter(EntryData.getInstance().LASTMODIFIED);
   }

   private HashMap getHashMap()
   {
      HashMap values = new HashMap();
      
      values.put(BasicItemData.ID,this.id);
      values.put(BasicItemData.NUMBER,this.number);
      values.put(EntryData.getInstance().ENABLE,this.enabled);
      values.put(PermissionItemData.WHO,this.who);
      values.put(PermissionItemData.WHAT,this.what);
      values.put(PermissionItemData.TYPE,this.type);
      values.put(PermissionItemData.REMOTE_ADDR,this.remoteAddr);
      values.put(PermissionItemData.START_TIME,this.startTime);
      values.put(PermissionItemData.END_TIME,this.endTime);
      values.put(BasicItemData.PRICE,this.price);
      
      Calendar calendar=Calendar.getInstance();
      String time = new String(new Long(calendar.getTimeInMillis()).toString());
      
      values.put(EntryData.getInstance().LASTMODIFIED,time);
      
      return values;
   }

   public String insert()
   {
      try
      {
         Calendar calendar=Calendar.getInstance();
         String time = new String(new Long(calendar.getTimeInMillis()).toString());
         Vector values = new Vector();
                  
         values.add(this.id);
         values.add(this.number);
         values.add(this.enabled);
         values.add(this.who);
         values.add(this.what);
         values.add(this.type);
         values.add(this.remoteAddr);
         values.add(this.startTime);
         values.add(this.endTime);
         values.add(this.price);
         
         values.add(time);
         values.add(time);
         
         PermissionItemsEntityFactory.getInstance().getPermissionItemsEntityInstance().insert(values);
         
         String success = "Successfully inserted " + id + " into items table";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"insert()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to insert " + id + " into items table";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"inserts()",e));
         }
         return error;
      }
   }    
   
   public String delete()
   {
      try
      {
         PermissionItemsEntityFactory.getInstance().getPermissionItemsEntityInstance().delete(id);
         
         String success = "Successfully deleted";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"delete()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to delete";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"delete()",e));
         }
         return error;
      }
   }
     
   public String update()
   {
      try
      {
         String success = "Update Pricing Successful";
         
         HashMap values = this.getHashMap();
         PermissionItemsEntityFactory.getInstance().getPermissionItemsEntityInstance().update(values);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(id + " " + success,this,"update()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update: " + id;
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"update()",e));
         }
         return error;
      }
   }

}
