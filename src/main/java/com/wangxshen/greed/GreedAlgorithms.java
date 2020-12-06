package com.wangxshen.greed;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.*;

/**
 * @Author WangShen
 * @Date 2020/12/6 10:43
 * @Version 1.0
 */
//贪心算法的各种案例
public class GreedAlgorithms {

    /**
     * @Author:   on2020-12-06 11:17:09 
     * @Param: null
     * @return: 
     * description: 给定一个由字符串组成的数组，必须把所有字符串拼接起来，
     * 返回所有可能的结果中，字典序最小的结果
     */
    //暴力方法
    public static String getMinPath(String[] strs) {
        String path = "";
        HashSet<Integer> used = new HashSet<>();
        ArrayList<String> all = new ArrayList<>();
        process_minPath(strs, path, used, all);
        String lowest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if (all.get(i).compareTo(lowest) < 0) {
                lowest = all.get(i);
            }
        }
        return lowest;
    }

    public static void process_minPath(String[] strs, String path, HashSet<Integer> used, ArrayList<String> all) {
        if (used.size() == strs.length) {
            all.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!used.contains(i)) {
                    used.add(i);
                    process_minPath(strs, path + strs[i], used, all);
                    used.remove(i);
                }
            }
        }
    }

    //贪心算法
    public static String getMinPath2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    @Test
    public void testMinPath() {
        int arrSize = 10;
        int strLength = 5;
        int loop = 100;
        for (int i = 0; i < loop; i++) {
            String[] strs = TestUtil.generateRandomStringArray(arrSize, strLength);
            String res1 = getMinPath(strs);
            String res2 = getMinPath2(strs);
            if (!res1.equals(res2)) {
                System.out.println("-------------test fail--------------");
                System.out.println(res1);
                System.out.println(res2);
                return;
            }
        }
        System.out.println("success");
    }
    
    /**
     * @Author:   on2020-12-06 13:23:40 
     * @Param: null
     * @return: 
     * description: 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个及以上的会议，
     * 给你每一个项目的开始和结束时间，你来安排宣讲的日程，要求会议室进行的宣讲的次数最多。
     * 返回最多宣讲场次。
     */
    public static class Program {
        int start;
        int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Program{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    //暴力解法
    public static int getMaxPrograms(Program[] programs) {
        int count = processGetMaxPrograms(programs, 0, 0);
        return count;
    }

    public static int processGetMaxPrograms(Program[] programs, int done, int timeline) {
        if (programs.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeline) {
                max = Math.max(max, processGetMaxPrograms(copyButExcept(programs,i), done+1, programs[i].end));
            }
        }
        return max;
    }

    public static Program[] copyButExcept(Program[] programs, int i) {
        Program[] copy = new Program[programs.length - 1];
        int index = 0;
        for (int j = 0; j < programs.length; j++) {
            if (j != i) {
                copy[index++] = programs[j];
            }
        }
        return copy;
    }

    //贪心算法
    public static int getMaxPrograms2(Program[] programs) {
        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;
            }
        });

        int timeline = 0;
        int count = 0;
        for (int i = 0; i < programs.length; i++) {
            if (timeline <= programs[i].start) {
                count++;
                timeline = programs[i].end;
            }
        }
        return count;
    }

    @Test
    public void testGetMaxPrograms() {
        int numOfProgram = 10;
        int loop = 10000;
        for (int i = 0; i < loop; i++) {
            Program[] programs = getPrograms(10);
            int res1 = getMaxPrograms(programs);;
            int res2 = getMaxPrograms2(programs);
            if (res1 != res2) {
                System.out.println("------------------test fail------------------");
                System.out.println("res1:" + res1 + ", res2:" + res2);
                return;
            }
        }
        System.out.println("success");
    }

    public static Program[] getPrograms(int size) {
        Program[] programs = new Program[size];
        for (int i = 0; i < size; i++) {
            int[] ints = TestUtil.generateRandomStartEnd();
            programs[i] = new Program(ints[0], ints[1]);
        }
        return programs;
    }

    /**
     * @Author:   on2020-12-06 14:50:37
     * @Param: null
     * @return:
     * description: 给定一个字符串str，只由'X'和'.'组成，
     * 'X'表示墙，不能放灯也不能被点亮，
     * '.'表示居民点，可以放灯，需要被点亮，
     * 如果灯放在i位置上，可以让i-1，i+1和i三个位置被照亮，
     * 如果需要点亮所有的居民点，至少需要几盏灯？
     */
    public static final char Y = '.';
    public static final char N = 'X';

    public static int getMinLights(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return processGetMinLights(str.toCharArray(), 0, new HashSet<Integer>());
    }

    /**
     * lights 表示放了灯的位置
     * index 表示当前位置
     * */
    public static int processGetMinLights(char[] chars, int index, HashSet<Integer> lights) {
        /**
        * 当所有位置都遍历过后，筛选出满足条件的方案
        * 一次递归在此处结束
        * */
        if (index == chars.length) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == Y) {
                    /**
                    * i-1，i，i+1中任一位置有灯，i位置都会被照亮
                    * */
                    if (!lights.contains(i-1)
                            && !lights.contains(i)
                            && !lights.contains(i+1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            /**
             * no 表示当前位置不放灯
             * yes 表示当前位置放灯
             * */
            int no = processGetMinLights(chars, index+1, lights);

            int yes = Integer.MAX_VALUE;
            /**
            * 判断此处可不可以放灯
            * */
            if (chars[index] == Y) {
                //放入灯
                lights.add(index);
                yes = processGetMinLights(chars, index+1, lights);
                //删除灯，lights是公用的，要恢复到初始状态给下一次递归用
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    //贪心算法
    public static int getMinLights2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        int lights = 0;
        while (index < chars.length) {
            if (chars[index] != Y) {
                index++;
            } else {
                lights++;
                if (index + 1 < chars.length) {
                    if (chars[index + 1] == Y) {
                        index = index + 3;
                    } else {
                        index = index + 2;
                    }
                } else {
                    //index + 1 == chars.length 时跳出循环
                    break;
                }
            }
        }
        return lights;
    }

    @Test
    public void testMinLights() {
        int strlen = 20;
        int loop = 100000;
        for (int i = 0; i < loop; i++) {
            String s = generateString(strlen);
            int res1 = getMinLights(s);
            int res2 = getMinLights2(s);
            if (res1 != res2) {
                System.out.println("-------------test fail--------------");
                System.out.println(s);
                System.out.println("res1:" + res1 + ", res2:" + res2);
                return;
            }
        }
        System.out.println("success");
    }

    public static String generateString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int flag = (int)(Math.random() * 2);
            if (flag == 0) {
                sb.append(Y);
            } else {
                sb.append(N);
            }
        }
        String str = sb.toString();
        return str;
    }

    /**
     * @Author:   on2020-12-06 17:40:09
     * @Param: null
     * @return:
     * description: 给一个整型数组如[20,10,30]，代表一个总价60的金条，
     * 每次切割金条都要花费与金条等价的钱，比如切一刀60的金条花费60元，
     * 如何花费最少的钱将金条切成给定数组中的划分？返回最少花费
     */
    //暴力解法
    public static int getMinCost(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int mergeCost = getMergeCost(arr, 0);
        return mergeCost;
    }

    public static int getMergeCost(int[] arr, int cost) {
        if (arr.length == 1) {
            return cost;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, getMergeCost(mergeAndCopy(arr, i, j), cost + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    public static int[] mergeAndCopy(int[] arr, int i, int j) {
        int index = 0;
        int[] ans = new int[arr.length-1];
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j) {
                ans[index++] = arr[k];
            }
        }
        ans[index] = arr[i] + arr[j];
        return ans;
    }

    //贪心算法
    public static int getMinCost2(int[] arr) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i : arr) {
            heap.add(i);
        }
        int cost = 0;
        int cur = 0;
        while (heap.size() > 1) {
            cur = heap.poll() + heap.poll();
            cost += cur;
            heap.add(cur);
        }
        return cost;
    }

    @Test
    public void testMinCost() {
        int size = 5;
        int range = 100;
        int loop = 10000;
        for (int i = 0; i < loop; i++) {
            int[] intArray = TestUtil.generateRandomIntArray(size, range);
            int res1 = getMinCost(intArray);
            int res2 = getMinCost2(intArray);
            if (res1 != res2) {
                System.out.println("------------------test fail----------------");
                System.out.println("res1:" + res1 + ", res2:" + res2);
                return;
            }
        }

        System.out.println("success");
    }
    
    /**
     * @Author:   on2020-12-06 21:35:35
     * @Param: null
     * @return: 
     * description:
     * 输入：正数数组costs，正数数组profits，正数K，正数M
     * costs[i]表示i号项目的花费
     * profits[i]表示i号项目的利润
     * K表示只能串行的最多做K个项目
     * M表示初始资金
     * 每做完一个项目，马上获得收益，可以支持你去做下一个项目，不能并行做项目。
     * 输出：你最后获得的最大钱数
     */
    public static class Profit{
        int cost;
        int profit;

        public Profit(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static int getMaxMoney(int[] costs, int[] profits, int K, int M) {
        ArrayList<Profit> profitArray = new ArrayList<>();
        for (int i = 0; i < costs.length; i++) {
            profitArray.add(new Profit(costs[i], profits[i]));
        }

        PriorityQueue<Profit> cqueue = new PriorityQueue<>(new Comparator<Profit>() {
            @Override
            public int compare(Profit o1, Profit o2) {
                return o1.cost - o2.cost;
            }
        });
        PriorityQueue<Profit> pqueue = new PriorityQueue<>(new Comparator<Profit>() {
            @Override
            public int compare(Profit o1, Profit o2) {
                return o2.profit - o1.profit;
            }
        });

        for (int i = 0; i < profitArray.size(); i++) {
            cqueue.add(profitArray.get(i));
        }
        for (int i = 0; i < K; i++) {
            while (cqueue.peek().cost <= M) {
                pqueue.add(cqueue.poll());
            }
            if (pqueue.isEmpty()) {
                return M;
            }
            M += pqueue.poll().profit;
        }
        return M;
    }

}
