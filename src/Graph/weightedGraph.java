package Graph;

import Graph.Heuristics.Heuristic;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class weightedGraph {

    // Weighted graph
    private final Map<Integer, List<Edge>> Graph = new HashMap<>();

    private void addVertex(int v) {
        Graph.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(int from, int to, int weight) {
        addVertex(from);
        addVertex(to);
        Graph.get(from).add(new Edge(to, weight));

        //add if you want it Undirected
        Graph.get(to).add(new Edge(from, weight));
    }

    public Map<Integer, List<Edge>> getGraph() {
        return Graph;
    }

    // ---------- BFS ----------
    public void BFS(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();

        visited.add(start);
        q.offer(start);

        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.print(v + " ");

            for (Edge e : Graph.getOrDefault(v, List.of())) {
                if (!visited.contains(e.to)) {
                    visited.add(e.to);
                    q.offer(e.to);
                }
            }
        }
    }

    // ---------- BFS SHORTEST PATH ----------
    public List<Integer> BFS_ShortestPath(int start, int end) {
        Map<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();

        q.offer(start);
        parent.put(start, null);

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == end) break;

            for (Edge e : Graph.getOrDefault(curr, List.of())) {
                if (!parent.containsKey(e.to)) {
                    parent.put(e.to, curr);
                    q.offer(e.to);
                }
            }
        }
        return buildPath(parent, start, end);
    }

    public void DFS(int start) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (!visited.contains(v)) {
                visited.add(v);
                System.out.print(v + " ");

                for (Edge e : Graph.getOrDefault(v, List.of())) {
                    stack.push(e.to);
                }
            }
        }
    }

    public List<Integer> DFS_ShortestPath(int start, int end) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();

        boolean found = dfsPathHelper(start, end, visited, parent);
        if (!found) return List.of();

        return buildPath(parent, start, end);
    }

    private boolean dfsPathHelper(int current,
                                  int end,
                                  Set<Integer> visited,
                                  Map<Integer, Integer> parent) {
        visited.add(current);

        if (current == end) return true;

        for (Edge e : Graph.getOrDefault(current, List.of())) {
            if (!visited.contains(e.to)) {
                parent.put(e.to, current);
                if (dfsPathHelper(e.to, end, visited, parent)) {
                    return true;
                }
            }
        }
        return false;
    }


    private List<Integer> buildPath(Map<Integer, Integer> parent, int start, int end) {
        List<Integer> path = new ArrayList<>();
        Integer cur = end;

        while (cur != null) {
            path.add(cur);
            cur = parent.get(cur);
        }
        Collections.reverse(path);
        return path.get(0) == start ? path : List.of();
    }

    // ---------- DIJKSTRA ----------
    public List<Integer> dijkstra_ShortestPath(int start, int end) {
        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int v : Graph.keySet()) {
            dist.put(v, Integer.MAX_VALUE);
        }

        dist.put(start, 0);
        parent.put(start, null);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.vertex == end) break;

            for (Edge e : Graph.getOrDefault(curr.vertex, List.of())) {
                int newDist = dist.get(curr.vertex) + e.weight;

                if (newDist < dist.get(e.to)) {
                    dist.put(e.to, newDist);
                    parent.put(e.to, curr.vertex);
                    pq.offer(new Node(e.to, newDist));
                }
            }
        }
        return buildPath(parent, start, end);
    }

    // ---------- A* SHORTEST PATH ----------
    public List<Integer> Astar_ShortestPath(int start,
                                            int end,
                                            Heuristic heuristic) {

        Map<Integer, Integer> gScore = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        PriorityQueue<Node> openSet = new PriorityQueue<>();

        for (int v : Graph.keySet()) {
            gScore.put(v, Integer.MAX_VALUE);
        }

        gScore.put(start, 0);
        parent.put(start, null);

        int hStart = heuristic.estimate(start, end);
        openSet.offer(new Node(start, 0, hStart));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.vertex == end) break;

            for (Edge e : Graph.getOrDefault(current.vertex, List.of())) {
                int tentativeG = gScore.get(current.vertex) + e.weight;

                if (tentativeG < gScore.get(e.to)) {
                    gScore.put(e.to, tentativeG);
                    parent.put(e.to, current.vertex);

                    int f = tentativeG + heuristic.estimate(e.to, end);
                    openSet.offer(new Node(e.to, tentativeG, f));
                }
            }
        }
        return buildPath(parent, start, end);
    }

    public void ShowGraph(String Name, Map<Integer, Point> Coords, List<Integer> Path) {
        JFrame frame = new JFrame(Name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new GraphPanel(Coords, getGraph(), Path));
        frame.setVisible(true);
    }
}
