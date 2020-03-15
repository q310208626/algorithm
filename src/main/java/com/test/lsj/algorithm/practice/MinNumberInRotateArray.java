package com.test.lsj.algorithm.practice;

/*
 *  把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *  输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 *  例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 *  NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class MinNumberInRotateArray {

    public static void main(String[] args){
        int[] array = {3,4,5,1,2};
        System.out.println(minNumberInRotateArray(array));
    }

    public static int minNumberInRotateArray(int[] array) {
        if(null ==array || array.length == 0) return 0;
        return getMin(array,0,array.length-1);

    }

    // 456 | 123
    // 4561 | 2345
    private static int getMin(int[] array, int start, int end){
        if(start >= end) return array[start];

        // 递增序列，非大则小
        // 456 | 123
        if(array[start] < array[end]){
            return array[start];
        }else{
            int mid = (start + end)/2;
            int leftMin = getMin(array,0,mid);
            int rightMin = getMin(array,mid + 1,end);
            return  leftMin < rightMin?leftMin:rightMin;
        }

    }
}
