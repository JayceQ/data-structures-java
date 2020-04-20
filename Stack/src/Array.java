public class Array<E> {

    private E[] data;

    private int size;

    @SuppressWarnings("unchecked")
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Add an element to the array at the index position
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >=0 and index <= size.");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }


    public void addFirst(E e) {
        add(0, e);
    }

    E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        data[index] = e;
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //data[size] can not be visited, need be released to ensure garbage reclaiming，
        //loitering object is not memory leak
        data[size] = null;

        //lazy strategy to prevent complexity oscillation
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public boolean removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public void removeAllElement(E e) {
        boolean flag = true;
        while (flag) {
            flag = removeElement(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d \n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }


    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
