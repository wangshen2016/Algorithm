package com.wangxshen.queue;

import com.wangxshen.linkedList.DoublyLinkedList;

/**
 * @Author WangShen
 * @Date 2020/9/25 14:56
 * @Version 1.0
 */
public class DoubleEndsQueue<T> {
    public DoublyLinkedList<T> head;
    public DoublyLinkedList<T> tail;

    public DoubleEndsQueue() {
    }

    public void addFromHead(T val) {
        DoublyLinkedList<T> cur = new DoublyLinkedList<T>(val);
        if (head == null) {
            head = cur;
            tail = cur;
        } else {
            head.pre = cur;
            cur.next = head;
            head = cur;
        }
    }

    public void addFromBottom(T val) {
        DoublyLinkedList<T> cur = new DoublyLinkedList<T>(val);
        if (tail == null) {
            head = cur;
            tail = cur;
        } else {
            tail.next = cur;
            cur.pre = tail;
            tail = cur;
        }
    }

    public T popFromHead() {
        if (head == null) {
            return null;
        }

        DoublyLinkedList<T> ret = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            ret.next = null;
            head.pre = null;
        }

        return ret.value;
    }

    public T popFromBottom() {
        if (tail == null) {
            return null;
        }

        DoublyLinkedList<T> ret = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.pre;
            ret.pre = null;
            tail.next = null;
        }

        return ret.value;
    }

    public Boolean isEmpty() {
        return head == null ? true : false;
    }
}
