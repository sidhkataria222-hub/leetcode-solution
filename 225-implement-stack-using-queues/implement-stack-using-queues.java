import java.util.*;

class MyStack {
    Queue<Integer> q = new ArrayDeque<>();

    public MyStack() {

    }

    public void push(int x) {
        q.add(x);

        int size = q.size();

        
        for (int i = 0; i < size - 1; i++) {
            q.add(q.remove());
        }
    }

    public int pop() {
        return q.remove();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}