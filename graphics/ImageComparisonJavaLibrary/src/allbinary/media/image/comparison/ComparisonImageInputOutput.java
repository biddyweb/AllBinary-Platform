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
package allbinary.media.image.comparison;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.java.number.LongUtil;
import abcs.logic.visual.media.MediaData;
import allbinary.input.automation.ImageOutputData;
import allbinary.media.image.cache.BufferedImageCacheable;
import allbinary.media.image.io.ImageIOInterface;
import allbinary.media.image.ImageUtil;
import java.awt.image.BufferedImage;

public class ComparisonImageInputOutput implements ImageIOInterface
{
    private final static String ROOT_NAME = "_Changed_";
    
    public ComparisonImageInputOutput()
    {
    }
    
    public void save(Long frame) throws Exception
    {
        if(frame > 0)
        {
            ImageComparisonResultFrameCacheable imageComparisonResultFrameCacheable =
                (ImageComparisonResultFrameCacheable)
                ImageComparisonResultCacheSingleton.getInstance().get(frame);
            
            if(imageComparisonResultFrameCacheable != null)
            {
                ImageComparisonResult imageComparisonResult =
                    imageComparisonResultFrameCacheable.getImageComparisonResult();
                
                this.save(imageComparisonResult,
                    imageComparisonResultFrameCacheable.getFrame());
            }
            else
            {
                LogUtil.put(new Log("Comparison Results Not Available for Output: " +
                    frame, this, "save"));
            }
        }
        else
        {
            LogUtil.put(new Log("No Comparison Results: for first frame: " +
                frame, this, "save"));
        }
    }
    
    public void save(ImageComparisonResult imageComparisonResult, Long frame)
    throws Exception
    {
        StringBuffer filePathStringBuffer = new StringBuffer();
        filePathStringBuffer.append(ImageOutputData.SAVE_PATH);
        filePathStringBuffer.append(LongUtil.fillIn(frame.toString()));
        filePathStringBuffer.append(ROOT_NAME);
        
        StringBuffer filePathStringBuffer1 = new StringBuffer();
        filePathStringBuffer1.append(filePathStringBuffer.toString());
        filePathStringBuffer1.append("_1");
        filePathStringBuffer1.append(MediaData.JPG.getExtension());
        String filePath1 = filePathStringBuffer1.toString();
        
        StringBuffer filePathStringBuffer2 = new StringBuffer();
        filePathStringBuffer2.append(filePathStringBuffer.toString());
        filePathStringBuffer2.append("_2");
        filePathStringBuffer2.append(MediaData.JPG.getExtension());
        String filePath2 = filePathStringBuffer2.toString();
        
        BufferedImageCacheable bufferedImageCacheables[] =
            ChangedPixelsUtil.generateBufferedImageChacheables(
            imageComparisonResult);
        
        LogUtil.put(new Log("Comparison Image File Path 1: " + filePath1, this, "save"));
        LogUtil.put(new Log("Comparison Image File Path 2: " + filePath2, this, "save"));
        
        BufferedImage bufferedImageArray[] = new BufferedImage[2];
        
        bufferedImageArray[0] = bufferedImageCacheables[0].getBufferedImage();
        bufferedImageArray[1] = bufferedImageCacheables[1].getBufferedImage();
        
        ImageUtil.save(filePath1, bufferedImageArray[0]);
        ImageUtil.save(filePath2, bufferedImageArray[1]);
    }
    
}
