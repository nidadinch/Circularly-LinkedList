import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author nidadinc
 *
 * Algorithm: First of all, convert L and M to String via toString() method
 * and after, use isEmpty() method for checking two string's equality
 * by checking firstly firstString's size equal to secondString's size.
 * Then, checking if secondString is a substring of firstString by concatenated by itself.
 *
 */


public class Exercise1 {
    public static void main(String[] args) {
        // Created two circularly linked lists L and M
        CircularlyLinkedList<Integer> L = new CircularlyLinkedList<>();
        CircularlyLinkedList<Integer> M = new CircularlyLinkedList<>();

        // Inserted same elements but in different order to L and M
        L.insert(1);
        L.insert(2);
        L.insert(3);
        L.insert(4);
        L.insert(5);
        L.insert(6);
        M.insert(2);
        M.insert(3);
        M.insert(4);
        M.insert(5);
        M.insert(6);
        M.insert(1);

        // Circularly Linked Lists printed
        System.out.println(L);
        System.out.println(M);

        // Circularly Linked Lists toString
        String l = L.toString();
        String m = M.toString();

        // Test for only strings
        String firstString = "nidadinc";
        String secondString = "dincnida";
        String s3 = "dadincni";

        System.out.println(L.isEqual(firstString,secondString));
        System.out.println(L.isEqual(firstString,s3));

        // isEqual method checks L and M is equal
        System.out.println(L.isEqual(l,m));


    }
}
/**
 * @author nidadinc
 * @version 1.0
 * Created CircularlyLinkedList class
 *
 */
class CircularlyLinkedList<E> {

    /**
     * @author nidadinc
     * @version 1.0
     * Created Node<E> class
     */
    private static final class Node<E> {

        private final E data;
        private Node<E> next;
        
        Node(final E data) {
            this.data = data;
        }

        // Getters and setters
        E getData() {
            return data;
        }

        Node<E> getNext() {
            return next;
        }

        void setNext(final Node<E> next) {
            this.next = next;
        }
    }

    // No need for tail, because in circularly linked lists, tail = head.next
    private Node<E> head;
    private int size;

    // size() method returns size of the list
    public int size() {
        return size;
    }

    // insert method appending new node lo list
    public void insert(int n) {
        Node node = new Node(n);

        if (head == null) {
            node.next = node;
        } else {
            node.next = head.next;
            head.next = node;
        }
        head = node;
        size++;
    }

    
    /**
     * Delete method for deleting element from list
     * @throws NoSuchElementException  If an input or output
     *                      exception occurred
     */
    public E delete() throws NoSuchElementException{
        if (isEmpty()) {
            throw new NoSuchElementException("Deleting from an empty list.");
        }

        final E headd = head.getData();

        if (size == 1) {
            head = null;
            head.next = null;
        } else {
            head = head.getNext();
            head.next.setNext(head);
        }

        size--;
        return headd;
    }

    // isEmpty method for is list's size equals 0.
    public boolean isEmpty() {
        return size == 0;
    }

    // toString method Overrided instead of writing print method.
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");

        // if list is empty return "" but if it is not empty return head.getData()
        if (size > 0) {
            sb.append(head.getData());
        } else {
            return "";
        }

        // Created currentNode for taking head.getNext() element and appending to string
        Node<E> currentNode = head.getNext();

        for (int i = 1; i < size; ++i, currentNode = currentNode.getNext()) {
            sb.append("").append(currentNode.getData());
        }

        return sb.append("").toString();
    }

    // Checks two string's equality by checking firstly firstString's size equal to secondString's size.
    // Then, checking if secondString is a substring of firstString by concat by itself.
    boolean isEqual(String firstString, String secondString) {
        return (firstString.length() == secondString.length()) && ((firstString+firstString).indexOf(secondString) != -1);
    }

}
