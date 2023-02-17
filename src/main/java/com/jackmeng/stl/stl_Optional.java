package com.jackmeng.stl;

/**
 * <p>
 * This class can be used for both Return Types and Parameters.
 * <br>
 * Why? It was designed to be used inplace of Java STDLIB's
 * Optional<?>, as it is against standards to use this as a parameter.
 * <br>
 * Why use for a parameter? It helps to alleviate ugly null checks
 * in a JVM language like Java. However, in certain JVM languages like
 * Kotlin, Scala, Clojure, such a functionality is either not necessary
 * or completely implemented for you.
 *
 *
 * This class targets Java11+
 *
 * @author Jack Meng
 */
public final class stl_Optional< T >
{

}
