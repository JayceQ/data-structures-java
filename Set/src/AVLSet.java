public class AVLSet<E extends Comparable<E>> implements Set<E> {

    private AVLTree<E, ?> avl;

    public AVLSet() {
        avl = new AVLTree<>();
    }

    @Override
    public void add(E e) {
        avl.add(e, null);
    }

    @Override
    public void remove(E e) {
        avl.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }

    @Override
    public int size() {
        return avl.size();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
