package ru.svetozarov.lab_1;
import ru.svetozarov.lab_1.handler_file.ReaderFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Шмыга on 07.02.2017.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<ReaderFile> readersArray=new ArrayList<>(args.length);
        WordBook wordBook =new WordBook();

        for (String arg:
             args) {
            ReaderFile readerFile= new ReaderFile(arg, wordBook);
            readersArray.add(readerFile);

        }
        for (ReaderFile readerFile:
                readersArray){
            readerFile.start();
        }


    }
}
