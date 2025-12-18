package Graph;

import Graph.Heuristics.Heuristic;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AnimatedGraphPanel extends JPanel {

    private final Map<Integer, Point> nodes;
    private final Map<Integer, List<Edge>> graph;

    private final Map<Integer, NodeState> states = new HashMap<>();
    private List<Integer> shortestPath = List.of();

    private Timer timer;
    private Queue<Integer> queue = new LinkedList<>();
    private Stack<Integer> stack = new Stack<>();
    private PriorityQueue<Node> pq;

    private boolean paused = false;

    public AnimatedGraphPanel(Map<Integer, Point> nodes,
                              Map<Integer, List<Edge>> graph) {
        this.nodes = nodes;
        this.graph = graph;
        nodes.keySet().forEach(v -> states.put(v, NodeState.UNVISITED));
    }

    // ---------- PLAY / PAUSE / RESET ----------
    public void play() { paused = false; }
    public void pause() { paused = true; }
    public void reset() {
        nodes.keySet().forEach(v -> states.put(v, NodeState.UNVISITED));
        shortestPath = List.of();
        queue.clear();
        stack.clear();
        pq = null;
        repaint();
    }

    // ---------- BFS ----------
    public void animateBFS(int start) {
        reset();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);

        timer = new Timer(500, e -> {
            if (!paused) {
                if (!queue.isEmpty()) {
                    int current = queue.poll();
                    states.put(current, NodeState.VISITING);
                    repaint();

                    for (Edge edge : graph.getOrDefault(current, List.of())) {
                        if (!visited.contains(edge.to)) {
                            visited.add(edge.to);
                            queue.offer(edge.to);
                        }
                    }

                    states.put(current, NodeState.VISITED);
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    // ---------- DFS (stack-based) ----------
    public void animateDFS(int start) {
        reset();
        Set<Integer> visited = new HashSet<>();
        stack.push(start);

        timer = new Timer(500, e -> {
            if (!paused) {
                if (!stack.isEmpty()) {
                    int current = stack.pop();
                    if (!visited.contains(current)) {
                        visited.add(current);
                        states.put(current, NodeState.VISITING);
                        repaint();

                        // Push neighbors in reverse order for natural order
                        List<Edge> neighbors = graph.getOrDefault(current, List.of());
                        Collections.reverse(neighbors);
                        for (Edge edge : neighbors) {
                            if (!visited.contains(edge.to)) {
                                stack.push(edge.to);
                            }
                        }

                        states.put(current, NodeState.VISITED);
                    }
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    // ---------- Dijkstra ----------
    public void animateDijkstra(int start) {
        reset();
        Map<Integer, Integer> dist = new HashMap<>();
        pq = new PriorityQueue<>();
        Map<Integer, Integer> parent = new HashMap<>();

        for (int v : graph.keySet()) dist.put(v, Integer.MAX_VALUE);
        dist.put(start, 0);
        parent.put(start, null);
        pq.offer(new Node(start, 0, 0)); // gCost = 0, fCost ignored

        timer = new Timer(500, e -> {
            if (!paused) {
                if (!pq.isEmpty()) {
                    Node current = pq.poll();
                    states.put(current.vertex, NodeState.VISITING);
                    repaint();

                    for (Edge edge : graph.getOrDefault(current.vertex, List.of())) {
                        int newDist = dist.get(current.vertex) + edge.weight;
                        if (newDist < dist.get(edge.to)) {
                            dist.put(edge.to, newDist);
                            parent.put(edge.to, current.vertex);
                            pq.offer(new Node(edge.to, newDist, 0));
                        }
                    }

                    states.put(current.vertex, NodeState.VISITED);
                } else {
                    ((Timer) e.getSource()).stop();
                    // After finishing, show shortest path to max dist node
                    shortestPath = buildPath(parent, start);
                    shortestPath.forEach(v -> states.put(v, NodeState.PATH));
                    repaint();
                }
            }
        });
        timer.start();
    }

    // ---------- A* ----------
    public void animateAStar(int start, int goal, Heuristic heuristic) {
        reset();
        Map<Integer, Integer> gScore = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        pq = new PriorityQueue<>();

        for (int v : graph.keySet()) gScore.put(v, Integer.MAX_VALUE);
        gScore.put(start, 0);
        parent.put(start, null);
        pq.offer(new Node(start, 0, heuristic.estimate(start, goal)));

        timer = new Timer(500, e -> {
            if (!paused) {
                if (!pq.isEmpty()) {
                    Node current = pq.poll();
                    states.put(current.vertex, NodeState.VISITING);
                    repaint();
                    if (current.vertex == goal) {
                        ((Timer) e.getSource()).stop();
                        shortestPath = buildPath(parent, start, goal);
                        shortestPath.forEach(v -> states.put(v, NodeState.PATH));
                        repaint();
                        return;
                    }

                    for (Edge edge : graph.getOrDefault(current.vertex, List.of())) {
                        int tentativeG = gScore.get(current.vertex) + edge.weight;
                        if (tentativeG < gScore.get(edge.to)) {
                            gScore.put(edge.to, tentativeG);
                            parent.put(edge.to, current.vertex);
                            int f = tentativeG + heuristic.estimate(edge.to, goal);
                            pq.offer(new Node(edge.to, tentativeG, f));
                        }
                    }

                    states.put(current.vertex, NodeState.VISITED);
                }
            }
        });
        timer.start();
    }

    // ---------- PATH BUILDER ----------
    private List<Integer> buildPath(Map<Integer, Integer> parent, int start) {
        List<Integer> path = new ArrayList<>();
        Integer curr = null;
        // pick farthest node for Dijkstra or goal for A*
        if (parent.containsKey(start)) curr = parent.keySet().stream().reduce((first, second) -> second).orElse(start);
        while (curr != null) {
            path.add(curr);
            curr = parent.get(curr);
        }
        Collections.reverse(path);
        return path;
    }

    private List<Integer> buildPath(Map<Integer, Integer> parent, int start, int end) {
        List<Integer> path = new ArrayList<>();
        Integer curr = end;
        while (curr != null) {
            path.add(curr);
            curr = parent.get(curr);
        }
        Collections.reverse(path);
        return path.get(0) == start ? path : List.of();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw edges
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

        // Draw nodes with colors
        for (int v : nodes.keySet()) {
            Point p = nodes.get(v);
            switch (states.get(v)) {
                case UNVISITED -> g2.setColor(Color.CYAN);
                case VISITING -> g2.setColor(Color.ORANGE);
                case VISITED -> g2.setColor(Color.GREEN);
                case PATH -> g2.setColor(Color.RED);
            }
            g2.fillOval(p.x - 15, p.y - 15, 30, 30);
            g2.setColor(Color.BLACK);
            g2.drawString(String.valueOf(v), p.x - 5, p.y + 5);
        }
    }
}