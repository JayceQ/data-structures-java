/**
 * LeetCode No.307
 */
public class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data, Integer::sum);
        }
    }

    public void update(int i, int val) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null.");
        }
        segmentTree.set(i, val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null.");
        }
        return segmentTree.query(i, j);
    }

    private class SegmentTree<E> {

        private E[] tree;
        private E[] data;
        private Merger<E> merger;

        public SegmentTree(E[] arr, Merger<E> merger) {
            this.merger = merger;
            data = (E[]) new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                data[i] = arr[i];
            }
            tree = (E[]) new Object[4 * arr.length];
            buildSegmentTree(0, 0, data.length - 1);
        }

        //在treeIndex的位置创建表示区间[l...r]的线段树
        private void buildSegmentTree(int treeIndex, int l, int r) {
            if (l == r) {
                tree[treeIndex] = data[l];
                return;
            }
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            int mid = l + (r - l) / 2;
            buildSegmentTree(leftTreeIndex, l, mid);
            buildSegmentTree(rightTreeIndex, mid + 1, r);

            //tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
            tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        }

        public E get(int index) {
            if (index < 0 || index >= data.length) {
                throw new IllegalArgumentException("Index is illegal.");
            }
            return data[index];
        }

        public int size() {
            return data.length;
        }

        private int leftChild(int index) {
            return 2 * index + 1;
        }

        private int rightChild(int index) {
            return 2 * index + 2;
        }

        public E query(int queryL, int queryR) {
            if (queryL < 0 || queryL >= data.length ||
                    queryR < 0 || queryR >= data.length || queryL > queryR) {
                throw new IllegalArgumentException("Index is illegal.");
            }
            return query(0, 0, data.length - 1, queryL, queryR);
        }

        //在以treeID为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
        //treeIndex所在的区间范围[l...r]，可以包装为线段树的节点类
        private E query(int treeIndex, int l, int r, int queryL, int queryR) {
            //左右边界刚好与当前treeIndex节点相等
            if (l == queryL && r == queryR) {
                return tree[treeIndex];
            }

            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            if (queryL >= mid + 1)//需要查询的左边界刚好落在右孩子
                return query(rightTreeIndex, mid + 1, r, queryL, queryR);
            else if (queryR <= mid)//需要查询的右边界刚好落在左孩子
                return query(leftTreeIndex, l, mid, queryL, queryR);

            //左边界在左孩子
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            //右边界在右孩子
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }

        public void set(int index, E e) {
            if (index < 0 || index >= data.length) {
                throw new IllegalArgumentException("Index is illegal.");
            }

            data[index] = e;
            set(0, 0, data.length - 1, index, e);
        }

        //logN
        private void set(int treeIndex, int l, int r, int index, E e) {
            if (l == r) {
                tree[treeIndex] = e;
                return;
            }
            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            if (index >= mid + 1)
                set(rightTreeIndex, mid + 1, r, index, e);
            else// <= mid
                set(leftTreeIndex, l, mid, index, e);
            tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        }

        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append('[');
            for (int i = 0; i < tree.length; i++) {
                if (tree[i] != null) {
                    res.append(tree[i]);
                } else {
                    res.append("null");
                }
                if (i != tree.length - 1)
                    res.append(", ");
            }
            res.append(']');
            return res.toString();
        }
    }

    private interface Merger<E> {
        E merge(E a, E b);
    }

}
