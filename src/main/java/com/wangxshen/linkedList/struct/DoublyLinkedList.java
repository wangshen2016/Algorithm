package com.wangxshen.linkedList.struct;

/**
 * @Author WangShen
 * @Date 2020/9/25 13:58
 * @Version 1.0
 */
public class DoublyLinkedList<T> implements LinkedList<T> {
    public T value;
    public DoublyLinkedList<T> pre;
    public DoublyLinkedList<T> next;

    public DoublyLinkedList(T value) {
        this.value = value;
    }

    public LinkedList<T> reverseLinkedList(LinkedList<T> head) {
        DoublyLinkedList<T> h = (DoublyLinkedList<T>) head;
        DoublyLinkedList<T> pre = null, next = null;
        while(h != null) {
            next = h.next;
            h.next = pre;
            h.pre = next;
            pre = h;
            h = next;
        }
        return pre;
    }

    public LinkedList<T> removeValue(LinkedList<T> head, Object val) {
        DoublyLinkedList<T> h = (DoublyLinkedList<T>) head;
        DoublyLinkedList<T> pre = new DoublyLinkedList(null);
        pre.next = h;
        h.pre = pre;
        DoublyLinkedList<T> last = pre;
        DoublyLinkedList<T> next = null;
        while (h != null) {
            next = h.next;
            if (h.value.equals(val)) {
                next.pre = last;
                last.next = next;
            } else {
                last = h;
            }

            h = next;
        }
        return pre.next;
    }
}
