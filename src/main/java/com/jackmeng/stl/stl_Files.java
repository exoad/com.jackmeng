package com.jackmeng.stl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URI;

public final class stl_Files
{
  private stl_Files()
  {
  }

  /**
   * Quick write (not speed wise ({@link java.io.PrintWriter} is used))
   *
   * @param content
   * @param fileName
   * @param errorHandler
   */
  public static void fwrite_1(String fileName, stl_Callback< Void, Exception > errorHandler, String content)
  {
    try
    {
      PrintWriter pw = new PrintWriter(fileName);
      pw.print(content);
      pw.flush();
      pw.close();
    } catch (Exception e)
    {
      errorHandler.call(e);
    }
  }

  public static URI uri(String fileName)
  {
    return new File(fileName).toPath().toUri();
  }

  public static void erasure_create_file(String fileName, stl_Callback< Void, Exception > errorHandler)
  {
    File f = new File(fileName);
    if (f.exists())
      f.delete();
    try
    {
      f.createNewFile();
    } catch (Exception e)
    {
      errorHandler.call(e);
    }
  }

  public static void fappend_1(String fileName, stl_Callback< Void, Exception > errorHandler, String content)
  {
    try
    {
      PrintWriter pw = new PrintWriter(fileName);
      pw.append(content);
      pw.flush();
      pw.close();
    } catch (Exception e)
    {
      errorHandler.call(e);
    }
  }

  /**
   * Read files by String (char[]) lines
   *
   * @param fileName
   * @param errorHandler
   * @param consumer
   */
  public static void fread_bl_1(String fileName, stl_Callback< Void, Exception > errorHandler,
      stl_Callback< Void, String > consumer)
  {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
    {
      while (br.ready())
        consumer.call(br.readLine());
    } catch (Exception e)
    {
      errorHandler.call(e);
    }
  }

  /**
   * Read by byte
   *
   * @param fileName
   * @param errorHandler
   * @param consumer
   */
  public static void fread_s_1(String fileName, stl_Callback< Void, Exception > errorHandler,
      stl_Callback< Void, Byte > consumer)
  {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
    {
      while (br.ready())
        consumer.call((byte) (((Integer) br.read()).byteValue() & 0xFF));
    } catch (Exception e)
    {
      errorHandler.call(e);
    }
  }
}