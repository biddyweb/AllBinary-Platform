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
package allbinary.game.ai;

import allbinary.game.input.GameInput;
import allbinary.game.physics.velocity.BasicVelocityProperties;
import allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.logic.math.BasicDecimal;

public class UpDownVectorAI extends BasicAI
{
   private BasicVelocityProperties velocityInterface;
   private int index = 0;

   public UpDownVectorAI(AllBinaryLayer ownerLayerInterface,
      GameInput gameInput) throws Exception
   {
      super(ownerLayerInterface, gameInput);

      VelocityInterfaceCompositeInterface velocityInterfaceCompositeInterface =
         (VelocityInterfaceCompositeInterface) this.getOwnerLayerInterface();

      this.velocityInterface =
         velocityInterfaceCompositeInterface.getVelocityProperties();
      this.velocityInterface.getVelocityYBasicDecimal().set(0);
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception
   {
      //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "processAI"));

      int y = this.getOwnerLayerInterface().getHeight() * 270;
      
      if (this.index < 5)
      {
          BasicDecimal basicDecimal = this.velocityInterface.getVelocityYBasicDecimal();
          
          basicDecimal.set(0);
          basicDecimal.add(y);

         this.index++;
      } else if (this.index < 10)
      {
          BasicDecimal basicDecimal = this.velocityInterface.getVelocityYBasicDecimal();
          
          basicDecimal.set(0);
          basicDecimal.subtract(y);

         this.index++;
      } else
      {
         this.index = 0;
         this.processAI(allBinaryLayerManager);
      }
   }
}