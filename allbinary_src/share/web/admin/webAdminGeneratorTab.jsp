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

<div id="webAdminTabbedPane2" class="tab" >
Generate Product Pages:<p/>

It is best to generate product pages for the following reasons:<p />

Special Customization - Make special edits to product pages<br />
Search Engine Indexing - Allow search engines to index pages<br />
Performance - Reduce request time by not generating pages dynamically<br />
<p />
Use the following steps:
<p />
1.  Usually takes ~2 seconds per product (200 products = ~400 seconds)
<p />
<form method="POST" action="<%= STOREADMINPAGE %>">
<input type="hidden" value="<%= storeName %>" 
   name="<%= StoreFrontData.NAME %>" />

<input type="hidden" name="<%= SearchData.PAGE %>" value="1000" />
<input type="hidden" name="<%= SearchData.FIELD %>" value="<%= BasicItemData.SUMMARY %>" />
<input type="hidden" name="<%= SearchData.SORTBY %>" value="AlphaNumeric" />
<input type="hidden" name="<%= SearchData.LENGTH %>" value="10" />
<input type="hidden" name="<%= SearchData.ORDER %>" value="Ascending" />

<input type="hidden" 
   value="<%= GENERATORSTAB %>" 
   name="<%= TAB %>">

<input class="submit" type="submit" value="Product Pages" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
</form>

<p/>
2.  Make the new product pages public.  Usually takes ~1 seconds per 100 products (200 products = ~2 seconds)
<p/>
<form method="POST" action="<%= WEBADMINPAGE %>">
<input type="hidden" value="<%= storeName %>" 
   name="<%= StoreFrontData.NAME %>" />

<input type="hidden" 
   value="<%= GENERATORSTAB %>" 
   name="<%= TAB %>">

<input class="submit" type="submit" value="Make Public" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />   
</form>
<p/>
Note: Not needed unless you manually change a view file.<p/>

Generate Store Pages:<p/>

<form method="POST" action="<%= WEBADMINPAGE %>">

<input type="hidden" value="<%= GENERATESTORE %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />

<input class="submit" type="submit" value="Generate" />

</form>
<p/>

</div>