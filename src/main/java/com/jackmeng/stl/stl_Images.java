// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.awt.Graphics2D;
import java.awt.image.*;
import java.awt.*;

public final class stl_Images
{
  private stl_Images()
  {
  }

  public static BufferedImage awtimg2bi(Image i)
  {
    if (i instanceof BufferedImage)
      return (BufferedImage) i;
    BufferedImage img = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics g = img.getGraphics();
    g.drawImage(i, 0, 0, null);
    g.dispose();
    compat_Img(img);
    return img;

  }

  public static BufferedImage make_compatible(BufferedImage img)
  {
    BufferedImage dst = stl_SwingHelper.default_gconf().createCompatibleImage(img.getWidth(), img.getHeight(),
        img.getTransparency());
    Graphics2D g2 = dst.createGraphics();
    g2.drawImage(img, 0, 0, null);
    g2.dispose();
    make_compatible(img);
    return dst;
  }

  public static BufferedImage compat_Img(BufferedImage e)
  {
    BufferedImage r = compat_Img(e.getWidth(), e.getHeight(), e);
    Graphics2D g2 = r.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.VALUE_RESOLUTION_VARIANT_DPI_FIT);
    g2.drawImage(e, 0, 0, null);
    g2.dispose();
    return r;
  }

  public static BufferedImage compat_Img(int w, int h, BufferedImage r)
  {
    return stl_SwingHelper.default_gconf().createCompatibleImage(w, h, r.getTransparency());
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

  public static Image width_subimg(BufferedImage img, int newwidth, int newheight)
  {
    return (img.getWidth() > img.getHeight()
        ? img.getSubimage(img.getWidth() / 2 - img.getHeight() / 2, 0, img.getHeight(), img.getHeight())
        : img.getSubimage(0, img.getHeight() / 2 - img.getWidth() / 2, img.getWidth(), img.getWidth()))
            .getScaledInstance(newwidth, newheight, Image.SCALE_AREA_AVERAGING);
  }

}
