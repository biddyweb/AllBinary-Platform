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
package allbinary.business.category;

import abcs.logic.basic.path.AbPath;
import allbinary.business.category.hierarchy.CategoryHierarchyInterface;
import allbinary.business.category.properties.CategoryPropertiesInterface;
import allbinary.data.tables.TableMappingInterface;
import allbinary.logic.control.validate.ValidationInterface;

import java.util.Vector;

public interface CategoryInterface extends TableMappingInterface, ValidationInterface
{
   public Vector getChildNodes();
   
   public boolean addChildProperty(CategoryPropertiesInterface categoryPropertiesInterface);
   public boolean addChild(CategoryInterface categoryInterface);
   public boolean removeChild(CategoryInterface categoryInterface);
   public boolean isLeaf() throws Exception;
   
   public CategoryPropertiesInterface getProperties();

   public void setProperties(CategoryPropertiesInterface categoryPropertiesInterface);

   public CategoryHierarchyInterface getHierarchy();

   public void setHierarchy(CategoryHierarchyInterface categoryHierarchyInterface);

   public AbPath getPath() throws Exception;
   public AbPath getFilePath() throws Exception;
   public AbPath getWebAppPath() throws Exception;
   public AbPath getRootFilePath() throws Exception;
   
   public void log() throws Exception;
}
