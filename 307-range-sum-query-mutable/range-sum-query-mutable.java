//update(index,value)
//sumRange(left,right)
class NumArray {

    int[] tree;
    int n;

    public NumArray(int[] nums) {
        n = nums.length;

        tree = new int[4 * n];

        build(nums, 1, 0, n - 1);
    }

    
    void build(int[] nums, int node, int l, int r) {

        
        if (l == r) {
            tree[node] = nums[l];
            return;
        }

        int mid = (l + r) / 2;

    
        build(nums, node * 2, l, mid);

    
        build(nums, node * 2 + 1, mid + 1, r);

    
        tree[node] =
            tree[node * 2] + tree[node * 2 + 1];
    }

    
    public void update(int index, int val) {

        updateTree(1, 0, n - 1, index, val);
    }

    void updateTree(
        int node,
        int l,
        int r,
        int index,
        int val
    ) {

        // Reached the required index
        if (l == r) {
            tree[node] = val;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {

            updateTree(
                node * 2,
                l,
                mid,
                index,
                val
            );

        } else {

            updateTree(
                node * 2 + 1,
                mid + 1,
                r,
                index,
                val
            );
        }

        // Recalculate parent
        tree[node] =
            tree[node * 2] + tree[node * 2 + 1];
    }

    // Query range
    public int sumRange(int left, int right) {

        return query(
            1,
            0,
            n - 1,
            left,
            right
        );
    }

    int query(
        int node,
        int l,
        int r,
        int ql,
        int qr
    ) {

        // Completely inside query range
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        // Query only left side
        if (qr <= mid) {
            return query(
                node * 2,
                l,
                mid,
                ql,
                qr
            );
        }

        // Query only right side
        if (ql > mid) {
            return query(
                node * 2 + 1,
                mid + 1,
                r,
                ql,
                qr
            );
        }

    
        int leftSum = query(
            node * 2,
            l,
            mid,
            ql,
            qr
        );

        int rightSum = query(
            node * 2 + 1,
            mid + 1,
            r,
            ql,
            qr
        );

        return leftSum + rightSum;
    }
}