public class UnionFind4 implements UF {

    private int[] parent;

    //rank[i] 表示以i为根的集合中元素高度
    private int[] rank;

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查看元素p和元素q是否所属同一集合
    // O(h)复杂度，h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        //根据两个元素所在树的rank不同判断合并方向
        //rank低的集合合并到rank高的集合上
        if (rank[pRoot] < rank[qRoot])
            //pRoot这棵树合并后的高度最多和qRoot相等，qRoot高度不变，不需要维护rank
            parent[pRoot] = qRoot;
        else if (rank[qRoot] < rank[pRoot])
            parent[qRoot] = pRoot;
        else {
            //高度相等，谁指向谁都可以，但是要维护高度
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }

    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查询根节点，查找元素p所对应的集合编号
    // O(h)复杂度，h为树的高度
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        while (p != parent[p])
            p = parent[p];

        return p;
    }
}
