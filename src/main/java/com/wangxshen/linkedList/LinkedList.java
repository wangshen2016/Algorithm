package com.wangxshen.linkedList;

/**
 * @Author WangShen
 * @Date 2020/9/25 14:01
 * @Version 1.0
 */
public interface LinkedList<T> {

    /**
     * @Author:   on2020-09-25 14:18:11
     * @Param: head
     * @return: LinkedList
     * description: 链表反转算法
     */
    public LinkedList<T> reverseLinkedList(LinkedList<T> head);

    /**
     * @Author:   on2020-09-25 14:19:40
     * @Param: head, val
     * @return: LinkedList
     * description: 删除链表中特定值的节点，返回头节点
     */
    public LinkedList<T> removeValue(LinkedList<T> head, Object val);
}
