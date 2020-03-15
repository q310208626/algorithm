package com.test.lsj.algorithm.practice;

/*
 * 斐波那契函数: f(n) = f(n-1) + f(n-2)
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 *
 * 进阶:
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 *
 */
public class JumpFloorPractice {

    public static void main(String[] args){
        System.out.println(JumpFloor(4));
        System.out.println(JumpFloorII(4));
    }

    public static int JumpFloor(int target) {
        // f(n) = f(n-1) + f(n-2)
        if(target <= 0) return 0;
        if(target == 1) return 1;
        if(target == 2) return 2;

        int temp1 =1;
        int temp2 =2;
        int tempSum =0;
        for(int i = 3; i <= target; i++){
            tempSum = temp1 + temp2;
            temp1 = temp2;
            temp2 = tempSum;
        }
        return tempSum;
    }


    public static int JumpFloorII(int target) {
        // f(n) = 2f(n-1)
        if (target <= 0) return 0;
        if (target == 1) return 1;

        int temp1 = 1;
        for(int i = 2;i <= target;i++){
            temp1 *= 2;
        }
        return temp1;
    }
}
