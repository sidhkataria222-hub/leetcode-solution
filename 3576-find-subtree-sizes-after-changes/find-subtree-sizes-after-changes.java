//parent[i] batata hai ki node i ka parent kaun hai
//s[i] har node ka character hai.
//Har node ko apne nearest ancestor (upar wale node) ke paas move karna hai jiska character same ho.
//Agar same character wala ancestor nahi milta, toh node apne original parent ke paas hi rahega.import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<List<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            children.get(parent[i]).add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);

        dfsParent(0, children, s, last, newParent);

        
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            adj.get(newParent[i]).add(i);
        }

        int[] ans = new int[n];
        dfsSize(0, adj, ans);

        return ans;
    }

    private void dfsParent(int node, List<List<Integer>> children,
                          String s, int[] last, int[] newParent) {

        int ch = s.charAt(node) - 'a';
        int previous = last[ch];

        last[ch] = node;

        for (int child : children.get(node)) {
            int childChar = s.charAt(child) - 'a';

            if (last[childChar] != -1) {
                newParent[child] = last[childChar];
            }

            dfsParent(child, children, s, last, newParent);
        }

        
        last[ch] = previous;
    }

    private int dfsSize(int node, List<List<Integer>> adj, int[] ans) {
        ans[node] = 1;

        for (int child : adj.get(node)) {
            ans[node] += dfsSize(child, adj, ans);
        }

        return ans[node];
    }
}