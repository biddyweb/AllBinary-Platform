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
package allbinary.media.image;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class ImageRotationUtil
{
   
   private ImageRotationUtil()
   {
   }

   public static BufferedImage getRotatedImage(
      BufferedImage bufferedImage, double radians)
   {
      BufferedImage newBufferedImage = 
         BufferedImageUtil.create(
         bufferedImage.getWidth(null), 
         bufferedImage.getHeight(null));

      Graphics2D g = newBufferedImage.createGraphics();
      //g.translate((neww-w)/2, (newh-h)/2);

      g.rotate(radians, 
         newBufferedImage.getWidth(null)/2, 
         newBufferedImage.getHeight(null)/2);

      g.drawImage(bufferedImage, 0, 0, 
         bufferedImage.getWidth(null), bufferedImage.getHeight(null), null);

      //g.drawRenderedImage(image, null);
      g.dispose();

      return newBufferedImage;
   }
   
   public static BufferedImage[] getRotatedImages(
       BufferedImage bufferedImage, int numberOfFrames, int totalAngle)
   {
      BufferedImage bufferedImageArray[] = new BufferedImage[numberOfFrames];
      
      double arc = (2 * Math.PI) * totalAngle / 360;
      
      for(int index = 0; index < bufferedImageArray.length; index++)
      {
         double radians = (arc / bufferedImageArray.length) * index;
         bufferedImageArray[index] =
            ImageRotationUtil.getRotatedImage(bufferedImage, radians);
      }
      return bufferedImageArray;
   }

   public static BufferedImage createSpriteImage(
      BufferedImage bufferedImageArray[])
   {
      int columns = 9;
      int rows = 0;

      if(bufferedImageArray.length < columns)
      {
          columns = bufferedImageArray.length;
      }
      
      rows = (bufferedImageArray.length/columns);
      
      //Extra row for incomplete but needed row
      if(bufferedImageArray.length % columns != 0)
      {
          rows++;
      }
      
      BufferedImage bufferedImage = 
         BufferedImageUtil.create(
         bufferedImageArray[0].getWidth(null) * columns, 
         bufferedImageArray[0].getHeight(null) * rows);

      Graphics2D g = bufferedImage.createGraphics();
      //g.translate((neww-w)/2, (newh-h)/2);

      int columnIndex = 0;
      int rowIndex = 0;

      for(int index = 0; index < bufferedImageArray.length; index++)
      {
         if(index/9 != 0 && index % 9 == 0)
         {
            rowIndex++;
            columnIndex = 0;
         }

         g.drawImage(bufferedImageArray[index], 
            bufferedImageArray[index].getWidth(null) * columnIndex, 
            bufferedImageArray[index].getHeight(null) * rowIndex, 
            bufferedImageArray[index].getWidth(null), 
            bufferedImageArray[index].getHeight(null), null);
         //g.drawRenderedImage(image, null);

         columnIndex++;
      }

      g.dispose();
      
      return bufferedImage;
   }   
}
