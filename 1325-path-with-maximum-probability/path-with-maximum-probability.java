
import java.util.*;

class Solution {
    public double maxProbability(int n, int[][] edges,
                                 double[] succProb,
                                 int start, int end) {

        List<List<double[]>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double p = succProb[i];

            graph.get(u).add(new double[]{v, p});
            graph.get(v).add(new double[]{u, p});
        }

        double[] prob = new double[n];
        prob[start] = 1.0;

        PriorityQueue<double[]> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b[1], a[1])
        );

        pq.offer(new double[]{start, 1.0});

        while (!pq.isEmpty()) {
            double[] curr = pq.poll();

            int node = (int) curr[0];
            double probability = curr[1];

            if (probability < prob[node]) {
                continue;
            }

            if (node == end) {
                return probability;
            }

            for (double[] neighbor : graph.get(node)) {
                int next = (int) neighbor[0];
                double edgeProb = neighbor[1];

                double newProb = probability * edgeProb;

                if (newProb > prob[next]) {
                    prob[next] = newProb;
                    pq.offer(new double[]{next, newProb});
                }
            }
        }

        return 0.0;
    }
}
