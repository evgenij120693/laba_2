package ru.svetozarov.lab_1;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import ru.svetozarov.lab_1.thread_maker.ThreadMaker;


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
        WordBook wordBook = new WordBook();
        ThreadMaker threadMaker = new ThreadMaker(args, wordBook);
        if(threadMaker.createReaderFile()){
            threadMaker.startReading();
        }
        else {
            logger.warn("Select resources....");
        }

    }
}
