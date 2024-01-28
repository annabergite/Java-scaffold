package com.annabergite.middle;

import java.util.List;

/**
 * 假设你是一家合金制造公司的老板，你的公司使用多种金属来制造合金。现在共有 n 种不同类型的金属可以使用，并且你可以使用 k 台机器来制造合金。每台机器都需要特定数量的每种金属来创建合金。
 * <p>
 * 对于第 i 台机器而言，创建合金需要 composition[i][j] 份 j 类型金属。最初，你拥有 stock[i] 份 i 类型金属，而每购入一份 i 类型金属需要花费 cost[i] 的金钱。
 * <p>
 * 给你整数 n、k、budget，下标从 1 开始的二维数组 composition，两个下标从 1 开始的数组 stock 和 cost，请你在预算不超过 budget 金钱的前提下，最大化 公司制造合金的数量。
 * <p>
 * 所有合金都需要由同一台机器制造。
 * <p>
 * 返回公司可以制造的最大合金数。
 **/
public class BestNumberOfMedal2861 {
    int n;
    int budget;
    List<List<Integer>> composition;
    List<Integer> stock;
    List<Integer> cost;

    boolean isValid(long target) {
        for (List<Integer> currMachine : composition) {
            long remain = budget;
            for (int j = 0; j < n && remain >= 0; j++) {
                long need = Math.max(0, currMachine.get(j) * target - stock.get(j));
                remain -= need * cost.get(j);
            }
            if (remain >= 0) {
                return true;
            }
        }
        return false;
    }

    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition,
                                 List<Integer> stock, List<Integer> cost) {
        this.n = n;
        this.budget = budget;
        this.composition = composition;
        this.stock = stock;
        this.cost = cost;
        int l = -1;
        int r = budget / cost.get(0) + stock.get(0);
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (isValid(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    //第一眼看上去有点像是动态规划算法
    public static void main(String[] args) {

    }
}
