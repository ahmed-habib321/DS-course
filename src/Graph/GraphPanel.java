package Graph;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;


public class GraphPanel extends JPanel {
    private final Map<Integer, Point> nodes;
    private final Map<Integer, List<Edge>> graph;
    private final List<Integer> path;

    public GraphPanel(Map<Integer, Point> nodes,
                      Map<Integer, List<Edge>> graph,
                      List<Integer> path) {
        this.nodes = nodes;
        this.graph = graph;
        this.path = path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw edges + weights
        g2.setColor(Color.GRAY);
        for (int from : graph.keySet()) {
            for (Edge e : graph.get(from)) {
                Point p1 = nodes.get(from);
                Point p2 = nodes.get(e.to);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);

                int mx = (p1.x + p2.x) / 2;
                int my = (p1.y + p2.y) / 2;
                g2.drawString(String.valueOf(e.weight), mx, my);
            }
        }

        // Highlight shortest path
        g2.setColor(Color.RED);
        for (int i = 0; i < path.size() - 1; i++) {
            Point p1 = nodes.get(path.get(i));
            Point p2 = nodes.get(path.get(i + 1));
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Draw nodes
        for (int v : nodes.keySet()) {
            Point p = nodes.get(v);
            g2.setColor(Color.CYAN);
            g2.fillOval(p.x - 15, p.y - 15, 30, 30);
            g2.setColor(Color.BLACK);
            g2.drawString(String.valueOf(v), p.x - 5, p.y + 5);
        }
    }
}
