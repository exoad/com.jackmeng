// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.stl;

public final class stl_Crypto
{
  private stl_Crypto()
  {
  }

  public static final char[] BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
      .toCharArray();

  public static final byte[] BASE64_DECODE_TABLE = new byte[] {
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
      -1, -1, -1, -1,
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1,
      -1, -2, -1, -1, -1,
      0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63,
      -1, 26, 27, 28,
      29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1
  };

  public static byte[] base64_encode(byte[] data)
  {
    int length = data.length;
    int padding = (3 - length % 3) % 3;
    byte[] paddedData = new byte[length + padding];
    System.arraycopy(data, 0, paddedData, 0, length);

    byte[] encodedData = new byte[(length + padding) / 3 * 4];
    for (int i = 0, j = 0; i < length; i += 3, j += 4)
    {
      int buffer = ((paddedData[i] & 0xff) << 16) | ((paddedData[i + 1] & 0xff) << 8) | (paddedData[i + 2] & 0xff);
      encodedData[j] = (byte) BASE64_ALPHABET[(buffer >> 18) & 0x3f];
      encodedData[j + 1] = (byte) BASE64_ALPHABET[(buffer >> 12) & 0x3f];
      encodedData[j + 2] = (byte) (i + 1 < length ? BASE64_ALPHABET[(buffer >> 6) & 0x3f] : '=');
      encodedData[j + 3] = (byte) (i + 2 < length ? BASE64_ALPHABET[buffer & 0x3f] : '=');
    }
    return encodedData;
  }

  public static byte[] base64_decode(byte[] data)
  {
    int length = data.length;
    int padding = (data[length - 1] == '=') ? (data[length - 2] == '=' ? 2 : 1) : 0;
    int decodedLength = length * 3 / 4 - padding;
    byte[] decodedData = new byte[decodedLength];

    for (int i = 0, j = 0; i < length; i += 4)
    {
      int buffer = (BASE64_DECODE_TABLE[data[i] & 0xff] << 18) | (BASE64_DECODE_TABLE[data[i + 1] & 0xff] << 12) |
          ((i + 2 < length && data[i + 2] != '=') ? (BASE64_DECODE_TABLE[data[i + 2] & 0xff] << 6) : 0) |
          ((i + 3 < length && data[i + 3] != '=') ? BASE64_DECODE_TABLE[data[i + 3] & 0xff] : 0);
      decodedData[j++] = (byte) ((buffer >> 16) & 0xff);
      if (i + 2 < length && data[i + 2] != '=')
        decodedData[j++] = (byte) ((buffer >> 8) & 0xff);
      if (i + 3 < length && data[i + 3] != '=')
        decodedData[j++] = (byte) (buffer & 0xff);
    }
    return decodedData;

  }

}
