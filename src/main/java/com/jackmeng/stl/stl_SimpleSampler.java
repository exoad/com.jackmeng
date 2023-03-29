// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

public class stl_SimpleSampler< T >
        implements
        Consumer< T >
{
    private final int sampleRate;
    private int counter;
    private final Consumer< T > callback;

    public stl_SimpleSampler(int sampleRate, Consumer< T > callback)
    {
        this.sampleRate = sampleRate;
        this.callback = callback;
        counter = 0;
    }

    @Override public void accept(T t)
    {
        if (counter == 0)
            callback.accept(t);
        counter = (counter + 1) % sampleRate;
    }

    public static void sampleFile(String filePath, int sampleRate, Consumer< String > callback)
            throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            stl_SimpleSampler< String > sampler = new stl_SimpleSampler<>(sampleRate, callback);
            String line;
            while ((line = reader.readLine()) != null)
                sampler.accept(line);
        }
    }

}