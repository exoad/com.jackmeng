package com.jackmeng.stl;

public class stl_UnitTC
        implements Runnable
{
    private final String name;
    private final stl_Callback< ?, ? > action;
    private Integer expects;

    public stl_UnitTC(String name, stl_Callback< ?, ? > action)
    {
        this.name = name;
        this.action = action;
    }

    public stl_UnitTC(String name, stl_Callback< ?, ? > action, Integer expect)
    {
        this(name, action);
        expects = expect;
    }

    @Override
    public void run()
    {
        System.out.println("Testcase: " + name + "\n" + action.hashCode());
        long s = Runtime.getRuntime().maxMemory()
                - (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024,
                l = System.currentTimeMillis();
        Object returns;
        try
        {
            returns = action.call((null));
        } catch (Exception e)
        {
            System.out.println(new stl_AnsiMake(new stl_AnsiColors[] { stl_AnsiColors.GREEN_BG, stl_AnsiColors.BLACK_TXT },
                    new Object[] { "FAILED ON EXCEPTION" }));
            e.printStackTrace();
            return;
        }
        long t = System.currentTimeMillis() - l;
        System.out.println("Finished in: " + t + "ms\nMemory used: "
                + (Math.abs((Runtime.getRuntime().maxMemory()
                - ((double) Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024
                / 1024)
                - s))
                + " Mb"
                + (expects == null ? "\nCase Status: " + new stl_AnsiMake(
                new stl_AnsiColors[] { stl_AnsiColors.BLUE_BG, stl_AnsiColors.BLACK_TXT },
                new Object[] { "NONE" })
                : "\nCase status: " + ((returns.equals(expects)
                ? new stl_AnsiMake(new stl_AnsiColors[] { stl_AnsiColors.GREEN_BG, stl_AnsiColors.BLACK_TXT },
                new Object[] { "PASSED" })
                : new stl_AnsiMake(new stl_AnsiColors[] { stl_AnsiColors.RED_BG, stl_AnsiColors.BLACK_TXT },
                new Object[] { "FAILED" }))
                + "\nExpected: " + expects + "\nReturned: " + returns)));
    }
}