// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
// import java.lang.ref.Reference;
// import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jack Meng
 */
public class stl_Logger
    implements
        Runnable,
        Serializable
{
    public enum Logger_DisableBehavior
    {
        DROP_ALL, KEEP_ALL, CACHE_OLD;
    }

    private final long try_save_ms_periodic;
    private final String saveLocation, name;
    private final File file;
    private final Stack<String> logs = new Stack<>();
    private boolean running = false, enabled = true;
    private final AtomicBoolean saving = new AtomicBoolean(false);
    private final transient List<Runnable> afterRoutine;
    private transient long COLLECTED = 0L;
    private final AtomicLong CYCLE = new AtomicLong(0L);
    private int log_char_per_line = 70;
    //private final Reference<Stack<String>> logs_cache = new SoftReference<>(new Stack<>());
    //private Logger_DisableBehavior disableBehavior = Logger_DisableBehavior.KEEP_ALL;

    public stl_Logger(String loggerName, String saveLocationFolder, long save_time)
    {
        this.try_save_ms_periodic = save_time;
        this.name = loggerName;
        this.saveLocation = saveLocationFolder;
        this.file = new File(saveLocationFolder + stl0.dirm() + loggerName + "_" + stl_Chrono.format_millis() + ".log");
        afterRoutine = new ArrayList<>(10);
    }

    public stl_Logger(String loggerName, long save_time)
    {
        this(loggerName, stl_Commons.tmpdir(), save_time);
    }

    // public Logger_DisableBehavior disableBehavior()
    // {
    //     return disableBehavior;
    // }

    // public synchronized Logger_DisableBehavior disableBehavior(Logger_DisableBehavior e)
    // {
    //     Logger_DisableBehavior old = this.disableBehavior;
    //     this.disableBehavior = e;
    //     return old;
    // }

    public void enable(boolean e)
    {
        this.enabled = e;
    }

    public boolean enabled()
    {
        return this.enabled;
    }

    public void char_per_line(int i)
    {
        this.log_char_per_line = i;
    }

    public int char_per_line()
    {
        return log_char_per_line;
    }

    public stl_Logger(String loggerName, String saveLocation)
    {
        this(loggerName, saveLocation, 4000L);
    }

    public synchronized void push(Object contents)
    {
        String placeholder = String.format("%09d", COLLECTED + 1) + " | " + String.format("%09d", CYCLE.get()) + "   |   " + stl_Chrono.format_millis("HH:mm:ss MM/dd/YYYY") + "    |    ";
        if(saving.get())
            afterRoutine.add(() -> logs.push(placeholder + stl_Str.insert_nl(contents.toString(), char_per_line(), "\n" + stl_Str.n_copies(placeholder.length() - 5, " ")  + "|    ")));
        else
            logs.push(placeholder + stl_Str.insert_nl(contents.toString(), char_per_line(), "\n" + stl_Str.n_copies(placeholder.length() - 5, " ") + "|    "));
        COLLECTED++;
    }

    public synchronized void log(Object ... contents)
    {
        for(Object e : contents) push(e);
    }

    public synchronized void kill()
    {
        afterRoutine.clear();
        logs.clear();
        stl0.STL_TIMER0.cancel();
        Thread.currentThread().interrupt();
    }

    public String name()
    {
        return name;
    }

    public String saveLocation()
    {
        return saveLocation;
    }

    @Override
    public synchronized void run()
    {
        if(!running && enabled)
        {
            if(file.exists())
                file.delete();
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            stl0.STL_TIMER0.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    if(enabled)
                    {
                        if(logs.size() > 0)
                        {
                            saving.set(true);
                            synchronized (logs) {
                                StringBuilder sb = new StringBuilder();
                                while(!logs.isEmpty())
                                    sb.append(logs.pop()).append('\n');

                                try {
                                    Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.WRITE, StandardOpenOption.APPEND);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (afterRoutine.size() > 0)
                                    for(int i = 0; i < afterRoutine.size(); i++)
                                        afterRoutine.remove(i).run();
                            }
                            saving.set(false);
                            CYCLE.set(CYCLE.get() + 1L);
                        }
                    }
                }
            }, 500L, try_save_ms_periodic);
            running = true;
        }
    }
}
