package com.jackmeng.stl.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ux_Combos {

    public static final ux_Combos REGISTRY = new ux_Combos();

    private final Map<List<ux_Key>, Runnable> KEY_COMBOS;

    private ux_Combos()
    {
        KEY_COMBOS = new HashMap<>();
    }
}
