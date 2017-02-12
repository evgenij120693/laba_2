package ru.svetozarov.lab_1.handler_file;

//import ru.svetozarov.lab_1.exceptions.ErrorTest;

import ru.svetozarov.lab_1.WordBook;
import ru.svetozarov.lab_1.exceptions.DublicateWordException;
import ru.svetozarov.lab_1.exceptions.InvalidCharException;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс предназначен для работы с ресурсом в отдельном потоке
 * @author Evgenij Svetozarov
 */
public class ReaderFile extends Thread {
    private String nameFile;
    private WordBook wordBook;
    private final static String REG_HTML="^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";

    public String getNameFile() {
        return nameFile;
    }

    /**
     * Функция проверят является ли nameFile
     * удаленным ресурсом или локальным файлом, далее в соответсвии
     * с этим устанавливает значение поля this.name
     * @param nameFile имя ресурса (файла)
     */
    public void setNameFile(String nameFile) {
        Pattern p = Pattern.compile(REG_HTML);
        Matcher m = p.matcher(nameFile);
        if(m.matches()){
            this.nameFile = nameFile;
        }
        else{
            this.nameFile="file:/"+nameFile;
        }

    }

    /**
     * Функция-конструктор
     * @param name имя ресурса
     * @param wordBook ссылка на словарь
     */
    public ReaderFile(String name, WordBook wordBook){
            this.wordBook = wordBook;
            setNameFile(name);
    }

    private void readFile() throws FileNotFoundException, IOException,InvalidCharException, DublicateWordException{
        try (BufferedReader reader = new BufferedReader(new InputStreamReader((new URL(nameFile)).openStream()));) {
            String str;
            while ((str = reader.readLine()) != null) {
                Parser.parseString(str, wordBook);
            }
        }
    }

    @Override
    /**
     * Функция запуска процесса чтения ресурса
     */
    public void run(){
        try {
            readFile();
        }catch (FileNotFoundException e) {
            wordBook.failedProcess("Error: file not found "+getNameFile());
            e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
            wordBook.failedProcess("Error open file "+getNameFile());
        }catch (InvalidCharException e){
            wordBook.failedProcess("Error: invalid char in string "+e.getMessage()+", in file "+getNameFile());

        }catch (DublicateWordException e){
            wordBook.failedProcess("Error, dublicate word: "+e.getMessage()+",in file "+getNameFile());
        }
    }
}
