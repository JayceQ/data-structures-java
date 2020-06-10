public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;//添加的节点一定是和某个节点融合
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    //像红黑树中添加新的元素
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    //      node                     x
    //      /  \      左旋转        /  \
    //    T1    x  ---------->   node   T3
    //         /  \              /  \
    //       T2   T3           T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;

        //左旋转
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    //     node                    x
    //     /  \      右旋转        / \
    //    x    T2  ---------->   y  node
    //   /  \                       /  \
    //  y   T1                    T1   T2
    private Node rightRotate(Node node) {
        Node x = node.left;

        //右旋转
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    //颜色翻转
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //向以node为根的红黑树中插入元素(key,value)，递归算法
    //返回插入新节点后红黑树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            //key.compareTo(node.key == 0 如果是重复元素,替换掉
            node.value = value;
        }
        return node;
    }


    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
        }
        return null;
    }

    //删除以node为根的二分搜索树中键盘为key的节点，递归算法
    //返回删除节点新的二分搜索树的根
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {//key == node.key
            if (node.left == null) {
                Node rn = node.right;
                node.right = null;
                size--;
                return rn;
            }
            if (node.right == null) {
                Node ln = node.left;
                node.left = null;
                size--;
                return ln;
            }
            //待删除绩点左右子树均不为空，
            //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            //用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    //删除掉node为根的二分搜索树中的最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rn = node.right;
            node.right = null;
            size--;
            return rn;
        }
        node.left = removeMin(node.left);
        return node;
    }


    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }


    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        } else {
            node.value = value;
        }
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
