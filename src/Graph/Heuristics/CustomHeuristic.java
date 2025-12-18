package Graph.Heuristics;

import java.util.Map;

public class CustomHeuristic implements Heuristic {

    private final Map<Integer, Integer> nodeWeights;

    public CustomHeuristic(Map<Integer, Integer> nodeWeights) {
        this.nodeWeights = nodeWeights;
    }

    @Override
    public int estimate(int from, int to) {
        // Example: higher weight = less desirable
        return nodeWeights.getOrDefault(to, 0);
    }
}

/*
* Add this to the run
*
* Map<Integer, Integer> nodeScores = Map.of(1,0,2,5,3,2,4,1);
*
* */