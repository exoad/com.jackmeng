// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.awt.image.*;

public class stl_AssetFetcher
{

    public enum assetfetcher_FetcherStyle {
        WEAK, STRONG
    }

    private final Map< String, Object > lazyResource_cache;

    public stl_AssetFetcher(assetfetcher_FetcherStyle style)
    {
        lazyResource_cache = style == assetfetcher_FetcherStyle.WEAK ? new WeakHashMap<>() : new HashMap<>();
    }

    /**
     * @param path
     * @return ImageIcon
     */
    public ImageIcon image_icon(String path)
    {
        if (lazyResource_cache.containsKey(path) && lazyResource_cache.get(path) instanceof ImageIcon)
            return (ImageIcon) lazyResource_cache.get(path);
        ImageIcon i;
        try
        {
            i = new ImageIcon(
                    java.util.Objects.requireNonNull(getClass().getResource(path)));
        } catch (NullPointerException e)
        {
            return new ImageIcon(path);
        }
        lazyResource_cache.put(path, i);
        return i;
    }

    public BufferedImage image(String path)
    {
        if (lazyResource_cache.containsKey(path) && lazyResource_cache.get(path) instanceof BufferedImage)
            return (BufferedImage) lazyResource_cache.get(path);
        BufferedImage i;
        try
        {
            i = ImageIO.read(java.util.Objects.requireNonNull(getClass().getResource(path)));
        } catch (Exception e)
        {
            try
            {
                i = ImageIO.read(new File(path));
            } catch (IOException e1)
            {
                return null;
            }
        }
        lazyResource_cache.put(path, i);
        return i;
    }

    /**
     * @param path
     * @return File
     */
    public File file(String path)
    {
        if (lazyResource_cache.containsKey(path))
            return (File) lazyResource_cache.get(path);
        File i;
        try
        {
            i = new File(
                    java.util.Objects.requireNonNull(getClass().getResource(path)).getFile());
        } catch (NullPointerException e)
        {
            i = new File(path);
        }
        lazyResource_cache.put(path, i);
        return i;
    }

    public byte[] url(String url)
            throws IOException
    {
        if (lazyResource_cache.containsKey(url) && lazyResource_cache.get(url) instanceof byte[])
            return byte[].class.cast(lazyResource_cache.get(url));
        URL resourceUrl = new URL(url);
        try (InputStream inputStream = resourceUrl.openStream())
        {
            byte[] resourceBytes = inputStream.readAllBytes();
            lazyResource_cache.put(url, resourceBytes);
            return resourceBytes;
        }
    }
}