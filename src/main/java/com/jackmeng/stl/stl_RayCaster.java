// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.awt.image.*;
import java.awt.*;

public class stl_RayCaster
{
  private int screenWidth;
  private int screenHeight;
  private double[] playerPos;
  private double playerAngle;
  private double fov;
  private double[][] map;

  public stl_RayCaster(int screenWidth, int screenHeight, double[] playerPos, double playerAngle, double fov,
      double[][] map)
  {
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.playerPos = playerPos;
    this.playerAngle = playerAngle;
    this.fov = fov;
    this.map = map;
  }

  public BufferedImage castRays()
  {
    BufferedImage image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
    double angleStep = fov / screenWidth;
    double currentAngle = playerAngle - (fov / 2);
    for (int x = 0; x < screenWidth; x++)
    {
      double rayAngle = currentAngle * Math.PI / 180;
      double distanceToWall = 0;
      boolean hitWall = false;
      double[] rayPos = { playerPos[0], playerPos[1] };
      while (!hitWall && distanceToWall < map.length)
      {
        distanceToWall += 0.1;
        int testX = (int) (rayPos[0] + Math.sin(rayAngle) * distanceToWall);
        int testY = (int) (rayPos[1] + Math.cos(rayAngle) * distanceToWall);
        if (testX < 0 || testX >= map[0].length || testY < 0 || testY >= map.length)
        {
          hitWall = true;
          distanceToWall = map.length;
        }
        else if (map[testY][testX] == 1)
        {
          hitWall = true;
        }
      }
      assert distanceToWall != 0;
      int ceiling = (int) ((screenHeight / 2.0) - screenHeight / distanceToWall);
      int floor = screenHeight - ceiling;
      for (int y = 0; y < screenHeight; y++)
      {
        if (y < ceiling)
          image.setRGB(x, y, Color.BLUE.getRGB());
        else if (y > floor)
          image.setRGB(x, y, Color.GREEN.getRGB());
        else
          image.setRGB(x, y, Color.WHITE.getRGB());
      }
      currentAngle += angleStep;
    }
    return image;
  }

  public static void raycast(int[][] map, double posX, double posY, double dirX, double dirY, double planeX,
      double planeY, int[] pixels, int width, int height, Color color)
  {
    for (int x = 0; x < width; x++)
    {
      double cameraX = 2 * x / (double) width - 1;
      double rayDirX = dirX + planeX * cameraX;
      double rayDirY = dirY + planeY * cameraX;

      int mapX = (int) posX;
      int mapY = (int) posY;

      double sideDistX;
      double sideDistY;

      double deltaDistX = Math.abs(1 / rayDirX);
      double deltaDistY = Math.abs(1 / rayDirY);
      double perpWallDist;

      int stepX;
      int stepY;

      boolean hit = false;
      int side = 0;

      if (rayDirX < 0)
      {
        stepX = -1;
        sideDistX = (posX - mapX) * deltaDistX;
      }
      else
      {
        stepX = 1;
        sideDistX = (mapX + 1.0 - posX) * deltaDistX;
      }
      if (rayDirY < 0)
      {
        stepY = -1;
        sideDistY = (posY - mapY) * deltaDistY;
      }
      else
      {
        stepY = 1;
        sideDistY = (mapY + 1.0 - posY) * deltaDistY;
      }

      while (!hit)
      {
        if (sideDistX < sideDistY)
        {
          sideDistX += deltaDistX;
          mapX += stepX;
          side = 0;
        }
        else
        {
          sideDistY += deltaDistY;
          mapY += stepY;
          side = 1;
        }

        if (map[mapX][mapY] > 0)
          hit = true;
      }

      perpWallDist = side == 0 ? (mapX - posX + (1D - stepX) / 2) / rayDirX
          : (mapY - posY + (1D - stepY) / 2) / rayDirY;

      int lineHeight = (int) (height / perpWallDist);

      int drawStart = -lineHeight / 2 + height / 2;
      if (drawStart < 0)
        drawStart = 0;

      int drawEnd = lineHeight / 2 + height / 2;
      if (drawEnd >= height)
        drawEnd = height - 1;

      for (int y = drawStart; y <= drawEnd; y++)
      {
        int colorValue = color.getRGB();
        pixels[x + y * width] = colorValue;
      }
    }
  }
}
