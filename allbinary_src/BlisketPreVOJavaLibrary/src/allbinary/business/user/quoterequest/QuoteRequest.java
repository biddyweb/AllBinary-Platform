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
package allbinary.business.user.quoterequest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import abcs.logic.basic.io.file.generators.QuoteRequestIdGenerator;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.entry.EntryData;
import allbinary.business.quoterequest.QuoteRequestData;
import allbinary.business.user.UserData;
import allbinary.logic.communication.sql.AbSqlData;

public class QuoteRequest
{
   private String id;
   
   private String userName;
   private String projectInfo;
   private String userComments;
   private String budget;
   private String timeFrame;
   private String comments;
   
   public QuoteRequest(String userName, HttpServletRequest request)
       throws Exception
   {
	   QuoteRequestData quoteRequestData = QuoteRequestData.getInstance();
	   
      this.id = new QuoteRequestIdGenerator().getNext();
      //this.id = (String) hashMap.get(QuoteRequestData.ID);
      //this.setUserName((String) request.getParameter(UserData.USERNAME));
      this.setUserName(userName);
      this.setProjectInfo((String) request.getParameter(quoteRequestData.PROJECT_INFO));
      this.setUserComments((String) request.getParameter(quoteRequestData.CUSTOMER_COMMENTS));
      this.setBudget((String) request.getParameter(quoteRequestData.BUDGET));
      this.setTimeFrame((String) request.getParameter(quoteRequestData.TIMEFRAME));
      this.setComments((String) request.getParameter(quoteRequestData.COMMENTS));

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
      {
         LogUtil.put(LogFactory.getInstance("Created with: " + this.toHashMap(), this, "Constructor()"));
      }
   }   
   
   public QuoteRequest(HashMap hashMap) throws Exception
   {
	   QuoteRequestData quoteRequestData = QuoteRequestData.getInstance();
	   
      this.id = (String) hashMap.get(quoteRequestData.ID);
      this.setUserName((String) hashMap.get(UserData.USERNAME));
      this.setProjectInfo((String) hashMap.get(quoteRequestData.PROJECT_INFO));
      this.setUserComments((String) hashMap.get(quoteRequestData.CUSTOMER_COMMENTS));
      this.setBudget((String) hashMap.get(quoteRequestData.BUDGET));
      this.setTimeFrame((String) hashMap.get(quoteRequestData.TIMEFRAME));
      this.setComments((String) hashMap.get(quoteRequestData.COMMENTS));

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPER))
      {
         LogUtil.put(LogFactory.getInstance("Created with: " + this.toHashMap(), this, "Constructor()"));
      }
   }   
  
   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;
         if(userName==null || userName.length()<4 || userName.length()>250)
         {
            valid = Boolean.FALSE;
         }

         if(this.projectInfo==null || this.projectInfo.length()<0 || this.projectInfo.length()>AbSqlData.MAXBLOB )
         {
            valid = Boolean.FALSE;
         }

         if(this.userComments==null || this.userComments.length()<0 || this.userComments.length()>AbSqlData.MAXBLOB )
         {
            valid = Boolean.FALSE;
         }

         if(this.budget==null || this.budget.length()<0 || this.budget.length()>AbSqlData.MAXBLOB )
         {
            valid = Boolean.FALSE;
         }

         if(this.timeFrame==null || this.timeFrame.length()<0 || this.timeFrame.length()>AbSqlData.MAXBLOB )
         {
            valid = Boolean.FALSE;
         }

         if(this.comments==null || this.comments.length()<0 || this.comments.length()>AbSqlData.MAXBLOB )
         {
            valid = Boolean.FALSE;
         }

         return valid;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form",this,"isValid()",e));
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         if(userName==null || userName.length()<4 || userName.length()>250)
         {
            stringBuffer.append("Please enter a User Name with more than 4 characters.<br>");
         }
                  
         if(this.projectInfo==null || this.projectInfo.length()<0 || this.projectInfo.length()>AbSqlData.MAXBLOB )
         {
            stringBuffer.append("Please enter Project Info with less than " + AbSqlData.MAXBLOB  + " characters.<br>");
         }

         if(this.userComments==null || this.userComments.length()<0 || this.userComments.length()>AbSqlData.MAXBLOB )
         {
            stringBuffer.append("Please enter User Comments with less than " + AbSqlData.MAXBLOB  + " characters.<br>");
         }

         if(this.budget==null || this.budget.length()<0 || this.budget.length()>AbSqlData.MAXBLOB )
         {
            stringBuffer.append("Please enter Budget with less than " + AbSqlData.MAXBLOB  + " characters.<br>");
         }

         if(this.timeFrame==null || this.timeFrame.length()<0 || this.timeFrame.length()>AbSqlData.MAXBLOB )
         {
            stringBuffer.append("Please enter time frame with less than " + AbSqlData.MAXBLOB  + " characters.<br>");                        
         }

         if(this.comments==null || this.comments.length()<0 || this.comments.length()>AbSqlData.MAXBLOB )
         {
            stringBuffer.append("Please enter comments with less than " + AbSqlData.MAXBLOB  + " characters.<br>");
         }
                  
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info",this,"validationInfo()",e));
         }
         return "Error Validating Form";
      }
   }

   public Vector toVector()
   {
      Vector values = new Vector();
      //unique id is added in entity      
      values.add(id);
      values.add(userName);      
      values.add(this.projectInfo);
      values.add(this.userComments);
      values.add(this.budget);
      values.add(this.timeFrame);
      values.add(this.comments);
      
      Calendar calendar=Calendar.getInstance();
      String time = new String(new Long(calendar.getTimeInMillis()).toString());
      values.add(time);
      values.add(time);
      
      return values;
   }
   
   public HashMap toHashMap()
   {
	   QuoteRequestData quoteRequestData = QuoteRequestData.getInstance();
	   
      HashMap values = new HashMap();
      values.put(UserData.USERNAME,userName);
      values.put(quoteRequestData.PROJECT_INFO,this.projectInfo);
      values.put(quoteRequestData.CUSTOMER_COMMENTS,this.userComments);
      values.put(quoteRequestData.BUDGET,this.budget);
      values.put(quoteRequestData.TIMEFRAME,this.timeFrame);
      values.put(quoteRequestData.COMMENTS,this.comments);
      
      Calendar calendar=Calendar.getInstance();
      String time = new String(new Long(calendar.getTimeInMillis()).toString());
      values.put(EntryData.getInstance().LASTMODIFIED,time);
      
      return values;
   }

   public void setUserName(String value)
   {
      this.userName = value;
   }

   public void setProjectInfo(String value)
   {
      this.projectInfo = value;
   }

   public void setUserComments(String value)
   {
      this.userComments = value;
   }

   public void setBudget(String value)
   {
      this.budget = value;
   }

   public void setTimeFrame(String value)
   {
      this.timeFrame = value;
   }

   public void setComments(String value)
   {
      this.comments = value;
      if(this.comments==null) this.comments="";
   }
   
   public String getUserName()
   {
      return this.userName;
   }

   public String getProjectInfo()
   {
      return this.projectInfo;
   }

   public String getUserComments()
   {
      return this.userComments;
   }

   public String getBudget()
   {
      return this.budget;
   }

   public String getTimeFrame()
   {
      return this.timeFrame;
   }

   public String getComments()
   {
      return this.comments;
   }
   
}
