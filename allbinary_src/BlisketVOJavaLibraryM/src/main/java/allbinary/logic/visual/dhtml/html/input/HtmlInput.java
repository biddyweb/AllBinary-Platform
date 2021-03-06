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
package allbinary.logic.visual.dhtml.html.input;

import allbinary.logic.visual.dhtml.html.HtmlTag;

import java.util.Iterator;

public class HtmlInput extends HtmlTag
{
   private final String END = ">";
   private final String STARTINPUT = "<INPUT ";
   private final String ENDINPUT = "</INPUT>";
   private final String TYPE = "TYPE=\"";
   private final String NAME = "NAME=\"";
   
    private String before;
    private String type;
    private String name;
    private String after;
            
    public HtmlInput(String before, String type,String name, String after)
    {
       this.before = before;
       this.type = type;
       this.name = name;
       this.after = after;
    }
        
    public String toString()
    {
       String result = "";
       Iterator attributeIter = otherAttributes.keySet().iterator();
       result = before;
       result += STARTINPUT;
       result += TYPE;
       result += type;
       result += "\" ";
       result += NAME;
       result += name;
       result += "\" ";       
       
       while(attributeIter.hasNext())
       {
          String key = (String) attributeIter.next();
          String value = (String) otherAttributes.get(key);
          if(value!=null && value.compareTo("")!=0)
          {
             result += key;
             result += "=\"";
             result += value;
             result += "\" ";
          }
       }
       result += END;
       result += ENDINPUT;
       result += after;
       return result;
    }
}
