package com.testCFT.app;
public class Sorter {

    public static void sortInt(int [] arr, boolean isAscending) {
        int tmp;
        int j;
        for (int i = 0; i < arr.length; i++) {
            tmp = arr[i];
            j = i - 1;
            while (j >= 0 && (isAscending ? arr[j] > tmp : arr[j] < tmp)) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = tmp;
        }
    }

    public static void sortString(String[] arr, boolean isAscending) {
        String tmp;
        int j;
        for (int i = 0; i < arr.length; i++) {
            tmp = arr[i];
            j = i - 1;
            while (j >= 0 && (isAscending ? arr[j].compareTo(tmp) > 0 : arr[j].compareTo(tmp) < 0)){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = tmp;
        }
    }
}

