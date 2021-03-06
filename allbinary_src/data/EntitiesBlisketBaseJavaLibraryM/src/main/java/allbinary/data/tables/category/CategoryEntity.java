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
package allbinary.data.tables.category;

import java.util.HashMap;
import java.util.Vector;

import abcs.business.init.db.InventoryDbInitInfo;
import abcs.business.installer.Portion;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.category.Category;
import allbinary.business.category.CategoryData;
import allbinary.business.category.CategoryFactoryInterface;
import allbinary.logic.communication.sql.AbSqlBean;

public class CategoryEntity extends AbSqlBean implements CategoryEntityInterface
{
   protected final String tableName = "categories";
   
   //private CategoryFactoryInterface categoryFactoryInterface;
   
   public CategoryEntity(CategoryFactoryInterface categoryFactoryInterface)
   {
      super(new InventoryDbInitInfo());
      //this.categoryFactoryInterface = categoryFactoryInterface;
      
      this.setTableName(tableName);
      
   }

   public CategoryEntity()
   {
      super(new InventoryDbInitInfo());
      this.setTableName(tableName);
   }

   /*
   public Category getUpToLevel(Integer level)
   {
      CategoryInterface categoryInterface = 
         categoryFactoryInterface.getRootInstance(categoryPath);
      new Category();
      
      for(int index = 1; index <= level.intValue(); index++)
      {
         categoryInterface.addChild(getAtLevel(level.toString()));
      }
      return categoryInterface;
   }
   
   public Category getAtLevel(String level)
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(CategoryData.LEVEL,level);
      Vector vectorOfChildCategoriesHashMaps = super.getRows(keysAndValues);
      return new Category(vectorOfChildCategoriesHashMaps);
   }
   
   public Category getAll()
   {
      Vector vectorOfChildCategoriesHashMaps = super.getAllRows();
      return new Category(vectorOfChildCategoriesHashMaps);
   }
   */
     /*
   public Categories getSubCategories(String category)
   {
      HashMap startsWithKeysAndValues = new HashMap();
      startsWithKeysAndValues.put(CategoryData.CATEGORY, category);
      Vector vectorOfHashMaps = super.getRowsStartWith(new HashMap(), startsWithKeysAndValues);
      return new Categories(vectorOfHashMaps);
   }
      */

   //ignore child categories
   public void insert(Category category)
   {
      try
      {
         Vector categoryVector = category.toVector();

         Vector values = new Vector();
         values.add(categoryVector.get(0));
         values.add(categoryVector.get(1));
         super.insert(values);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"insert"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"insert",e));
         }
      }
   }

   public void delete(String value)
   {
      try
      {
         super.deleteWhere(CategoryData.getInstance().NAME,value);
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"delete"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"delete",e));
         }
      }
   }
   
    public final String createTableStatement()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");

        stringBuffer.append(tableName);
        stringBuffer.append(" (");

        stringBuffer.append(CategoryData.getInstance().NAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(CategoryData.getInstance().LEVEL);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(CategoryData.getInstance().NAME);
        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }

   public void update(HashMap updatedValues)
   {
      super.updateWhere(CategoryData.getInstance().NAME,(String) updatedValues.get(CategoryData.getInstance().NAME),updatedValues);
   }
   
   public String backupTable()
   {
      return super.backupTable();
   }
   
   public String restoreTable(Portion portion)
   {
      return super.restoreTable(portion);
   }
   
   public String dropTable()
   {
      return super.dropTable();
   }
   
   /*
   public String getTable()
   {
      return super.getTable();
   }
    */
}
