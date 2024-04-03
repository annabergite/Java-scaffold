package com.annabergite.multithread;

/**
 * @PACKAGE_NAME: com.annabergite.multithread
 * @USER: annabergite
 * @Description
 * @Date 2024/3/19 19:58
 * @Version 1.0
 * @PROJECT_NAME: Java-scaffold
 **/
public class DaxiTest {

    /**
     * 一条
     */
    Object object = new Object();

    public static void main(String[] args) {
        int[] test = new int[]{1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1};
        System.out.println(solution(test));
    }


    //19:58
    public static int solution(int[] housese) {
        if (housese.length == 0) {
            return 0;
        }
        int longest0Left = 0;
        int longest0Length = 0;

        int present0Left = 0;
        int present0Length = 0;

        int first1 = -1;
        boolean needToNoteFirst1 = true;
        for (int i = 0; i < housese.length; i++) {
            if (needToNoteFirst1) {
                if (housese[i] == 1) {
                    first1 = i;
                    needToNoteFirst1 = false;
                    present0Left = first1;
                }
            } else {
                if (housese[i] == 1) {
                    if (i - 1 >= 0) {
                        present0Length = i - present0Left;
                        present0Left = i - present0Length;
                        if (present0Length > longest0Length) {
                            longest0Length = present0Length;
                            longest0Left = present0Left;
                        }
                        present0Length = 0;
                    }
                }
            }
        }
        longest0Length = longest0Length / 2;
        //对第一个1之前与最后一个1之后进行特殊编译
        if (present0Length > longest0Length) {
            longest0Length = present0Length;
            longest0Left = present0Left;
            return housese.length - 1;
        }
        if (first1 - 1 > longest0Length) {
            longest0Length = first1 - 1;
            longest0Left = 0;
            return 0;
        }
        return longest0Left + longest0Length;
    }
}
