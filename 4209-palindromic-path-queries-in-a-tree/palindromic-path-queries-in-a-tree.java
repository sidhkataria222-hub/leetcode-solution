
import java.util.*;

class Solution {
    private int[] head, to, next;
    private int[][] lift;
    private int[] first, last, depth;
    private int timer, maxPower;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        first = new int[n];
        last = new int[n];
        depth = new int[n];
        maxPower = 31 - Integer.numberOfLeadingZeros(n);
        lift = new int[maxPower + 1][n];

        int m = edges.length;
        head = new int[n];
        to = new int[2 * m];
        next = new int[2 * m];
        Arrays.fill(head, -1);

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            to[2 * i] = v;
            next[2 * i] = head[u];
            head[u] = 2 * i;

            to[2 * i + 1] = u;
            next[2 * i + 1] = head[v];
            head[v] = 2 * i + 1;
        }

        // Iterative DFS: compute Euler tour and ancestors.
        int[] nodeStack = new int[2 * n + 5];
        int[] parentStack = new int[2 * n + 5];
        int[] edgeStack = new int[2 * n + 5];

        int top = 0;
        nodeStack[top] = 0;
        parentStack[top] = -1;
        edgeStack[top++] = head[0];

        first[0] = ++timer;
        lift[0][0] = 0;

        while (top > 0) {
            int idx = top - 1;
            int u = nodeStack[idx];
            int e = edgeStack[idx];

            if (e == -1) {
                last[u] = timer;
                top--;
                continue;
            }

            edgeStack[idx] = next[e];
            int v = to[e];

            if (v == parentStack[idx]) {
                continue;
            }

            depth[v] = depth[u] + 1;
            lift[0][v] = u;

            for (int k = 1; k <= maxPower; k++) {
                lift[k][v] = lift[k - 1][lift[k - 1][v]];
            }

            first[v] = ++timer;

            nodeStack[top] = v;
            parentStack[top] = u;
            edgeStack[top] = head[v];
            top++;
        }

        Fenwick bit = new Fenwick(n + 1);
        char[] chars = s.toCharArray();

        for (int u = 0; u < n; u++) {
            int value = 1 << (chars[u] - 'a');
            bit.update(first[u], value);
            bit.update(last[u] + 1, value);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split("\\s+");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] != c) {
                    int diff = (1 << (chars[u] - 'a'))
                             ^ (1 << (c - 'a'));

                    bit.update(first[u], diff);
                    bit.update(last[u] + 1, diff);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int mask = bit.query(first[u])
                         ^ bit.query(first[v])
                         ^ (1 << (chars[ancestor] - 'a'));

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = maxPower; k >= 0; k--) {
            if ((diff & (1 << k)) != 0) {
                u = lift[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = maxPower; k >= 0; k--) {
            if (lift[k][u] != lift[k][v]) {
                u = lift[k][u];
                v = lift[k][v];
            }
        }

        return lift[0][u];
    }

    private static class Fenwick {
        private final int[] bit;
        private final int n;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 1];
        }

        void update(int index, int value) {
            for (int i = index; i <= n; i += i & -i) {
                bit[i] ^= value;
            }
        }

        int query(int index) {
            int result = 0;

            for (int i = index; i > 0; i -= i & -i) {
                result ^= bit[i];
            }

            return result;
        }
    }
}