package com.testCFT.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static com.testCFT.app.App.EXIT;

public class OperationService {

    public String checkListType(String[] arr, String expectedType) {
        if (expectedType.equals("-i")) {
            try {
                int[] intArr = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
            } catch (NumberFormatException e) {
                System.out.println(
                        "Вы пытаетесь отсортировать массив, содержащий строки, как массив целых чисел. Тип сортируемых данных будет изменен.");
                return "-s";
            }
        } else if (expectedType.equals("-s")) {
            try {
                int[] intArr = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
            } catch (NumberFormatException e) {
                System.out.println(
                        "Вы пытаетесь отсортировать массив целых чисел как массив строк. Тип сортируемых данных будет изменен.");
                return "i";
            }
        } else {
            System.out.println(
                    "Вы указали неверный тип входных данных. Данные будут отсортированы по умолчанию как массив строк.");
        }
        return expectedType;
    }

    public boolean checkListMode(String listMode) {
        if (listMode.equals("-a")) {
            return true;
        } else if (listMode.equals("-d")) {
            return false;
        }
        System.out.println(
                "Вы неверно указали тип сортировки. Элементы будут отсортированы по возрастанию по умолчанию.");
        return true;
    }

    public void fileWrite(String listType, String outputFileName, boolean isAscending, String[] data) {
        try (FileWriter writer = new FileWriter(outputFileName)) {
            if (listType.equals("-i")) {
                int[] intArr = Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
                Sorter.sortInt(intArr, isAscending);
                for (int j : intArr) {
                    writer.write(j + "\r\n");
                }
            } else {
                Sorter.sortString(data, isAscending);
                for (String datum : data) {
                    writer.write(datum + "\r\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи выходных данных." + EXIT);
        }
    }


}
