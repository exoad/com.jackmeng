// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl.ui;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.JFrame;

import java.awt.event.*;

public class gui_App
    implements
    Runnable,
    Accessible
{
  private final JFrame frame;

  public enum App_States {
    LOST_FOCUS, GAINED_FOCUS, WINDOW_EXITING, WINDOW_EXITED, WINDOW_SHOWN, WINDOW_ICONIFIED, WINDOW_DEICONIFIED, WINDOW_ACTIVATED, WINDOW_DEACTIVATED;
  }

  public gui_App(String titleName, ux_Dimension dimension, ux_Listener< App_States > app_State_Listener)
  {
    frame = new JFrame();
    frame.addWindowListener(new WindowListener() {
      @Override public void windowIconified(WindowEvent e)
      {
        app_State_Listener.call(App_States.WINDOW_ICONIFIED);
      }

      @Override public void windowOpened(WindowEvent e)
      {
        app_State_Listener.call(App_States.WINDOW_SHOWN);
      }

      @Override public void windowClosing(WindowEvent e)
      {
        app_State_Listener.call(App_States.WINDOW_EXITING);
      }

      @Override public void windowClosed(WindowEvent e)
      {
        app_State_Listener.call(App_States.WINDOW_EXITED);
      }

      @Override public void windowDeiconified(WindowEvent e)
      {
        app_State_Listener.call(App_States.WINDOW_DEICONIFIED);
      }

      @Override public void windowActivated(WindowEvent e)
      {
        app_State_Listener.call(App_States.WINDOW_ACTIVATED);
      }

      @Override public void windowDeactivated(WindowEvent e)
      {
        app_State_Listener.call(App_States.WINDOW_DEACTIVATED);
      }
    });
  }

  @Override public AccessibleContext getAccessibleContext()
  {
    return frame.getAccessibleContext();
  }

  @Override public void run()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'run'");
  }

}