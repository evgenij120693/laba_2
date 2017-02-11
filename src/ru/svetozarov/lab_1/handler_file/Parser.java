package ru.svetozarov.lab_1.handler_file;

import ru.svetozarov.lab_1.WordBook;
import ru.svetozarov.lab_1.exceptions.DublicateWordException;
import ru.svetozarov.lab_1.exceptions.InvalidCharException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Шмыга on 07.02.2017.
 */
public class Parser {
    private static final String REG_INVALID_SYMBOL="[A-Za-z$%^&*_+|~=`{}\\[\\]\\\"'<>\\/]";
    private static final String REG_SPEC_SYMBOL="[$%^&*()_+|~=`{}\\[\\]\\\"'<>\\/]";
    private static final String REG_PUNKT_SYMBOL="[!,.;():?\uFEFF ]|(\\-|—)(?![А-я0-9\\\\n])|( )?(\\-|—)";

    private static boolean checkStringInvalidSymbols(String str){
        Pattern p = Pattern.compile(REG_INVALID_SYMBOL);
        Matcher m = p.matcher(str);
        return m.find();
    }

    private static boolean checkStringSpecSymbols(String str){
        Pattern p = Pattern.compile(REG_SPEC_SYMBOL);
        Matcher m = p.matcher(str);
        return m.find();
    }

    private static  String replaceCharPunct(String str){
        Pattern p = Pattern.compile(REG_PUNKT_SYMBOL);
        Matcher m = p.matcher(str);
        return m.replaceAll(REG_PUNKT_SYMBOL);
    }



    public static void parseString(String str, WordBook wordBook) throws InvalidCharException, DublicateWordException{
        //System.out.println(str);
        if(!checkStringInvalidSymbols(str)){
            String[] arrayWord=str.split(REG_PUNKT_SYMBOL);
            for (String word:arrayWord){
                if(!word.trim().equals("")) {
                   // System.out.println(word.trim());
                    if (!wordBook.addWord(word.trim())) {
                       // System.out.println("invalid in string: "+str+"\n invalid word:"+word.trim());
                        throw new DublicateWordException(word.trim());
                    }
                }
            }
        }
        else{
            //System.out.println("invalid in string "+str);
            throw new InvalidCharException(str);
        }

    }





    //public


}
