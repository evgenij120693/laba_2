package ru.svetozarov.lab_1;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import ru.svetozarov.lab_1.handler_file.ReaderFile;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Класс Main точка входа в программу
 * @author Evgenij Svetozarov by 09.02.2017
 */
public class Main {
    private static final Logger logger = Logger.getLogger(WordBook.class);

    static {
        DOMConfigurator.configure("src/ru/svetozarov/lab_1/resources/log4j.xml");
    }
    /**
     * Функция является точкой входа в программу
     * @param args массив ресурсов, с которых считываем данные
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            logger.warn("Select resources....");
        } else {
            ArrayList<ReaderFile> readersArray=new ArrayList<>(args.length);
            WordBook wordBook = new WordBook();
            for (String arg :
                    args) {
                ReaderFile readerFile = new ReaderFile(arg, wordBook);
                readersArray.add(readerFile);
                readerFile.start();
            }

            for (ReaderFile readerFile :
                    readersArray) {
                try {
                    readerFile.join();
                } catch (InterruptedException e) {
                    logger.error("Error interrupted..");
                }
            }
            try {
                wordBook.writeFile("result.txt");
            } catch (IOException e) {
                logger.error("Error writing file.");
            }

            if (!wordBook.flagError) {
                logger.info("Process succesfully completed.");
            } else {
                logger.info("Process failed.");
            }

            logger.info("The result is stored in result.txt");

        }
    }
}
