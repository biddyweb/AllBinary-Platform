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
package allbinary.game.ai.tactical;

import javax.microedition.lcdui.Canvas;

import allbinary.game.ai.BasicAI;
import allbinary.game.ai.InputProbability;
import allbinary.game.input.GameInput;
import allbinary.game.rand.MyRandomFactory;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.logic.basic.util.visitor.Visitor;

public class BasicRandomAI extends BasicAI
{
    private int i_random = 0;

    private final InputProbability inputProbability;
    private final Visitor visitor;

    private final int[] keyArray =
    { Canvas.UP, Canvas.DOWN, Canvas.LEFT, Canvas.RIGHT, Canvas.KEY_NUM1, Canvas.KEY_NUM7,
            Canvas.KEY_NUM9 };

    public BasicRandomAI(AllBinaryLayer ownerLayerInterface, GameInput gameInput,
            InputProbability inputProbability, Visitor visitor)
    {
        super(ownerLayerInterface, gameInput);

        this.inputProbability = inputProbability;
        this.visitor = visitor;
    }

    private final MyRandomFactory myRandomFactory = MyRandomFactory.getInstance();
    
    public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
        if (this.inputProbability.getTimeDelayHelper().isTime())
        {
            i_random = myRandomFactory.getAbsoluteNextInt(this.inputProbability.getMax());

            boolean repeat = false;

            Integer[] repeatLikelyhoodIntegerArray = this.inputProbability
                    .getRepeatLikelyhoodIntegerArray();
            
            if (this.getLastKey() != -1 && repeatLikelyhoodIntegerArray[this.getLastKey()].intValue() >= i_random)
            {
                repeat = true;
            }

            if (!repeat)
            {
                Integer[][] likelyhoodIntegerArray = 
                    this.inputProbability.getLikelyhoodIntegerArray();

                i_random = myRandomFactory.getAbsoluteNextInt(this.inputProbability.getMax());

                int size = keyArray.length;
                Integer[] likelyhoodIntegerKeyArray;

                for (int index = 0; index < size; index++)
                {
                    int key = keyArray[index];

                    likelyhoodIntegerKeyArray = likelyhoodIntegerArray[key];

                    if (likelyhoodIntegerArray.length >= key
                            && likelyhoodIntegerKeyArray[0] != null)
                    {
                        if (i_random >= likelyhoodIntegerKeyArray[0].intValue()
                                && i_random < likelyhoodIntegerKeyArray[1].intValue())
                        {
                            this.setLastKey(key);
                            break;
                        }
                    }
                }
            }

            this.visitor.visit(this);
        }
    }
}