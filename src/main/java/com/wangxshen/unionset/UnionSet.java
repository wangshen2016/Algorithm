package com.wangxshen.unionset;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author WangShen
 * @Date 2020/12/7 11:16
 * @Version 1.0
 */
//并查集的实现及相关算法
public class UnionSet<V> {

    public static class Node<V>{
        V value;

        public Node(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public HashMap<V, Node<V>> nodes;
    public HashMap<Node<V>, Node<V>> parents;
    public HashMap<Node<V>, Integer> sizeMap;

    public UnionSet(List<V> list) {
        nodes = new HashMap<>();
        parents = new HashMap<>();
        sizeMap = new HashMap<>();

        for (V v : list) {
            Node<V> vNode = new Node<>(v);
            nodes.put(v, vNode);
            parents.put(vNode, vNode);
            sizeMap.put(vNode, 1);
        }
    }

    public UnionSet() {
        nodes = new HashMap<>();
        parents = new HashMap<>();
        sizeMap = new HashMap<>();
    }

    public Node<V> getFather(Node<V> node) {
        Node<V> cur = node;
        Deque<Node<V>> stack = new ArrayDeque<>();
        while (cur != parents.get(cur)) {
            stack.push(cur);
            cur = parents.get(cur);
        }
        while (!stack.isEmpty()) {
            parents.put(stack.pop(), cur);
        }
        return cur;
    }

    public boolean isSameSet(Node<V> a, Node<V> b) {
        if (!nodes.containsKey(a.value) || !nodes.containsKey(b.value)) {
            return false;
        }
        return getFather(a) == getFather(b);
    }

    public void unionSet(Node<V> a, Node<V> b) {
        if (!nodes.containsKey(a.value) || !nodes.containsKey(b.value)) {
            return;
        }
        Node<V> fatherA = getFather(a);
        Node<V> fatherB = getFather(b);
        if (fatherA == fatherB) {
            return;
        }

        if (sizeMap.get(fatherA) > sizeMap.get(fatherB)) {
            parents.put(fatherB, fatherA);
            sizeMap.put(fatherA, sizeMap.get(fatherA) + sizeMap.get(fatherB));
            sizeMap.remove(fatherB);
        } else {
            parents.put(fatherA, fatherB);
            sizeMap.put(fatherB, sizeMap.get(fatherA) + sizeMap.get(fatherB));
            sizeMap.remove(fatherA);
        }

    }

    /**
     * @Author:   on2020-12-08 10:23:33
     * @Param: null
     * @return:
     * description: 用户注册某平台的账户，需要填写QQ号，微信号，手机号三个关联账号，
     * 在这个平台每个学生可能有多个子账户，
     * 但只有关联账号有一个相同就认为是同一个用户的账户，
     * 给出一些账户的集合，问：这个集合中包含多少不同用户
     */
    public static class User {
        String wechat;
        String qq;
        String phone;

        public User(String wechat, String qq, String phone) {
            this.wechat = wechat;
            this.qq = qq;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "User{" +
                    "wechat='" + wechat + '\'' +
                    ", qq='" + qq + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(wechat, user.wechat) &&
                    Objects.equals(qq, user.qq) &&
                    Objects.equals(phone, user.phone);
        }

        @Override
        public int hashCode() {
            return Objects.hash(wechat, qq, phone);
        }
    }

    public static int getDifferentUsersCount(User[] users) {
        HashMap<String, User> wechatGroup = new HashMap<>();
        HashMap<String, User> qqGroup = new HashMap<>();
        HashMap<String, User> phoneGroup = new HashMap<>();

        ArrayList<User> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(user);
        }

        UnionSet<User> userUnionSet = new UnionSet<>(userList);

        for (User user : users) {
            if (wechatGroup.containsKey(user.wechat)) {
                User user1 = wechatGroup.get(user.wechat);
                userUnionSet.unionSet(userUnionSet.nodes.get(user), userUnionSet.nodes.get(user1));
            } else {
                wechatGroup.put(user.wechat, user);
            }

            if (qqGroup.containsKey(user.qq)) {
                User user1 = qqGroup.get(user.qq);
                userUnionSet.unionSet(userUnionSet.nodes.get(user), userUnionSet.nodes.get(user1));
            } else {
                qqGroup.put(user.qq, user);
            }

            if (phoneGroup.containsKey(user.phone)) {
                User user1 = phoneGroup.get(user.phone);
                userUnionSet.unionSet(userUnionSet.nodes.get(user), userUnionSet.nodes.get(user1));
            } else {
                phoneGroup.put(user.phone, user);
            }
        }

        return userUnionSet.sizeMap.size();
    }

    public static User[] generateUsers(int n, String[] wechats, String[] qqs, String[] phones) {
        User[] users = new User[n];
        int wechatRange = wechats.length;
        int qqRange = qqs.length;
        int phoneRange = phones.length;
        for (int i = 0; i < n; i++) {
            String wechat = wechats[(int)(Math.random() * wechatRange)];
            String qq = qqs[(int)(Math.random() * qqRange)];
            String phone = phones[(int)(Math.random() * phoneRange)];
            users[i] = new User(wechat, qq, phone);
            System.out.println(users[i]);
        }
        return users;
    }

    /**
     * @Author:   on2020-12-08 11:39:16
     * @Param: null
     * @return:
     * description: for test
     */
    public static void main(String[] args) {
        String[] wechats = new String[] {"wechat1", "wechat2", "wechat3", "wechat4", "wechat5", "wechat6"};
        String[] qqs = new String[] {"qq1", "qq2", "qq3", "qq4", "qq5", "qq6", "qq7", "qq8"};
        String[] phones = new String[] {"110", "119", "120", "114", "12315"};
        User[] users = generateUsers(4, wechats, qqs, phones);
        System.out.println(getDifferentUsersCount(users));
    }
}
