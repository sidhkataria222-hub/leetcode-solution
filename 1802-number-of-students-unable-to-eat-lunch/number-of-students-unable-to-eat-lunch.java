
import java.util.*;

class Solution {
    public int countStudents(int[] students, int[] sandwiches) {

        Queue<Integer> q = new LinkedList<>();

    
        for (int s : students) {
            q.add(s);
        }

        int index = 0;
        int count = 0;

        while (!q.isEmpty()) {

            if (q.peek() == sandwiches[index]) {

                q.poll();
                index++;
                count = 0;

            } else {

                q.add(q.poll());
                count++;
            }

        
            if (count == q.size()) {
                break;
            }
        }

        return q.size();
    }
}
