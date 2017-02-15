package ru.svetozarov.lab_1;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import ru.svetozarov.lab_1.exceptions.DublicateWordException;
import ru.svetozarov.lab_1.exceptions.InvalidCharException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/**
 * Класс осуществляет работу со словарем
 * @author Evgenij Svetozarov
 */
public class WordBook {
    private static final Logger logger = Logger.getLogger(WordBook.class);

    static {
        DOMConfigurator.configure("src/ru/svetozarov/lab_1/resources/log4j.xml");
    }

    private  HashSet<String> wordBook=new HashSet<>();
    public volatile boolean flagError=false;
    public volatile boolean isShowError=false;

    public HashSet<String> getWordBook() {
        return wordBook;
    }

    /**
     * Функция добавляет слово в словарь wordBook
     * @param str слово, добовляемое в словарь
     * @return результат операции добавления слова в словарь,
     * true -  в случае успеха, false - в случае если такое слово уже есть (произойдет перезапись)
     */
    public boolean addWord(String str){
        synchronized (wordBook) {
            boolean result=false;
            if (!flagError) {
                result = wordBook.add(str);
                if(!result){
                    flagError = true;
                }
                else {
                    logger.info(" added work: "+str);
                }
            }
            return result;
        }
    }

    /**
     * функция записи слов, хранящихся в словаре wordBook, в файл
     */
    public void writeFile(String fileName) throws IOException{
        try(FileWriter file = new FileWriter(fileName)){
            for (String str:
                    wordBook){
                file.write(str);
                file.append('\n');
            }
        }
    }

    /**
     * Функция останавливает работу со словарем wordBook, выводи сообщение об ошибке,
     * @param strError сообщение об ошибке, ее описание
     */
    public void failedProcess(String strError) {
        synchronized (wordBook){
            flagError = true;
            if(!isShowError){
                logger.error(strError);
            }
            isShowError = true;
        }
    }
}
