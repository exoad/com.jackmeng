// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.math.BigInteger;

import java.awt.*;

public final class stl_BlurHash_2
{
  public static String encode(int[] pixels, int width, int height, int componentX, int componentY)
  {
    int numComponents = componentX * componentY;
    int[] colors = new int[numComponents];
    double[][] colorCoords = new double[numComponents][2];

    for (int y = 0; y < componentY; y++)
    {
      for (int x = 0; x < componentX; x++)
      {
        int index = y * componentX + x;
        double[] coords = getColorCoordinates(x, y, componentX, componentY);
        colorCoords[index] = coords;

        double rAcc = 0, gAcc = 0, bAcc = 0;
        for (int j = 0; j < height; j++)
        {
          for (int i = 0; i < width; i++)
          {
            double s = (x == 0 ? 1 : 2) * Math.cos(Math.PI * i / width);
            double t = (y == 0 ? 1 : 2) * Math.cos(Math.PI * j / height);
            double factor = s * t / width / height;
            int pixelIndex = j * width + i;
            Color c = new Color(pixels[pixelIndex], true);
            rAcc += factor * c.getRed();
            gAcc += factor * c.getGreen();
            bAcc += factor * c.getBlue();
          }
        }

        int r = (int) Math.round(rAcc);
        int g = (int) Math.round(gAcc);
        int b = (int) Math.round(bAcc);
        colors[index] = packColor(r, g, b);
      }
    }

    BigInteger[] colorValues = new BigInteger[numComponents];
    BigInteger maxColorValue = BigInteger.valueOf(1L << 24).subtract(BigInteger.ONE);
    for (int i = 0; i < numComponents; i++)
    {
      int color = colors[i];
      int r = (color >> 16) & 0xff;
      int g = (color >> 8) & 0xff;
      int b = color & 0xff;
      BigInteger value = BigInteger.valueOf(encodeColor(r, maxColorValue))
          .shiftLeft(16).or(BigInteger.valueOf(encodeColor(g, maxColorValue)))
          .shiftLeft(16).or(BigInteger.valueOf(encodeColor(b, maxColorValue)));
      colorValues[i] = value;
    }

    int[] size = getSize(componentX, componentY);
    return encodeBlurhash(size[0], size[1], colorCoords, colorValues);
  }

  private static double[] getColorCoordinates(int x, int y, int componentX, int componentY)
  {
    double[] result = new double[2];
    result[0] = (2 * x + 1) / (2.0 * componentX);
    result[1] = (2 * y + 1) / (2.0 * componentY);
    return result;
  }

  private static int packColor(int r, int g, int b)
  {
    return (r << 16) | (g << 8) | b;
  }

  private static int encodeColor(int color, BigInteger maxColorValue)
  {
    BigInteger quantized = BigInteger.valueOf(color).multiply(BigInteger.valueOf(69L))
        .add(maxColorValue.divide(BigInteger.valueOf(2L)))
        .divide(maxColorValue);
    return quantized.intValue();
  }

  private static int[] getSize(int componentX, int componentY)
  {
    int maxDimension = Math.max(componentX, componentY);
    int smallDimension = Math.min(componentX, componentY);
    int x = (int) Math.ceil(Math.sqrt(maxDimension * (double) smallDimension));
    int y = (int) Math.ceil((double) maxDimension * smallDimension / x);
    return new int[] { x, y };
  }

  private static String encodeBlurhash(int width, int height, double[][] colorCoords, BigInteger[] colorValues)
  {
    int numComponents = colorCoords.length;
    int sizeFlag = (int) Math.max(0, Math.min(9, Math.floor(Math.log(width) / Math.log(2) - 1)))
        + (int) Math.max(0, Math.min(9, Math.floor(Math.log(height) / Math.log(2) - 1))) * 9;
    StringBuilder builder = new StringBuilder();
    builder.append(getBase83(sizeFlag, 1));
    for (int i = 0; i < numComponents; i++)
    {
      BigInteger value = colorValues[i];
      double[] coords = colorCoords[i];
      int quantizedX = (int) Math.max(0, Math.min(18, Math.floor(coords[0] * 19)));
      int quantizedY = (int) Math.max(0, Math.min(18, Math.floor(coords[1] * 19)));
      int quantized = quantizedY * 19 + quantizedX;
      builder.append(getBase83(quantized, 1));
      builder.append(getBase83(value.intValue(), 4));
    }
    return builder.toString();
  }

  private static char[] BASE83_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz#$%*+,-.:;=?@[]^_{|}~"
      .toCharArray();

  private static String getBase83(int value, int numChars)
  {
    char[] chars = new char[numChars];
    for (int i = numChars - 1; i >= 0; i--)
    {
      chars[i] = BASE83_ALPHABET[value % 83];
      value /= 83;
    }
    return new String(chars);
  }

}