public class ArrayDeque<T> {
    private T items[];// The circular Array
    private int size; // size of the Deque
    private int len_array; // size of the array
    private int nextFirst; // index of next element in front
    private int nextLast; // index of next element at last

    public ArrayDeque() { // Constructor for ArrayDeque
        /* Create an empty list */
        items = (T []) new Object[8]; // casting
        size = 0;
        len_array = 8;
        nextFirst = 4;
        nextLast = 5;
    }
    private void resize(int cap) { // change the size by factor of 2
        T[] newArray = (T []) new Object[cap];
        int first_index = getFirst();
        int last_index = getLast();

        if (first_index < last_index) {
            System.arraycopy(items, first_index, newArray, 0, size);
        }
        else if (first_index > last_index) {
            System.arraycopy(items, first_index, newArray, 0, size - first_index);
            System.arraycopy(items, 0, newArray, size - first_index, last_index+1);
        }
        items = newArray;
        len_array = cap;
        nextFirst = len_array - 1;
        nextLast = size;
    }
    public void addFirst(T item) {
        /* Adds an item of type T to the front of the deque. */
        // 1. The index to add is always nextFirst 2. The next nextFirst is always minus 1
        items[nextFirst] = item;
        size += 1;
        nextFirst -= 1;

        if (nextFirst == -1) {
            nextFirst = len_array - 1;
        }
        if (size == len_array) {
            resize(2*len_array);
        }
    }
    public void addLast(T item){
        /* Adds an item of type T to the back of the deque. */
        // 1. The index to add is always nextLast 2. The next nextLast is always plus 1
        items[nextLast] = item;
        size += 1;
        nextLast += 1;

        if (nextLast == len_array) {
            nextLast = 0;
        }
        if (size == len_array) {
            resize(2*len_array);
        }
    }
    public boolean isEmpty(){
        /* Returns true if deque is empty, false otherwise. */
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public int size(){
        /* Returns the number of items in the deque. */
        return size;
    }
    public void printDeque(){
        /* Prints the items in the deque from first to last, separated by a space. */
        if (isEmpty() == false) {
            int index = getFirst();
            for (int i = 0; i < size; i++) {
                System.out.print(items[index] + " ");
                if (index + 1 == len_array){
                    index = 0;
                }
                else {
                    index += 1;
                }
            }
        }
            System.out.println();
    }
    public T removeFirst(){
        /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
        if (isEmpty() == false) {
            int index = getFirst();
            T first_item = items[index];
            items[index] = null;
            size -= 1;
            nextFirst = index;
            if (len_array >= 16 && size < len_array*0.25) {
                resize(len_array/2);
            }
            return first_item;
        }
        else {
            return null;
        }
    }
    public T removeLast(){
        /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
        if (isEmpty() == false) {
            int index = getLast();
            T last_item =  items[index];
            items[index] = null;
            size -= 1;
            nextLast = index;
            if (len_array >= 16 && size < len_array*0.25) {
                resize(len_array/2);
            }
            return last_item;
        }
        else {
            return null;
        }
    }
    public int getFirst() {
        int index = nextFirst + 1;
        if (index == len_array) {
            index = 0;
        }
        return index;
    }
    public int getLast() {
        int index = nextLast - 1;
        if (index == -1) {
            index = len_array - 1;
        }
        return index;
    }
    public T get(int index){
        /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. */
        if (size > 0 && index >= 0 && index < size) {
            T get_item = items[getFirst()+index];
            return get_item;
        }
        else {
            return null;
        }
    }
}
