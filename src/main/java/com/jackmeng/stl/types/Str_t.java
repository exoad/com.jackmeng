package com.jackmeng.stl.types;


import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.Iterator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class is an extension of the original string class.
 * Mostly I was planning for you to instead of having to use .charAt()
 * you can just use [] and treat it as an array.
 *
 * @author Jack Meng
 * @version 1.0
 * @since 1.0
 */
public final class Str_t implements Iterable<Character> {

    /// STATIC CONSTANT FIELDS ///
    public static final char[] STD_PUNCTUATION = { ',', '.', '=', '+', '-', '*', '/', '%', '^', '&', '|', '!', '~',
            '(', ')', '[', ']', '{', '}', '<', '>', '?' };
    public static final char[] STD_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    public static final char[] STD_ALPHA = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    public static final char[] STD_ALPHA_UPPER = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static final char NULL_CHAR = '\0';
    public static final char NEWLINE = '\n';

    @Target({ ElementType.METHOD })
    @Retention(RetentionPolicy.SOURCE)
    private @interface LibTestingOnly {
    }

    /**
     * Currently, I do not expect the user to input a string bigger than
     * Integer.MAX_VALUE,
     * however in future updates, I can assure a Vector of char arrays to store the
     * string.
     */
    public char[] strc;

    public Str_t(String str) {
        this.strc = str.toCharArray();
    }

    public Str_t(char[] str) {
        this.strc = str;
    }

    public Str_t(byte[] str) {
        this.strc = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            this.strc[i] = (char) str[i];
        }
    }

    public Str_t(StringBuilder sb) {
        this.strc = new Str_t(sb.toString()).strc;
    }

    public Str_t(Str_t str) {
        this.strc = str.strc;
    }

    public char[] reversedContent() {
        char[] reversed = new char[strc.length];
        for (int i = 0; i < strc.length; i++) {
            reversed[i] = strc[strc.length - 1 - i];
        }
        return reversed;
    }

    public void reverse() {
        strc = reversedContent();
    }

    public char[] scrambledContent() {
        char[] scrambled = new char[strc.length];
        System.arraycopy(strc, 0, scrambled, 0, strc.length);
        for (int i = 0; i < strc.length; i++) {
            int rand = (int) (Math.random() * strc.length);
            char temp = scrambled[i];
            scrambled[i] = scrambled[rand];
            scrambled[rand] = temp;
        }
        return scrambled;
    }

    public void scramble() {
        strc = scrambledContent();
    }

    public void reset(Str_t str) {
        this.strc = str.strc;
    }

    public char randChar() {
        return strc[(int) (Math.random() * strc.length)];
    }

    public char getChar(int i) {
        return strc[i];
    }

    public void setCharNonRetraceable(int i, char c) {
        strc[i] = c;
    }

    public char setChar(int i, char c) {
        char old = strc[i];
        strc[i] = c;
        return old;
    }

    public char pop_back() {
        char c = strc[strc.length - 1];
        strc = Arrays.copyOf(strc, strc.length - 1);
        return c;
    }

    public int find_first_of(char c) {
        for (int i = 0; i < strc.length; i++) {
            if (strc[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public int find_last_of(char c) {
        for (int i = strc.length - 1; i >= 0; i--) {
            if (strc[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public void resize(int newSize) {
        strc = Arrays.copyOf(strc, newSize);
    }

    public char[] shiftedRight(int n) {
        char[] shifted = new char[strc.length];
        for (int i = 0; i < strc.length; i++) {
            shifted[i] = strc[(i + n) % strc.length];
        }
        return shifted;
    }

    public void moveRight(int n) {
        strc = shiftedRight(n);
    }

    public char[] shiftedLeft(int n) {
        char[] shifted = new char[strc.length];
        for (int i = 0; i < strc.length; i++) {
            shifted[i] = strc[(i - n + strc.length) % strc.length];
        }
        return shifted;
    }

    public void moveLeft(int n) {
        strc = shiftedLeft(n);
    }

    public char[] bitShiftedRight(int n) {
        char[] shifted = new char[strc.length];
        for (int i = 0; i < strc.length; i++) {
            shifted[i] = (char) ((strc[i] >> n) & 0xFF);
        }
        return shifted;
    }

    public void bitMoveRight(int n) {
        strc = bitShiftedRight(n);
    }

    public char[] bitShiftedLeft(int n) {
        char[] shifted = new char[strc.length];
        for (int i = 0; i < strc.length; i++) {
            shifted[i] = (char) ((strc[i] << n) & 0xFF);
        }
        return shifted;
    }

    public char[] appendedContent(char... c) {
        char[] appended = new char[strc.length + c.length];
        System.arraycopy(strc, 0, appended, 0, strc.length);
        System.arraycopy(c, 0, appended, strc.length, c.length);
        return appended;
    }

    public void append(char... c) {
        strc = appendedContent(c);
    }

    public void safeAppend(char... c) {
        strc = appendedContent(c);
    }

    public void bitMoveLeft(int n) {
        strc = bitShiftedLeft(n);
    }

    public char[] bitShiftedUnsignedRight(int n) {
        char[] shifted = new char[strc.length];
        for (int i = 0; i < strc.length; i++) {
            shifted[i] = (char) (strc[i] >>> n);
        }
        return shifted;
    }

    public void bitMoveUnsignedRight(int n) {
        strc = bitShiftedUnsignedRight(n);
    }

    public byte[] toByteArray() {
        byte[] bytes = new byte[strc.length];
        for (int i = 0; i < strc.length; i++) {
            bytes[i] = (byte) strc[i];
        }
        return bytes;
    }

    public char[] substringedContent(int start, int end) {
        char[] substr = new char[end - start];
        if (end - start >= 0) System.arraycopy(strc, start, substr, 0, end - start);
        return substr;
    }

    public void substr(int start, int end) {
        strc = substringedContent(start, end);
    }

    public void remove(int start, int end) {
        char[] newstr = new char[strc.length - (end - start)];
        if (start >= 0) System.arraycopy(strc, 0, newstr, 0, start);
        if (strc.length - end >= 0) System.arraycopy(strc, end, newstr, end - (end - start), strc.length - end);
        strc = newstr;
    }

    public void split(String regex) {
        String[] split = Arrays.toString(strc).split(regex);
        strc = new char[split.length];
        for (int i = 0; i < split.length; i++) {
            strc[i] = split[i].charAt(0);
        }
    }

    public void split(String regex, int limit) {
        String[] split = Arrays.toString(strc).split(regex, limit);
        strc = new char[split.length];
        for (int i = 0; i < split.length; i++) {
            strc[i] = split[i].charAt(0);
        }
    }

    public void replace(int start, int end, char... c) {
        char[] newstr = new char[strc.length - (end - start) + c.length];
        if (start >= 0) System.arraycopy(strc, 0, newstr, 0, start);
        System.arraycopy(c, 0, newstr, start, c.length);
        for (int i = end; i < strc.length; i++) {
            newstr[start + c.length + i - (end - start)] = strc[i];
        }
        strc = newstr;
    }

    public int match(char c) {
        int count = 0;
        for (char x : strc) {
            if (x == c) {
                count++;
            }
        }
        return count;
    }

    public void swap(int i, int j) {
        assert i < strc.length;
        assert j < strc.length;
        char temp = strc[i];
        strc[i] = strc[j];
        strc[j] = temp;
    }

    public void dispose() {
        strc = null;
    }

    public int[] toIntArray() {
        int[] ints = new int[strc.length];
        for (int i = 0; i < strc.length; i++) {
            ints[i] = strc[i];
        }
        return ints;
    }

    /// STATIC METHODS FOR EXTERNAL ACCESS ///

    public static Str_t fromBytes(byte[] bytes) {
        char[] chars = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            chars[i] = (char) bytes[i];
        }
        return new Str_t(chars);
    }

    public static char[] toupper(char[] c) {
        char[] uppercased = new char[c.length];
        for (int i = 0; i < c.length; i++) {
            uppercased[i] = Character.toUpperCase(c[i]);
        }
        return uppercased;
    }

    public static char[] tolower(char[] c) {
        char[] lowercased = new char[c.length];
        for (int i = 0; i < c.length; i++) {
            lowercased[i] = Character.toLowerCase(c[i]);
        }
        return lowercased;
    }

    public static <T> Str_t nonRefereableNewString(T[] args) {
        StringBuilder sb = new StringBuilder();
        for (T t : args) {
            sb.append(t);
        }
        return new Str_t(sb.toString());
    }

    /// OVERRIDDEN METHODS ///

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Str_t str) {
            if (str.strc.length != strc.length) {
                return false;
            }
            for (int i = 0; i < strc.length; i++) {
                if (strc[i] != str.strc[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Arrays.hashCode(this.strc);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char c : strc) {
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < strc.length;
            }

            @Override
            public Character next() {
                if (index > strc.length) {
                    throw new NoSuchElementException();
                }
                return strc[index++];
            }

            @Override
            public void remove() {
                // IGNORE
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Character> action) {
        for (char c : strc) {
            action.accept(c);
        }
    }

    /// ONLY FOR TESTING ///

    @LibTestingOnly
    public synchronized void print() {
        System.out.println(this);
    }
}
