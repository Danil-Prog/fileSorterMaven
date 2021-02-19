package com.testCFT.app;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static String exit = " Click Enter to EXIT";

    public static void main(String[] args) {
        if (args.length < 3){

            System.out.println("Необходимо минимум 3 аргумента!" + exit);
            exitByEnterPressed();

        }
        String listMode = args[0];
        String listType = args [1];
        String outputFileName = args[2];
        String inputFileName;
        ArrayList<String> rawData = new ArrayList<>();
        String data[];
        int intArr[];
        boolean isAscending;
        for (int i = 3; i <= args.length - 1; i++) {
            inputFileName = args[i];

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
                while (reader.ready()) {
                    rawData.add(reader.readLine());
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("Входной файл с таким именем не найден!" + exit);

            } catch (IOException e) {
                System.out.println("Ошибка чтения входных данных");
            }catch (Exception ex){
                break;
            }
        }

        data = new String[rawData.size()];
        rawData.toArray(data);
        listType = checkListType(data, listType);
        isAscending = checkListMode(listMode);
        try(FileWriter writer = new FileWriter(outputFileName)) {
            if (listType.equals("-i")) {
                intArr = Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
                Sorter.sortInt(intArr, isAscending);
                for (int i = 0; i < intArr.length; i++)
                    writer.write(intArr[i] + "\r\n");
                writer.close();

            } else {
                Sorter.sortString(data, isAscending);
                for (int i = 0; i < data.length; i++)
                    writer.write(data[i] + "\r\n");
                writer.close();
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка записи выходных данных." + exit);
        }
    }

    // Завершение программы по нажатии пользователем Enter
    public static void exitByEnterPressed() {
        try {
            System.in.read();
            System.exit(1);
        }
        catch (IOException ignored) {

        }
    }

    public static String checkListType(String[] arr, String expectedType) {
        if (expectedType.equals("-i")){
            try {
                int intArr[] = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
            }
            catch (NumberFormatException e){
                System.out.println("Вы пытаетесь отсортировать массив, содержащий строки, как массив целых чисел. Тип сортируемых данных будет изменен.");
                return "-s";
            }
        }
        else if (expectedType.equals("-s")) {
            try {
                int intArr[] = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
                System.out.println("Вы пытаетесь отсортировать массив целых чисел как массив строк. Тип сортируемых данных будет изменен.");
                return "i";
            } catch (NumberFormatException e){
                return expectedType;
            }
        }
        else {
            System.out.println("Вы указали неверный тип входных данных. Данные будут отсортированы по умолчанию как массив строк.");
            return "-s";
        }
        return expectedType;
    }


    public static boolean checkListMode(String listMode) {
        if (listMode.equals("-a"))
            return true;
        else if (listMode.equals("-d"))
            return false;
        System.out.println("Вы неверно указали тип сортировки. Элементы будут отсортированы по возрастанию по умолчанию.");
        return true;
    }
}
