package com.wangxshen.linkedList;

/**
 * @Author WangShen
 * @Date 2020/9/25 13:56
 * @Version 1.0
 */
public class SinglyLinkedList<T> implements LinkedList<T>{
    public T value;
    public SinglyLinkedList<T> next;

    public SinglyLinkedList(T value) {
        this.value = value;
    }

    public LinkedList<T> reverseLinkedList(LinkedList<T> head) {
        SinglyLinkedList<T> h = (SinglyLinkedList<T>) head;
        SinglyLinkedList<T> pre = null, next = null;
        while (head != null) {
            next = h.next;
            h.next = pre;
            pre = h;
            h = next;
        }
        return pre;
    }

    /**
     * @Author:   on2020-09-29 14:22:51
     * @Param: null
     * @return:
     * description: 头插法
     */
    public LinkedList<T> removeValue(LinkedList<T> head, Object val) {
        SinglyLinkedList pre = new SinglyLinkedList(null);
        SinglyLinkedList h = (SinglyLinkedList) head;
        pre.next = h;
        SinglyLinkedList last = pre;
        SinglyLinkedList next = null;
        while (h != null) {
            next = h.next;
            if (h.value.equals(val)) {
                last.next = next;
                h = next;
            }
        }
        return pre.next;
    }


    /**
     * @Author:   on2020-09-29 14:23:15
     * @Param: null
     * @return:
     * description: 普通方法，不需要头插指针
     */
    public LinkedList<T> removeValue2(LinkedList<T> head, Object val) {

        SinglyLinkedList<T> h = (SinglyLinkedList<T>) head;
        while (h != null) {
            if (h.value.equals(val)) {
                h = h.next;
            } else {
                break;
            }
        }
        SinglyLinkedList<T> pre = h;
        SinglyLinkedList<T> cur = h.next;
        while (cur != null) {
            if (cur.value.equals(val)) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return h;
    }

}
