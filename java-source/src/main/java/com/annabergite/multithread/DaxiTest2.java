package com.annabergite.multithread;

/**
 * @PACKAGE_NAME: com.annabergite.multithread
 * @USER: annabergite
 * @Description TODO
 * @Date 2024/3/22 19:01
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
public class DaxiTest2 {
    public static void main(String[] args) {

        int[] test = new int[]{40, 50, 65, 70, 3, 4, 5, 6, 7, 8, 10};
        System.out.println(solution(test, 0, test.length - 1));
    }

//一个数组n个元素不完全有序，0-n-1，a[n-1] < a[0] 半有序
    // 19：16-36


    public static int solution(int[] natures, int start, int end) {
        if (natures.length == 0) {
            return -1;
        }
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (natures[middle] > natures[middle + 1]) {
            return middle;
        }
        //如果既包含左边的序列又包含右边的
        else if (natures[middle] > natures[start] && natures[middle] < natures[end]) {
            return solution(natures, middle, (middle + end) / 2);
        } else if (natures[middle] < natures[end] && natures[middle] < natures[end]) {
            return solution(natures, (middle + start) / 2, end);
        }
        //边界条件，极值判断条件、遇到问题先把流程想清楚
        return -2;
    }
}
