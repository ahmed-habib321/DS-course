package Graph.Heuristics;

import java.awt.Point;
import java.util.Map;

public class EuclideanHeuristic implements Heuristic {

    private final Map<Integer, Point> positions;

    public EuclideanHeuristic(Map<Integer, Point> positions) {
        this.positions = positions;
    }

    @Override
    public int estimate(int from, int to) {
        Point p1 = positions.get(from);
        Point p2 = positions.get(to);

        return (int) Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }
}