
package br.com.mariojp.figureeditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

class DrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_SIZE = 60;
    private final List<Shape> shapes = new ArrayList<>();
    private Point startDrag = null;
    private Point currentDrag = null;

    DrawingPanel() {
        
        setBackground(Color.WHITE);
        setOpaque(true);
        setDoubleBuffered(true);

        var mouse = new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
               startDrag = e.getPoint();
               currentDrag = null;
                }

                @Override
                public void mouseReleased(MouseEvent e){
                    if (startDrag != null){
                        int x = Math.min(startDrag.x, e.getX());
                        int y = Math.min(startDrag.y, e.getY());
                        int w = Math.abs(e.getX() - startDrag.x);
                        int h = Math.abs(e.getY() - startDrag.y);

                        Shape s;
                        if (w < 5 || h <5) {
                            int size = DEFAULT_SIZE;
                            s = new Ellipse2D.Double(e.getX(), e.getY(), size, size);
                        }else{
                            s = new Rectangle2D.Double(x,y,w,h);
                        }
                        shapes.add(s);
                        startDrag = null;
                        currentDrag = null;
                        repaint();
                    }
                }
            @Override
            public void mouseDragged(MouseEvent e){
                currentDrag = e.getPoint();
                repaint();
            }
        };
        addMouseListener(mouse);        
        addMouseMotionListener(mouse);

    }

    void clear() {
        shapes.clear();
        repaint();
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Shape s : shapes) {
            g2.setColor(new Color(30,144,255));
            g2.fill(s);
            g2.setColor(new Color(0,0,0,70));
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(s);
        }

        if (startDrag != null && currentDrag != null){
            int x = Math.min(startDrag.x, currentDrag.x);
            int y = Math.min(startDrag.y, currentDrag.y);
            int w = Math.abs(currentDrag.x - startDrag.x);
            int h = Math.abs(currentDrag.y - startDrag.y);

            g2.setColor(Color.GRAY);
            float dash[] = {5.0f};
            g2.setStroke(new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_BEVEL,
            0,dash,0));
            g2.drawRect(x,y,w,h);
        }
        g2.dispose();
    }
}

