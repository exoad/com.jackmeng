package com.jackmeng.stl;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple class to help with parsing String[] args or String... args
 *
 * @author Jack Meng
 */
public class stl_Commands
        implements
        Runnable,
        stl_Callback< String, String >
{
    private abstract static class Commands_Fx
            implements stl_Callback< String, String >
    {
        private boolean finished = false;
        private final String properName, commandName;

        /**
         * <p>
         * Constructs the necessary Command Function Object. There is also a separate
         * constructor {@link #stl_Commands()} which inputs a null String as the default
         * name <b>("DO NOT USE").
         *
         * @param properName
         *            The properName is the string used in the real command argument. It
         *            should preferably be all lowercase with underscores or all
         *            uppercase.
         * @param commandName
         *            The commandName is the string used in the readable definitions and
         *            manual definitions of the command. It should not be used for
         *            anything else.
         */
        public Commands_Fx(String properName, String commandName)
        {
            this.properName = properName;
            this.commandName = commandName;
        }

        public Commands_Fx()
        {
            this("\0", "\0");
        }

        public String properName()
        {
            return properName;
        }

        public String commandName()
        {
            return commandName;
        }

        public boolean matches(String input)
        {
            return properName.equalsIgnoreCase(input);
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
        public abstract String sequence();

        @Override public String call(String argument)
        {
            return consume(argument);
        }

        /**
         * Should tell the original stl_Commands if this current consumer function
         * has done its job.
         *
         * @return (true -> done >|| false -> not done)
         */
        public boolean finished()
        {
            return finished;
        }

        public abstract Commands_Type[] associated_types();
    }

    public static class Type_INFORMATIONAL
            extends Commands_Fx
    {
        private final String text;

        public Type_INFORMATIONAL(String text)
        {
            this.text = text;
        }

        public String text()
        {
            return text;
        }

        @Override public String consume(String argument)
        {
            return this.text;
        }

        @Override public String sequence()
        {
            return "--";
        }

        @Override public Commands_Type[] associated_types()
        {
            return new Commands_Type[] { Commands_Type.INFORMATIONAl };
        }

    }

    public static final class Type_DYNAMIC
            extends Commands_Fx
    {

        public Type_DYNAMIC()
        {
        }

        @Override public String consume(String argument)
        {

            throw new UnsupportedOperationException("Unimplemented method 'consume'");
        }

        @Override public String sequence()
        {
            return "--";
        }

        @Override public Commands_Type[] associated_types()
        {

            throw new UnsupportedOperationException("Unimplemented method 'associated_type'");
        }

    }

    public static final class Type_HYBRID
            extends Commands_Fx
    {

        public Type_HYBRID()
        {
        }

        @Override public String consume(String argument)
        {

            throw new UnsupportedOperationException("Unimplemented method 'consume'");
        }

        @Override public String sequence()
        {
            return "--";
        }

        @Override public Commands_Type[] associated_types()
        {

            throw new UnsupportedOperationException("Unimplemented method 'associated_type'");
        }

    }

    public static class Type_STATIC
            extends Commands_Fx
    {

        public Type_STATIC()
        {
        }

        @Override public String consume(String argument)
        {

            throw new UnsupportedOperationException("Unimplemented method 'consume'");
        }

        @Override public String sequence()
        {
            return "--";
        }

        @Override public Commands_Type[] associated_types()
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

    private final Map< String, ? super stl_Commands.Commands_Fx > ARGUMENTS;

    public stl_Commands()
    {
        this(new HashMap<>());
    }

    public stl_Commands(
            Map< String, stl_Commands.Commands_Fx > ARGUMENTS)
    {
        this.ARGUMENTS = ARGUMENTS;
    }

    public void add(String argumentName, Commands_Type type, stl_Callback< String, String > callback)
    {
        add(argumentName, new Type_INFORMATIONAL(callback.call((String) null)));
    }

    public void add(String argumentName, stl_Commands.Commands_Fx e)
    {
        this.ARGUMENTS.put(argumentName, e);
    }

    @Override public void run()
    {

    }

    @Override public String call(String arg)
    {
        return "";
    }
}
