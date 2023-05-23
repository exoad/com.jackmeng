// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;

import com.jackmeng.stl.stl_Struct.struct_Pair;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;

public final class stl_SwingHelper
{

    private static final class $rr_corner_border_01
            implements
            Border
    {

        public final int radius, strokeThickness;
        public final Color color;
        public final Insets i;

        public $rr_corner_border_01(int radius, int thickness, Color color)
        {
            this(radius, thickness, color, null);
        }

        public $rr_corner_border_01(int radius, int thickness, Color c, Insets myInsets)
        {
            this.radius = radius;
            this.color = c;
            this.strokeThickness = thickness;
            this.i = myInsets == null
                    ? new Insets(Math.max(1, strokeThickness - 1), Math.max(1, strokeThickness - 1),
                            Math.max(1, strokeThickness - 1), Math.max(1, strokeThickness - 1))
                    : myInsets;
        }

        @Override public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
        {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setStroke(new BasicStroke(strokeThickness));
            g2.setColor(color);
            g2.drawRoundRect(x, y, width, height, radius, radius);
            g2.dispose();
        }

        @Override public Insets getBorderInsets(Component c)
        {
            return i;
        }

        @Override public boolean isBorderOpaque()
        {
            return true;
        }
    }

    private stl_SwingHelper()
    {
    }
    
    public static Border rrect_border_uniform(int round_factor, Color color, int thickness)
    {
        return new $rr_corner_border_01(round_factor, thickness, color);
    }

    public static Shape rrect_clip(int x, int y, int arc_w, int arc_h, int w, int h)
    {
        return new RoundRectangle2D.Float(x, y, w, h, arc_w, arc_h);
    }

    public static GraphicsDevice default_gdev()
    {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    }

    public static GraphicsConfiguration default_gconf()
    {
        return default_gdev().getDefaultConfiguration();
    }

    public static int acc_mem()
    {
        return default_gdev().getAvailableAcceleratedMemory();
    }

    public static stl_Struct.struct_Pair< JTree, Map< String, Component > > getComponentTreeWithInfo()
    {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Components");
        Map< String, Component > componentInfoMap = new HashMap<>();
        for (Window window : Window.getWindows())
            traverseComponentHierarchy(window, rootNode, componentInfoMap);
        JTree componentTree = new JTree(rootNode);
        componentTree.setRootVisible(false);
        return struct_Pair.make(componentTree, componentInfoMap);
    }

    private static void traverseComponentHierarchy(Component component, DefaultMutableTreeNode node,
            Map< String, Component > componentInfoMap)
    {
        DefaultMutableTreeNode componentNode = new DefaultMutableTreeNode(getComponentInfo(component));
        node.add(componentNode);
        componentInfoMap.put(component.getName(), component);
        if (component instanceof Container)
            for (Component child : ((Container) component).getComponents())
                traverseComponentHierarchy(child, componentNode, componentInfoMap);
    }

    private static String getComponentInfo(Component component)
    {
        String className = component.getClass().getSimpleName();
        String name = component.getName();
        Rectangle bounds = component.getBounds();
        Point location = component.getLocation();
        return className + " - " + name + " - x: " + location.x + ", y: " + location.y + ", width: " +
                bounds.width + ", height: " + bounds.height;
    }
}
