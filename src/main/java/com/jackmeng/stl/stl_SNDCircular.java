// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class stl_SNDCircular
{
  private SourceDataLine line;
  private final stl_CircularBuffer buffer;
  private final int buffer_size;

  public stl_SNDCircular()
  {
    this(1024, new stl_CircularBuffer());
  }

  public stl_SNDCircular(int buffer_size, stl_CircularBuffer reference)
  {
      buffer = reference;
      this.buffer_size = buffer_size;
  }

  public void open(File file)
        throws
        IOException,
        LineUnavailableException,
        UnsupportedAudioFileException
  {
    AudioInputStream in = AudioSystem.getAudioInputStream(file);
    AudioFormat format = in.getFormat();
    DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
    line = (SourceDataLine) AudioSystem.getLine(info);
    line.open(format);
    line.start();
    byte[] buf = new byte[buffer_size];
    int bytesRead;
    while ((bytesRead = in.read(buf)) != -1)
      buffer.push(buf, 0, bytesRead);
    in.close();
  }

  public void play()
  {
    new Thread(() -> {
      byte[] data = new byte[buffer_size];
      while (buffer.used_sz() > 0)
      {
        int bytesRead = buffer.pop(data, 0, data.length);
        line.write(data, 0, bytesRead);
      }
    }).start();
  }

  public void pause()
  {
    line.stop();
  }

  public void stop()
  {
    line.stop();
    line.flush();
    buffer.init();
  }

  public void close()
  {
    line.close();
    buffer.init();
  }
}