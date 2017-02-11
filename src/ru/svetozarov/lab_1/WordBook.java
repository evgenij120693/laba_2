package ru.svetozarov.lab_1;

import ru.svetozarov.lab_1.exceptions.DublicateWordException;
import ru.svetozarov.lab_1.exceptions.InvalidCharException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/**
 * Created by Шмыга on 09.02.2017.
 */
public class WordBook {
    private volatile HashSet<String> wordBook=new HashSet<>();
    public volatile boolean flagError=false;
    public volatile boolean isShowError=false;
    public boolean addWord(String str){
        synchronized (wordBook) {
            System.out.println("Flag error: "+flagError+" thread "+Thread.currentThread().getId());
            boolean result=false;
            if (!flagError) {
                result=wordBook.add(str);
                if(!result){
                    flagError=true;
                }
            }
            //wordBook.notifyAll();
            return result;
        }
    }
    public HashSet<String> getWordbook() {
        return wordBook;
    }

    public void setWordbook(HashSet<String> wordBook) {
        this.wordBook = wordBook;
    }

    public void writeFile(){
        String fileName="C:\\res.txt";
        try(FileWriter file=new FileWriter(fileName)){
            for (String str:
                    wordBook){
                file.write(str);
                file.append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void failedProcess(String strError) {
        synchronized (wordBook){
            if(!isShowError){
                System.out.println(strError);
                writeFile();
            }
            isShowError=true;

           // wordBook.notify();

        }
    }
}
