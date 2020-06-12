import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {


    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    //向以node为根的二分搜索树中插入元素(key,value)，递归算法
    //返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            //key.compareTo(node.key == 0 如果是重复元素,替换掉
            node.value = value;

        //更新height
        //TODO 优化：如果重新计算后的高度和这个节点之前的高度相等，后续对这个节点以及祖先节点则不需要维护平衡
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        //插入的元素在不平衡的节点左侧的左侧(LL)，此时右旋转
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);
        //插入的元素在不平衡的节点右侧的右侧(RR)，此时左旋转
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);
        //插入的元素在不平衡的节点左侧的右侧(LR)，先对左孩子左旋转化为LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // T1 < x < T2 < z < T3 < Y < T4
            //        y                        y                          z
            //       / \                      / \                        /  \
            //      x   T4   向左旋转 (x)     z   T4   向右旋转 (y)       x     y
            //     / \      - - - - - - ->  /\       - - - - - - ->    / \   / \
            //    T1  z                    x  T3                     T1  T2 T3  T4
            //       / \                  / \
            //      T2  T3               T1  T2
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //插入的元素在不平衡的节点右侧的左侧(RL)，先对右孩子右旋转化为RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            // T1 < y < T2 < z < T3 < x < T4
            //        y                        y                          z
            //       / \                      / \                        /  \
            //     T1   x    向右旋转 (x)     T1   z   向左旋转 (y)       y     x
            //         / \  - - - - - - ->       / \  - - - - - - ->   / \   / \
            //        z   T4                    T2  x                T1  T2 T3  T4
            //       / \                           / \
            //     T2  T3                        T3  T4
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    // T1 < z < T2 < x < T3 < y < T4
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        //向右旋转
        x.right = y;
        y.left = T3;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
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
        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {//key == node.key
            if (node.left == null) {
                Node rn = node.right;
                node.right = null;
                size--;
                retNode = rn;
            } else if (node.right == null) {
                Node ln = node.left;
                node.left = null;
                size--;
                retNode = ln;
            } else {
                //待删除绩点左右子树均不为空，
                //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
                //用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                //未做平衡处理
                //successor.right = removeMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        if (retNode == null) {
            return null;
        }
        //更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        //插入的元素在不平衡的节点左侧的左侧(LL)，此时右旋转
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);
        //插入的元素在不平衡的节点右侧的右侧(RR)，此时左旋转
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);
        //插入的元素在不平衡的节点左侧的右侧(LR)，先对左孩子左旋转化为LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            node.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //插入的元素在不平衡的节点右侧的左侧(RL)，先对右孩子右旋转化为RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
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

    //判断该二叉树是否是一颗二分搜索树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null)
            return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //判断以Node为根的二叉树是否是一颗平衡二叉树，递归算法
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null)
            return true;
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }
}

