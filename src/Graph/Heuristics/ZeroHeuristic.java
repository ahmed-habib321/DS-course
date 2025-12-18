package Graph.Heuristics;

public class ZeroHeuristic implements Heuristic {
    @Override
    public int estimate(int from, int to) {
        return 0; // A* behaves exactly like Dijkstra
    }
}
