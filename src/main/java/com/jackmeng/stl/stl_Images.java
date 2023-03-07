package com.jackmeng.stl;

import java.awt.Graphics2D;
import java.awt.image.*;
import java.awt.*;

public final class stl_Images
{
  private stl_Images()
  {
  }

  public static BufferedImage gaussian_filter(BufferedImage img, int radius, double sigma)
  {
    int size = radius * 2 + 1;
    float[] data = new float[size * size];
    double sum = 0.0;

    for (int y = -radius; y <= radius; y++)
    {
      for (int x = -radius; x <= radius; x++)
      {
        double value = Math.exp(-(x * x + y * y) / (2 * sigma * sigma));
        int index = (y + radius) * size + (x + radius);
        data[index] = (float) value;
        sum += value;
      }
    }
    assert sum != 0;
    for (int i = 0; i < data.length; i++)
      data[i] /= sum;

    Kernel kernel = new Kernel(size, size, data);
    ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
    return op.filter(img, null);
  }

  public static BufferedImage mask(BufferedImage image, BufferedImage mask)
  {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage maskedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = maskedImage.createGraphics();
    g2d.drawImage(image, 0, 0, null);
    g2d.setColor(Color.BLACK);
    g2d.drawImage(mask, 0, 0, null);
    g2d.dispose();
    return maskedImage;
  }

}
