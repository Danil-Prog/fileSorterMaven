package com.testCFT.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App {

    public static final String EXIT = " Click Enter to EXIT";

    public final OperationService operationService = new OperationService();

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Необходимо минимум 3 аргумента!" + EXIT);
            exitByEnterPressed();
        }
        ArrayList<String> rawData = new ArrayList<>();

        for (int i = 3; i <= args.length - 1; i++) {
            String inputFileName = args[i];
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
                while (reader.ready()) {
                    rawData.add(reader.readLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println("Входной файл с таким именем не найден!" + EXIT);
            } catch (IOException e) {
                System.out.println("Ошибка чтения входных данных");
            } catch (Exception ex) {
                break;
            }
        }
        new App().run(args[0], args[1], args[2], rawData);
    }

    public void run(
            String listMode,
            String listType,
            String outputFileName,
            ArrayList<String> rawData) {
                String[] data = (String[]) rawData.toArray();
                listType = operationService.checkListType(data, listType);
                boolean isAscending = operationService.checkListMode(listMode);
                operationService.fileWrite(listType, outputFileName, isAscending, data);
        }

    // Завершение программы по нажатии пользователем Enter
    public static void exitByEnterPressed() {
        try {
            System.in.read();
            System.exit(1);
        } catch (IOException ignored) {

        }
    }


}
