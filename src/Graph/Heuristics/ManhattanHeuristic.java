package Graph.Heuristics;

import java.awt.Point;
import java.util.Map;

public class ManhattanHeuristic implements Heuristic {

    private final Map<Integer, Point> positions;

    public ManhattanHeuristic(Map<Integer, Point> positions) {
        this.positions = positions;
    }

    @Override
    public int estimate(int from, int to) {
        Point p1 = positions.get(from);
        Point p2 = positions.get(to);
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}