package org.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class test {

    @Test
    public void test01() {
        int[] num1 = {1, 2};
        int[] num2 = {3, 4};
        double medianSortedArrays = findMedianSortedArrays(num1, num2);
        System.out.println("medianSortedArrays = " + medianSortedArrays);
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int[] arr = new int[totalLength];
        for (int i = 0; i < nums1.length; i++) {
            arr[i] = nums1[i];
        }
        for (int i = nums1.length,j = 0; i < totalLength; i++,j++) {
            arr[i] = nums2[j];
        }
        Arrays.sort(arr);
        if ((arr.length % 2) == 0) {
            return (arr[(arr.length /2) - 1] + arr[(arr.length /2)]) / 2.0;
        }
        return Math.floor(arr[arr.length /2]);
    }

    public static boolean isOddArr(int[] arr) {
        if (arr.length % 2 == 0) {
            return false;
        }
        return true;
    }
}
