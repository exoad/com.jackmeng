package com.jackmeng.stl;

import com.jackmeng.stl.types.Null_t;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class stl_Commands
        implements
        Runnable
{
    private abstract static class Commands_Fx
            implements stl_Callback< String, String >
    {
        private boolean finished = false;
        private final PrintStream ps;

        public Commands_Fx(PrintStream printstream)
        {
            ps = printstream == null ? System.out : printstream;
        }

        /**
         * Called by the original stl_Commands parser on every tick
         * to parse and run another argument that was related to this
         * argument.
         *
         * @param argument
         *            The input argument
         * @return The next argument to be asked by stl_Commands. if this is null, the
         *         stl_Commands move on to the next Consumer (AKA argument)
         */
        public abstract String consume(String argument);

        /**
         * @return The prefix of the command name. Should be standarized to
         *         '-','--',or'---''
         */
        public abstract char[] sequence();

        @Override public String call(String argument)
        {
            return consume(argument);
        }

        public PrintStream stream()
        {
            return ps;
        }

        /**
         * Should tell the original stl_Commands if this current consumer function
         * has done its job.
         *
         * @return (true -> done >|| false -> not done)
         */
        boolean finished()
        {
            return finished;
        }

        public abstract Commands_Type associated_type();
    }

    public static class Type_INFORMATIONAL
            extends Commands_Fx
    {

        public Type_INFORMATIONAL()
        {
            super(System.out);
        }

        @Override public String consume(String argument)
        {

            throw new UnsupportedOperationException("Unimplemented method 'consume'");
        }

        @Override public char[] sequence()
        {
            return new char[] { '-' };
        }

        @Override public Commands_Type associated_type()
        {

            throw new UnsupportedOperationException("Unimplemented method 'associated_type'");
        }

    }

    public static class Type_DYNAMIC
            extends Commands_Fx
    {

        public Type_DYNAMIC()
        {
            super(System.out);
        }

        @Override public String consume(String argument)
        {

            throw new UnsupportedOperationException("Unimplemented method 'consume'");
        }

        @Override public char[] sequence()
        {
            return new char[] { '-', '-' };
        }

        @Override public Commands_Type associated_type()
        {

            throw new UnsupportedOperationException("Unimplemented method 'associated_type'");
        }

    }

    public static class Type_HYBRID
            extends Commands_Fx
    {

        public Type_HYBRID()
        {
            super(System.out);
        }

        @Override public String consume(String argument)
        {

            throw new UnsupportedOperationException("Unimplemented method 'consume'");
        }

        @Override public char[] sequence()
        {

            throw new UnsupportedOperationException("Unimplemented method 'sequence'");
        }

        @Override public Commands_Type associated_type()
        {

            throw new UnsupportedOperationException("Unimplemented method 'associated_type'");
        }

    }

    public static class Type_STATIC
            extends Commands_Fx
    {

        public Type_STATIC()
        {
            super(System.out);
        }

        @Override public String consume(String argument)
        {

            throw new UnsupportedOperationException("Unimplemented method 'consume'");
        }

        @Override public char[] sequence()
        {

            throw new UnsupportedOperationException("Unimplemented method 'sequence'");
        }

        @Override public Commands_Type associated_type()
        {

            throw new UnsupportedOperationException("Unimplemented method 'associated_type'");
        }

    }

    public enum Commands_Type {
        INFORMATIONAl, // just displays some text and does not require any input
        DYNAMIC_CONSUMER, // asks the user for input after the command is fully parsed
        HYBRID_CONSUMER, // both a static and dynamic consumer combined
        STATIC_CONSUMER; // the input is taken during the command parsing phase (in the format of
                         // something like --variable=13
    }

    private final Map< String, stl_Struct.struct_Pair< Commands_Type, stl_Callback< String, String > > > ARGUMENTS;

    public stl_Commands()
    {
        this(new HashMap<>());
    }

    public stl_Commands(
            Map< String, stl_Struct.struct_Pair< Commands_Type, stl_Callback< String, String > > > ARGUMENTS)
    {
        this.ARGUMENTS = ARGUMENTS;
    }

    public void add(String argumentName, Commands_Type type, stl_Callback< String, String > callback)
    {
        ARGUMENTS.put(argumentName,
                stl_Struct.make_pair(type == null ? Commands_Type.STATIC_CONSUMER : type, callback));
    }

    public void add(String argumentName, stl_Callback< String, String > callback)
    {
        add(argumentName, null, callback);
    }

    @Override public void run()
    {

    }
}
