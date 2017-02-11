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
 * Created by Evgenij Svetozarov on 07.02.2017.
 */
public class ReaderFile extends Thread {
    private String nameFile;
    private WordBook wordBook;
    private final static String REG_HTML="^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        Pattern p = Pattern.compile(REG_HTML);
        Matcher m = p.matcher(nameFile);
        System.out.println(nameFile.matches(REG_HTML));
        if(m.matches()){
            this.nameFile = nameFile;
        }
        else{
            this.nameFile="file:/"+nameFile;
        }

    }

    public ReaderFile(String name, WordBook wordBook){
            this.wordBook = wordBook;
            setNameFile(name);
    }

    private void readFile() throws FileNotFoundException, IOException,InvalidCharException, DublicateWordException{
        try (BufferedReader reader = new BufferedReader(new InputStreamReader((new URL(nameFile)).openStream()));) {
            String str;
            while ((str = reader.readLine()) != null) {
                Parser.parseString(str, wordBook);
               // System.out.println("rest");
            }
        }
    }

    @Override
    public void run(){
        try {
            readFile();
        }catch (FileNotFoundException e) {
            wordBook.failedProcess("Error: file not found");
            e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
            wordBook.failedProcess("Error: file not found");
        }catch (InvalidCharException e){
            wordBook.failedProcess("Error: invalid char in string\n"+e.getMessage());

        }catch (DublicateWordException e){
            wordBook.failedProcess("Error, dublicate word: "+e.getMessage());
        }
        wordBook.writeFile();
    }
}
