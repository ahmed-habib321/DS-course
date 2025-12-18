package Graph;

public class Node implements Comparable<Node> {
    int vertex;
    int gCost;   // cost so far
    int fCost;   // g + h

    public Node(int v, int g, int f) {
        vertex = v;
        gCost = g;
        fCost = f;
    }

    public Node(int v, int dist) {
        vertex = v;
        gCost = dist;
        fCost = dist;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.fCost, other.fCost);
    }
}
