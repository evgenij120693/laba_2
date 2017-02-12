package ru.svetozarov.lab_1;
import ru.svetozarov.lab_1.handler_file.ReaderFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс Main точка входа в программу
 * @author Evgenij Svetozarov by 09.02.2017
 */
public class Main {
    /**
     * Функция является точкой входа в программу
     * @param args массив ресурсов, с которых считываем данные
     */
    public static void main(String[] args) {
        ArrayList<ReaderFile> readersArray=new ArrayList<>(args.length);
        WordBook wordBook =new WordBook();

        for (String arg:
             args) {
            ReaderFile readerFile= new ReaderFile(arg, wordBook);
            readersArray.add(readerFile);
            readerFile.start();
        }

        for (ReaderFile readerFile:
                readersArray){
            try {
                readerFile.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        wordBook.writeFile();
        if(!wordBook.flagError) {
            System.out.println("Process succesfully completed.");
        }else{
            System.out.println("Process failed.");
        }

        System.out.println("The result is stored in result.txt");

    }
}
