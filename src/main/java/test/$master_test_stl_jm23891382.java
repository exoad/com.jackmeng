package test;

import com.jackmeng.stl.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

final class $master_test_stl_jm23891382
{
    static final class SplineDrawer
        extends JPanel
    {
        private final stl_Spline spline;

        public SplineDrawer(stl_Spline spline) {
            this.spline = spline;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            g.setColor(Color.BLACK);
            for (int x = 0; x < width; x++) {
                double t = (double) x / width;
                int y = (int) (height - spline.interpolate(t) * height);
                g.fillOval(x, y, 2, 2);
            }
        }
    }

    static final class BezierCurveDrawer
        extends JPanel
    {
        private static final int WIDTH = 800;
        private static final int HEIGHT = 800;
        private static final int CONTROL_POINT_RADIUS = 10;
        private static final int NUM_POINTS = 100;
        private static final Stroke CURVE_STROKE = new BasicStroke(2f);
        private static final Color CURVE_COLOR = Color.BLUE;
        private static final Color CONTROL_POINT_COLOR = Color.RED;

        private final java.util.List<Point> controlPoints;
        private final java.util.List<Point> curvePoints;

        public BezierCurveDrawer() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            controlPoints = new ArrayList<>();
            controlPoints.add(new Point(WIDTH / 4, HEIGHT / 2));
            controlPoints.add(new Point(WIDTH / 2, HEIGHT / 4));
            controlPoints.add(new Point(WIDTH * 3 / 4, HEIGHT / 2));

            curvePoints = new ArrayList<>();
            for (int i = 0; i <= NUM_POINTS; i++) {
                double t = (double) i / NUM_POINTS;
                curvePoints.add(calculateBezierPoint(t));
            }

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Point p = e.getPoint();
                    for (Point controlPoint : controlPoints) {
                        if (controlPoint.distance(p) <= CONTROL_POINT_RADIUS) {
                            controlPoint.x = p.x;
                            controlPoint.y = p.y;
                            curvePoints.clear();
                            for (int i = 0; i <= NUM_POINTS; i++) {
                                double t = (double) i / NUM_POINTS;
                                curvePoints.add(calculateBezierPoint(t));
                            }
                            repaint();
                            break;
                        }
                    }
                }
            });
        }

        private Point calculateBezierPoint(double t) {
            double x = (1 - t) * (1 - t) * controlPoints.get(0).x + 2 * (1 - t) * t * controlPoints.get(1).x + t * t *     controlPoints.get(2).x;
            double y = (1 - t) * (1 - t) * controlPoints.get(0).y + 2 * (1 - t) * t * controlPoints.get(1).y + t * t * controlPoints.get(2).y;
            return new Point((int)x, (int)y);
        }

        @Override
        public void paintComponent(Graphics g) {
            Point prev = calculateBezierPoint(0);
            for (double t = 0.01; t <= 1; t += 0.01) {
                Point curr = calculateBezierPoint(t);
                g.drawLine(prev.x, prev.y, curr.x, curr.y);
                prev = curr;
            }
        }
    }

    private $master_test_stl_jm23891382() {}
    public static void main(String[] args)
    {
        test_spline_drawing: {
            java.util.List<Double> x = new ArrayList<>();
            x.add(0.0);
            x.add(1.0);
            x.add(2.0);
            x.add(3.0);
            java.util.List<Double> y = new ArrayList<>();
            y.add(0.0);
            y.add(1.0);
            y.add(0.0);
            y.add(1.0);
            stl_Spline spline = new stl_Spline(x, y);
            JFrame frame = new JFrame("Spline Drawer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new SplineDrawer(spline));
            frame.setVisible(true);
        }

        test_bezier_drawing: {
            BezierCurveDrawer curveDrawer = new BezierCurveDrawer();

            JFrame frame = new JFrame("Bezier Curve");
            frame.add(curveDrawer);
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }
}
