package com.jackmeng.stl;

/**
 * <p>
 * This class defines the constants for the library itself. These include
 * constants like the stl versions, supported targets, and more. All of these
 * constants
 * in the end help with making sure dependencies are met and the latest stable
 * version
 * is used.
 *
 * @author Jack Meng
 */
public interface stl
{

    /**
     * Marks the library version in a pretty string.
     */
    String STL_VERSION = "0.0.1";

    /**
     * Marks the latest release that took place when this library was used. This
     * String should always equal {@link #STL_VERSION}.
     */
    String STL_LATEST_RELEASE = "0.0.1";

    /**
     * Marks the numerical formatted version number. This is usually the date of the
     * last
     * upload.
     */
    long STL_BASE = 2023_4_13L;
    
    long STL_RELEASE = 2023_5_24L;

    /**
     * Marks the allowed platforms this library is allowed or is most stable/tested
     * on. The elements in the array are simplified OperatingSystem/Kernel names.
     */
    String[] TARGETS = new String[] { "win32", "linux", "osx" };

    /**
     * Marks the allowed CPU Bitness Architecture allowed or is most stable/test on.
     * The popular numbers are represented as xN, such as x32, x64.
     */
    short[] TARGET_ARCHS = { 32, 64 };

}
