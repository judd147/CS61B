public class LinkedListDeque<T> {
    private Node sentinel; // The dummy node for a circular linked list
    private int size;
    public class Node {
        public T item;
        public Node prev;
        public Node next;
        public Node(T i, Node p, Node n) { // Constructor for Node
            item = i;
            prev = p;
            next = n;
        }
    }
    public LinkedListDeque() { // Constructor for LinkedListDeque
        /* Create an empty list */
        sentinel = new Node(null,null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        /* Adds an item of type T to the front of the deque. */
        // 1. The first Node's prev is always sentinel 2. Its next is always sentinel.next(original first item)
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode; // reassign original first's prev pointer
        sentinel.next = newNode; // reassign sentinel's next pointer
        size += 1;
    }
    public void addLast(T item){
        /* Adds an item of type T to the back of the deque. */
        // 1. The last Node's next is always sentinel 2. Its prev is always previously the end of list(requires traverse)
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode; // reassign original last's next pointer
        sentinel.prev = newNode; // reassign sentinel's prev pointer
        size += 1;
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
            Node ptr = sentinel.next;
            while (ptr != sentinel) {
                System.out.print(ptr.item + " ");
                ptr = ptr.next;
            }
            System.out.println();
        }
    }
    public T removeFirst(){
        /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
        // 1. The first Node's prev is always sentinel 2. Its next is always sentinel.next(original first item)
        if (isEmpty() == false) {
            T first_item = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return first_item;
        }
        else {
            return null;
        }
    }
    public T removeLast(){
        /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
        // 1. The last Node's next is always sentinel 2. Its prev is always previously the end of list(requires traverse)
        if (isEmpty() == false) {
            T last_item = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return last_item;
        }
        else {
            return null;
        }
    }
    public T get(int index){
        /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. */
        if (size > 0 && index >= 0 && index < size) {
            Node ptr = sentinel;
            for (int i = 0; i <= index; i++) {
                ptr = ptr.next;
            }
            T get_item = ptr.item;
            return get_item;
        }
        else {
            return null;
        }
    }
    public T getRecursive(int index) {
        /* Same as get, but uses recursion. */
        if (size > 0 && index >= 0 && index < size) {
            Node ptr = sentinel.next;
            return getRecursiveHelper(ptr, index);
        }
        else {
            return null;
        }
    }
    public T getRecursiveHelper(Node ptr, int index) {
        if (index == 0) {
            return ptr.item;
        }
        return getRecursiveHelper(ptr.next, index - 1);
    }
}