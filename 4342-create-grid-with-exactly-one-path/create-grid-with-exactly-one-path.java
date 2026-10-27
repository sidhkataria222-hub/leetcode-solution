import java.util.*;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        // Fill the entire grid with obstacles
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        // Make the first row free
        Arrays.fill(grid[0], '.');

        // Make the last column free
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        // Convert the grid into strings
        String[] ans = new String[m];

        for (int i = 0; i < m; i++) {
            ans[i] = new String(grid[i]);
        }

        return ans;
    }
}