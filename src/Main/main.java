/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Graph.*;
import Graph.Heuristics.CustomHeuristic;
import Graph.Heuristics.EuclideanHeuristic;
import Graph.Heuristics.ManhattanHeuristic;
import Graph.Heuristics.ZeroHeuristic;

import javax.swing.*;
import java.awt.*;
import java.util.Map;


/**
 *
 * @author Ahmed
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(25);
        bst.insert(30);
        bst.insert(18);
        bst.insert(40);
        bst.insert(28);
        bst.insert(20);
        bst.insert(13);
        bst.insert(50);
        bst.insert(38);
        System.out.println(bst.height());
        System.out.println(bst.isComplete());
    */

        weightedGraph g = new weightedGraph();
        g.addEdge(1, 2,60);
        g.addEdge(1, 3,1000);
        g.addEdge(1, 4,50);
        g.addEdge(2, 3,10);
        g.addEdge(4, 3,300);
        g.addEdge(3, 5,20);

        Map<Integer, Point> nodes = Map.of(
                1, new Point(300, 100),
                2, new Point(300, 300),
                3, new Point(100, 300),
                4, new Point(100, 100),
                5, new Point(50, 350)
        );
/*
        g.BFS(1);
        System.out.println();
        g.DFS(1);


        List<Integer> dfsSearchPath = g.DFS_ShortestPath(1, 5);
        List<Integer> bfsShortestPath = g.BFS_ShortestPath(1, 5);
        List<Integer> dijkstra = g.dijkstra_ShortestPath(1, 5);

        List<Integer> a_star = g.Astar_ShortestPath(1, 5,new EuclideanHeuristic(nodes));

        g.ShowGraph("DFS", nodes, dfsSearchPath);
        g.ShowGraph("BFS", nodes, bfsShortestPath);
        g.ShowGraph("dijkstra", nodes, dijkstra);
        g.ShowGraph("A*", nodes, a_star);
*/


        AnimatedGraphPanel panel = new AnimatedGraphPanel(nodes, g.getGraph());

        JFrame frame = new JFrame("Animated Graph Algorithms");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(panel);
        frame.setVisible(true);

        // Control panel
        JPanel controls = new JPanel();
        JButton bfsBtn = new JButton("BFS");
        JButton dfsBtn = new JButton("DFS");
        JButton dijkstraBtn = new JButton("Dijkstra");
        JButton aStarBtn = new JButton("A*");
        JButton pauseBtn = new JButton("Pause");
        JButton playBtn = new JButton("Play");
        JButton resetBtn = new JButton("Reset");

        controls.add(bfsBtn);
        controls.add(dfsBtn);
        controls.add(dijkstraBtn);
        controls.add(aStarBtn);
        controls.add(pauseBtn);
        controls.add(playBtn);
        controls.add(resetBtn);

        frame.add(controls, BorderLayout.SOUTH);

        bfsBtn.addActionListener(e -> panel.animateBFS(1));
        dfsBtn.addActionListener(e -> panel.animateDFS(1));
        dijkstraBtn.addActionListener(e -> panel.animateDijkstra(1));

        aStarBtn.addActionListener(e -> panel.animateAStar(1, 5, new EuclideanHeuristic(nodes)));
        pauseBtn.addActionListener(e -> panel.pause());
        playBtn.addActionListener(e -> panel.play());
        resetBtn.addActionListener(e -> panel.reset());

        frame.revalidate();

    }

}
