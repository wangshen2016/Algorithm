package com.wangxshen.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @Author WangShen
 * @Date 2020/9/27 23:29
 * @Version 1.0
 */
public class ComparatorHeap<T> {
    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private Integer heapSize;
    private Comparator<? super T> comparator;

    public ComparatorHeap(Comparator<? super T> comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return this.heapSize == 0;
    }

    public boolean contains(T key) {
        return this.indexMap.containsKey(key);
    }

    public void push(T value) {
        this.heap.add(value);
        this.indexMap.put(value, this.heapSize);
        heapInsert(this.heapSize++);
    }

    public T pop() {
        T ans = this.heap.get(0);
        swap(0, --this.heapSize);
        heapify(0, this.heapSize);
        return ans;
    }

    public void resign(T value) {
        int index = this.indexMap.get(value);
        heapInsert(index);
        heapify(index, this.heapSize);
    }

    public void heapInsert(int index) {
        while (this.comparator.compare(this.heap.get(index), this.heap.get((index-1)/2)) < 0) {
            swap(index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    public void heapify(int index, int heapSize) {
        int lchild = 2 * index + 1;
        while (lchild < heapSize) {
            int priority = lchild + 1 < heapSize &&
                    comparator.compare(this.heap.get(lchild + 1), this.heap.get(lchild)) < 0 ? lchild + 1 : lchild;
            priority = comparator.compare(this.heap.get(priority), this.heap.get(index)) < 0 ? priority : index;
            if (priority == index) {
                break;
            }
            swap(index, priority);
            index = priority;
            lchild = 2 * index + 1;
        }
    }

    public void swap(int i, int j) {
        T t1 = this.heap.get(i);
        T t2 = this.heap.get(j);
        this.heap.set(j, t1);
        this.heap.set(i, t2);
        this.indexMap.put(t1, j);
        this.indexMap.put(t2, i);
    }


    //以下部分为测试用例和对数器
    public static class Student {
        public int classNo;
        public int age;
        public int id;

        public Student(int c, int a, int i) {
            classNo = c;
            age = a;
            id = i;
        }

    }

    public static class StudentComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }

    }

    public static void main(String[] args) {
        Student s1 = null;
        Student s2 = null;
        Student s3 = null;
        Student s4 = null;
        Student s5 = null;
        Student s6 = null;

        s1 = new Student(2, 50, 11111);
        s2 = new Student(1, 60, 22222);
        s3 = new Student(6, 10, 33333);
        s4 = new Student(3, 20, 44444);
        s5 = new Student(7, 72, 55555);
        s6 = new Student(1, 14, 66666);

        PriorityQueue<Student> heap = new PriorityQueue<>(new StudentComparator());
        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);
        heap.add(s6);
        while (!heap.isEmpty()) {
            Student cur = heap.poll();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }

        System.out.println("===============");

        ComparatorHeap<Student> myHeap = new ComparatorHeap<>(new StudentComparator());
        myHeap.push(s1);
        myHeap.push(s2);
        myHeap.push(s3);
        myHeap.push(s4);
        myHeap.push(s5);
        myHeap.push(s6);
        while (!myHeap.isEmpty()) {
            Student cur = myHeap.pop();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }

        System.out.println("===============");

        s1 = new Student(2, 50, 11111);
        s2 = new Student(1, 60, 22222);
        s3 = new Student(6, 10, 33333);
        s4 = new Student(3, 20, 44444);
        s5 = new Student(7, 72, 55555);
        s6 = new Student(1, 14, 66666);

        heap = new PriorityQueue<>(new StudentComparator());

        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);
        heap.add(s6);

        s2.age = 6;
        s4.age = 12;
        s5.age = 10;
        s6.age = 84;

        while (!heap.isEmpty()) {
            Student cur = heap.poll();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }

        System.out.println("===============");

        s1 = new Student(2, 50, 11111);
        s2 = new Student(1, 60, 22222);
        s3 = new Student(6, 10, 33333);
        s4 = new Student(3, 20, 44444);
        s5 = new Student(7, 72, 55555);
        s6 = new Student(1, 14, 66666);

        myHeap = new ComparatorHeap<>(new StudentComparator());

        myHeap.push(s1);
        myHeap.push(s2);
        myHeap.push(s3);
        myHeap.push(s4);
        myHeap.push(s5);
        myHeap.push(s6);

        s2.age = 6;
        myHeap.resign(s2);
        s4.age = 12;
        myHeap.resign(s4);
        s5.age = 10;
        myHeap.resign(s5);
        s6.age = 84;
        myHeap.resign(s6);

        while (!myHeap.isEmpty()) {
            Student cur = myHeap.pop();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }



        // 对数器
        System.out.println("test begin");
        int maxValue = 100000;
        int pushTime = 1000000;
        int resignTime = 100;
        ComparatorHeap<Student> test = new ComparatorHeap<>(new StudentComparator());
        ArrayList<Student> list = new ArrayList<>();
        for(int i = 0 ; i < pushTime; i++) {
            Student cur = new Student(1,(int) (Math.random() * maxValue), 1000);
            list.add(cur);
            test.push(cur);
        }
        for(int i = 0 ; i < resignTime; i++) {
            int index = (int)(Math.random() * pushTime);
            list.get(index).age = (int) (Math.random() * maxValue);
            test.resign(list.get(index));
        }
        int preAge = Integer.MIN_VALUE;
        while(test.isEmpty()) {
            Student cur = test.pop();
            if(cur.age < preAge) {
                System.out.println("Oops!");
            }
            preAge = cur.age;
        }
        System.out.println("test finish");

    }
}
