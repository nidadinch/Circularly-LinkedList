package com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author nidadinc
 *
 */

public class Exercise2 {
    public static void main(String[] args) {
        // Created two circularly linked lists L and M
        CircularlyLinkedList<Integer> L = new CircularlyLinkedList<>();


        // Inserted same elements but in different order to L and M
        L.insert(1);
        L.insert(2);
        L.insert(3);
        L.insert(4);
        L.insert(5);
        L.insert(6);

        // Circularly Linked Lists printed
        System.out.println(L);

        L.splitList();

        L.printList(L.firstHead);
        L.printList(L.secondHead);




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
    public Node<E> head, firstHead, secondHead;
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

   void splitList(){
       Node firstNode = head;
       Node secondNode = head;

       // if list is emply return nothing.
       if (head == null) {
           return;
       }

       /* For odd nodes in list, secondNode will be head for odd nodes
       secondNode next will be head */
       while (secondNode.next.next != head && secondNode.next != head) {
           secondNode = secondNode.next.next;
           firstNode = firstNode.next;
       }

       /* For even nodes in list one move for secondNode  */
       if (secondNode.next.next == head) {
           secondNode = secondNode.next;
       }

       // firstCircularlyLinked List's head setted to firstHead Node
       firstHead = head;

       // secondCircularlyLinked List's head setted to secondHead Node
       if (head.next != head) {
           secondHead = firstNode.next;
       }
       /* make circular to secondCircularlyLinkedList */
       secondNode.next = firstNode.next;

       /* make circular to firstCircularlyLinkedList  */
       firstNode.next = head;
   }

   // print method for printing lists
    void printList(Node node) {
        Node temp = node;
        if (node != null) {
            do {
                System.out.print(temp.data + " ");
                temp = temp.next;
            } while (temp != node);
            System.out.println(" ");
        }
    }

}

