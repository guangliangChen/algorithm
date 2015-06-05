package com.cgl.java.sort;

import java.util.Arrays;
import java.util.List;

/**
 * InsertionSort in java.
 * worst time O(n(2))
 *
 * Created by guangliangChen on 2015/6/5.
 */
public class InsertionSort {

    public static void main(String[] args) {
        InsertionSort sort = new InsertionSort();
        List<Integer> integerList = Arrays.asList(5,2,4,6,1,3,9,8,10,7);
        System.out.println("inputList is " + integerList);
        sort.sort(integerList);
        System.out.println("sortedList is " + integerList);
    }

    private void sort(List<Integer> inputList) {

        for (int i = 1; i < inputList.size(); i++) {
            int j = i-1;
            int key = inputList.get(i);//while循环用来比较的基准值key

            while(j >= 0 && key < inputList.get(j)) {//while循环结束时的j的值无需排序
                inputList.set(j+1, inputList.get(j));
                j--;
            }
            inputList.set(j + 1, key);

            System.out.println("i is " + i);
            System.out.println(inputList);
        }
    }
}
