// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

/**
 * <h1>jlibxx.math.Point</h1>
 * <p>
 * Point class creates a point instance on a standard 2D coordinate plane with
 * a specific coordinate of x & y</p>
 * <br>
 * <p>
 * This class enables the comparison of Points with other created Point classes,
 * either of jlibxx Point or of default Java Point class, this class should enable the usage
 * of both to calculate certain properties between point instances
 * </p
 *
 * @author Jack Meng
 * @since 1.0
 * @category Math
 */

package com.jackmeng.stl.jlib;

public class jlib_Point
{
  private double x;
  private double y;

  /**
   * @param x
   *          X coordinate of a point
   * @param y
   *          Y coordiante of a point
   *
   *          Constructor creates a new Point instance with user specified
   *          coordinates (x,y)
   *          in type double
   */
  public jlib_Point(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  /**
   * Overloaded constructor if the user has chosen to specify values that are
   * whole numbers in the range of Integer.MIN_VALUE
   * to Integer.MAX_VALUE.
   *
   * There is no casting needed in that integer can be easily converted to a
   * double
   *
   * @see java.lang.Integer.MIN_VALUE
   * @see java.lang.Integer.MAX_VALUE
   * @see java.lang.Double
   * @param x
   *          X coordinate of a point (integer)
   * @param y
   *          Y coordinate of a point (integer)
   */
  public jlib_Point(int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  /**
   * Overloaded constructor if the user has chosen to specify an object of
   * Standard Point.
   *
   * This constructor will try to parse the data from the standard point class
   * into a Point format this class can utilize.
   *
   * However, the user should note that using the standard point class incontrast
   * with jlibxx's Point class can lead to problems such as
   *
   * @link https://softwareengineering.stackexchange.com/questions/307616/how-to-deal-with-classes-having-the-same-name-different-packages
   *
   * @see java.awt.Point
   * @param point
   */
  public jlib_Point(jlib_Point point)
  {
    this(point.x, point.y);
  }

  /**
   * Point constructor incorporates that the user has specificied a valid
   * coordinate in the range of double, however, if necessary
   * the user may also specify a BigIntegerPoint using the BigIntegerPoint class
   *
   * @see jlibxx.math.BigIntegerPoint
   */

  /**
   * Enables the user to specify a new coordinate for the X-value of this point
   *
   * @param x
   *          The new x value to be set and overwritten
   */
  public void setX(double x)
  {
    this.x = x;
  }

  /**
   * Enables the user to specify a new coordinate for the Y-value of this point
   *
   * @param y
   *          The new y value to be set and overwritten
   */
  public void setY(double y)
  {
    this.y = y;
  }

  /**
   * References the current instance's coordinate value of X
   *
   * @return the x value in a double type
   */
  public double getX()
  {
    return x;
  }

  /**
   * References the current instance's coordinate value of Y
   *
   * @return the y value in a double type
   */
  public double getY()
  {
    return y;
  }

  /**
   * <p>
   * Incorporated calculations that could be done between jlibxx's Point
   * Objects/Instances
   * -Slope between 2 points
   * -Distance between 2 points
   * -Midpoint between 2 points
   * -Equality between 2 points
   * </p>
   */

  /**
   * This method performs a simple slope calculation between two points of type
   * double
   *
   * If give points are in a vertical line (where x = 0), this method will attemp
   * to return undefined
   * However, a forced infinity check returns NaN if the given is a vertical line
   *
   * @code slope(4.3d, 5.0d);
   *
   *       @param x2 The second X-value to be compared with
   * @param y2
   *          The second Y-value to be compared with
   * @return The slope between the two points
   */
  public double slope(double x2, double y2)
  {
    return (y2 - y) / (x2 - x);
  }

  /**
   * This method performs a simple slope calculation between two points of type
   * double
   *
   * @code slope(new Point(4, 3));
   *
   *       @param point A point type that will be parsed
   * @return The slope between the two points
   */
  public double slope(jlib_Point point)
  {
    return (point.y - y) / (point.x - x);
  }

  /**
   * This method performs a distance calculation between two points of type double
   *
   * This method uses the distance formula of:
   * d = sqrt((x2 - x1)^2 + (y2 - y1)^2)
   *
   * @code distance(3.7d, 9.8d);
   *
   *       @param x2
   * @param y2
   * @return
   */
  public double distance(double x2, double y2)
  {
    return Math.sqrt(Math.pow((x2 - x), 2) + (y2 - y) * (y2 - y));
  }

  /**
   * This method performs a distance calculation between two points of type double
   *
   * However the caveat is that if the user provides a @see java.awt.Point class
   * instead of via double values
   */
}