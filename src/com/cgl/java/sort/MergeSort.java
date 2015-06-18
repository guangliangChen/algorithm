package com.cgl.java.sort;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2015/6/17.
 */
public class MergeSort {
    private ArrayList<Integer> mergeSort(ArrayList<Integer> inputArray) {
        int length = inputArray.size();
//        ArrayList<Integer> outputArray;

        if(inputArray.isEmpty())
            return null;
        else if(inputArray.size() == 1)
            return inputArray;
        else {
            return mergeSort(0, inputArray.size() -1);
        }

    }

    private ArrayList<Integer> mergeSort(int startIndex, int endIndex) {
        ArrayList<Integer> outputList = new ArrayList<>();
        int middleIndex = endIndex /2;
        int leftEndIndex = middleIndex;
        int rightStratIndex = middleIndex + 1;
//        if(leftArray.size() > 1) {
//            leftChlidArrya =
//        }

        return outputList;


    }
}
