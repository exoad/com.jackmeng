// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl.jlib;

public class jlib_Dimension< T extends Number >
{
  private T width;
  private T height;

  public jlib_Dimension(T width, T height)
  {
    this.width = width;
    this.height = height;
  }

  @SuppressWarnings("unchecked") public jlib_Dimension(jlib_Point topRight, jlib_Point topLeft, jlib_Point bottomLeft,
      jlib_Point bottomRight)
  {
    this.width = (T) ((Object) (((int) topRight.getX()) - ((int) topLeft.getX())));
    this.height = (T) ((Object) (((int) topRight.getY()) - ((int) bottomRight.getY())));
  }

  public jlib_Dimension()
  {
    this(0, 0);
  }

  public jlib_Dimension(jlib_Dimension< T > other)
  {
    this.width = other.width;
    this.height = other.height;
  }

  @SuppressWarnings("unchecked") public jlib_Dimension(java.awt.Dimension other)
  {
    this.width = (T) ((Object) other.width);
    this.height = (T) ((Object) other.height);
  }

  @SuppressWarnings("unchecked") public jlib_Dimension(int i, int j)
  {
    this.width = (T) ((Object) i);
    this.height = (T) ((Object) j);
  }

  public java.awt.Dimension unwrapClass()
  {
    return new java.awt.Dimension((int) width, (int) height);
  }

  public float getWidth()
  {
    return (float) width;
  }

  public float getHeight()
  {
    return (float) height;
  }

  public String toString()
  {
    return "(" + width + ", " + height + ")";
  }

}