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
package allbinary.input.motion.touch.action;

import allbinary.game.input.PlatformInputMappingFactory;
import allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;
import allbinary.input.motion.gesture.TouchMotionGestureFactory;

public class DiagonalDownRightTouchInputToGameKeyEventAction extends GameKeyCompleteMotionGestureInputEvent
{
    private static final GameKeyCompleteMotionGestureInputEvent SINGLETON = new DiagonalDownRightTouchInputToGameKeyEventAction();
    
    public static GameKeyCompleteMotionGestureInputEvent getInstance()
    {
        return SINGLETON;
    }

    private DiagonalDownRightTouchInputToGameKeyEventAction()
    {
        super("Diagonal Down Right Action", 
           TouchMotionGestureFactory.getInstance().DIAGONAL_DOWN_RIGHT,
           PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping());
    }    
}
