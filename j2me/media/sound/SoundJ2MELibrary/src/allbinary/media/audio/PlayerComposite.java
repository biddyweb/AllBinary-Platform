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
package allbinary.media.audio;

import javax.microedition.media.Control;
import javax.microedition.media.Controllable;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.time.GameTickTimeDelayHelperFactory;
import allbinary.time.TimeDelayHelper;

public class PlayerComposite implements Controllable, Player
{
    private Player player;
    private TimeDelayHelper timeElapsedHelper = new TimeDelayHelper(0);
    
    public PlayerComposite(Player player)
    {
        this.player = player;
        this.timeElapsedHelper.setDelay(570);
    }

    public PlayerComposite(Player player, int repeatTime)
    {
        this.player = player;
        this.timeElapsedHelper.setDelay(repeatTime);
    }

    /*
     * public PlayerComposite(Player player, boolean allowConcurrent) {
     * this.player = player;
     * 
     * if(!allowConcurrent) { this.repeatTime = this.getMediaTime(); } }
     */

    /*
     * public PlayerComposite(Player player, long repeatTime) { this.player =
     * player; this.repeatTime = repeatTime; timeElapsedHelper.setStartTime(); }
     */

    public synchronized void addPlayerListener(PlayerListener playerListener)
    {
        this.player.addPlayerListener(playerListener);
    }

    public void removePlayerListener(PlayerListener playerListener)
    {
        this.player.removePlayerListener(playerListener);
    }

    public void close()
    {
        this.player.close();
        this.player = null;
    }

    public void deallocate()
    {
        this.player.deallocate();
    }

    public String getContentType()
    {
        return this.player.getContentType();
    }

    public long getDuration()
    {
        return this.player.getDuration();
    }

    public long getMediaTime()
    {
        return this.player.getMediaTime();
    }

   /*
   public TimeBase getTimeBase()
   {
      return this.player.getTimeBase();
   }

   public synchronized void setTimeBase(TimeBase timeBase)
       throws MediaException
   {
      this.player.setTimeBase(timeBase);
   }
   */

    public void prefetch()
    {
        try
        {
            this.player.prefetch();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "prefetch", e));
        }
    }

    public void realize()
    {
        try
        {
            this.player.realize();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "realize", e));
        }
    }

    public int getState()
    {
        return this.player.getState();
    }

    public void setLoopCount(int count)
    {
        this.player.setLoopCount(count);
    }

    public long setMediaTime(long now)
    {
        try
        {
            // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance()));
            return this.player.setMediaTime(now);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "setMediaTime", e));
            return -1;
        }
    }

    private final GameTickTimeDelayHelperFactory gameTickTimeDelayHelperFactory = 
        GameTickTimeDelayHelperFactory.getInstance();
    
    public void start()
    {
        try
        {
            // this.getMediaTime()
            if (timeElapsedHelper.isTime(gameTickTimeDelayHelperFactory.getStartTime()))
            {
                this.player.start();
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().START_METHOD_NAME, e));
        }
    }

    public void stop()
    {
        try
        {
            this.player.stop();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "stop", e));
        }
    }

    public Control getControl(String controlType)
    {
        return this.player.getControl(controlType);
    }

    public Control[] getControls()
    {
        return this.getControls();
    }

    public Player getPlayer()
    {
        return this.player;
    }
}