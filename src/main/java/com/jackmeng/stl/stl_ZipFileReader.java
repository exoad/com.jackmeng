// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class stl_ZipFileReader
{
    private final Map<String, byte[]> cache;

    public stl_ZipFileReader(InputStream inputStream)
            throws IOException
    {
        cache = new HashMap<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream))
        {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null)
            {
                byte[] content = zipInputStream.readAllBytes();
                cache.put(entry.getName(), content);
            }
        }
    }

    public InputStream getStream(String entryName)
    {
        byte[] content = cache.get(entryName);
        return content != null ? new ByteArrayInputStream(content) : null;
    }
}
