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
<%@ taglib
       uri="/WEB-INF/ecommerce.tld"
       prefix="ecommerce"
%>
<HTML>
<HEAD>
<TITLE>Processing - Store Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>

<BODY COLOR=#ffffff>
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%
Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
roles.add(BasicUserRole.STOREMANAGER);
roles.add(BasicUserRole.SHIPPING);
%>
<ecommerce:authentication 
   command="<%= allbinary.ecommerce.globals.GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - Order Processing - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + PAYMENTMANAGERPAGE %>">Help</a></p>
<div class="main">

<%  
final String SETSTATUS = "Set Status";

   if(command!=null && storeName!=null)
   {
         //command.compareTo(VIEWORDERSINRANGE)==0
         //not implemented well command.compareTo(VIEWALLORDERS)==0

      if(command.compareTo(VIEWORDERSINLASTHOUR)==0 ||
         command.compareTo(VIEWORDERSINLASTDAY)==0 ||
         command.compareTo(VIEWORDERSINLASTWEEK)==0 ||
         command.compareTo(VIEWORDERSINLAST30DAYS)==0 ||
         command.compareTo(VIEWORDERSINRANGE)==0 ||
         command.compareTo(VIEWALLORDERS)==0)
      {
%>
<admin:daterangeorderhistory command="<%= GLOBALS.VIEW %>" 
   storeName="<%= storeName %>"
   xsl="<%= ORDERHISTORYXSL %>" />
<%
      }
      else
      if(command.compareTo(VIEWCUSTOMERORDERHISTORY)==0)
      {
%>
<admin:usernameorderhistory command="<%= GLOBALS.VIEW %>" 
   storeName="<%= storeName %>"
   xsl="<%= ORDERHISTORYXSL %>" />
<%
      }   
   else
   if(command.compareTo(OrderHistoryData.SETSTATUS)==0)
   {
%>
<ecommerce:orderitems isSelected="true" command="<%= command %>" />
<p>
<ecommerce:orderhistory isSelected="true" command="<%= command %>" />
<p>
<%
   }
   else
   if(command.compareTo(allbinary.ecommerce.globals.GLOBALS.VIEW)==0)
   {
%>
Change Order Status:<p>
<form method="POST" action="<%= StoreAdminPageData.SHIPPING %>">
<generic:order command="<%= GLOBALS.VIEW %>" 
storeName="<%= storeName %>"
   xsl="<%= ORDERREVIEWXSL %>" />
<p>
Set Order Status:
<select name="<%= OrderHistoryData.STATUS %>" >
<OPTION><%= OrderHistoryData.PROCESSING %></OPTION>
<OPTION><%= OrderHistoryData.SHIPPED %></OPTION>
<OPTION><%= OrderHistoryData.PREPROCESSING %></OPTION>
<OPTION><%= OrderHistoryData.PARTIALLYSHIPPED %></OPTION>
<OPTION><%= OrderHistoryData.CANCELLED %></OPTION>
</select>
<p>
<p>
<input class="submit" type="submit" value="<%= SETSTATUS %>" 
   name="<%= allbinary.ecommerce.globals.GLOBALS.ADMINCOMMAND %>"></p>
</form>
<%
}
}
%>
</form>
<p>

<form method="POST" action="<%= StoreAdminPageData.SHIPPING %>">
View By Order:<p>
Order Id: <input type="text" name="<%= OrderData.ID %>" size="30"><p>
<p><input class="submit" type="submit" value="<%= VIEWORDER %>" name="<%= GLOBALS.ADMINCOMMAND %>" ></p>
</form>

<form method="POST" action="<%= StoreAdminPageData.SHIPPING %>">
Search By Customer:<p>
User Name: <input type="text" name="<%= CustomerData.USERNAME %>" size="30"></p>
<p><input class="submit" type="submit" value="<%= VIEWCUSTOMERORDERHISTORY %>" name="<%= GLOBALS.ADMINCOMMAND %>"></p>
</form>

<form method="POST" action="<%= StoreAdminPageData.SHIPPING %>">
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PREPROCESSINGNAME %>" >Preprocessing<br />
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PROCESSINGNAME %>" >Processing<br />
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.SHIPPEDNAME %>" >Shipped<br />
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PARTIALLYSHIPPEDNAME %>" >Partially<br />
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.CANCELLEDNAME %>" >Cancelled<br />
<input type="hidden" value="<%= OrderHistoryData.TYPELONG %>" name="<%= OrderHistoryData.DATETYPE %>" >
<br />
<input class="submit" type="submit" value="<%= VIEWORDERSINLASTHOUR %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<br />
<input class="submit" type="submit" value="<%= VIEWORDERSINLASTDAY %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<br />
<input class="submit" type="submit" value="<%= VIEWORDERSINLASTWEEK %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<br />
<input class="submit" type="submit" value="<%= VIEWORDERSINLAST30DAYS %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<br />
<input class="submit" type="submit" value="<%= VIEWALLORDERS %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<br />
</form>

<form method="POST" action="<%= StoreAdminPageData.SHIPPING %>">
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PREPROCESSINGNAME %>" >Preprocessing<br>
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PROCESSINGNAME %>" >Processing<br>
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.SHIPPEDNAME %>" >Shipped<br>
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PARTIALLYSHIPPEDNAME %>" >Partially<br>
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.CANCELLEDNAME %>" >Cancelled<br>
<br />

<jsp:include page="dateRangeTable.jsp" />

<input type="hidden" value="<%= OrderHistoryData.TYPECAESAR %>" name="<%= OrderHistoryData.DATETYPE %>" >

<br>
<input class="submit" type="submit" value="<%= VIEWORDERSINRANGE %>" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

</div>
<%@ include file="copyright.jsp" %>
</div>
</BODY>
</HTML>