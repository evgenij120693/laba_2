package ru.svetozarov.lab_1.handler_file;

import ru.svetozarov.lab_1.WordBook;
import ru.svetozarov.lab_1.exceptions.DublicateWordException;
import ru.svetozarov.lab_1.exceptions.InvalidCharException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс осуществляет проверку и парсинг строк
 * @author Evgenij Svetozarov
 */
public class Parser {
    private static final String REG_INVALID_SYMBOL="[A-Za-z$%^&*_+|~=`{}\\[\\]\\\"'<>\\/]";
    private static final String REG_PUNKT_SYMBOL="[!,.;():?\uFEFF ]|(\\-|—)(?![А-я0-9\\\\n])|( )?(\\-|—)?(0-9)";

    private static boolean checkStringInvalidSymbols(String str){
        Pattern p = Pattern.compile(REG_INVALID_SYMBOL);
        Matcher m = p.matcher(str);
        return m.find();
    }



    private static  String replaceCharPunct(String str){
        Pattern p = Pattern.compile(REG_PUNKT_SYMBOL);
        Matcher m = p.matcher(str);
        return m.replaceAll(REG_PUNKT_SYMBOL);
    }

    /**
     * Функция проверяет входную строку str на наличие запрещенных символов,
     * при обнаружении последнего кидает InvalidCharException, далее делит строку на
     * слова и вставляет их в словарь wordBook, в случае неудачи кидает DublicateWordException
     * @param str строка, которую проверяем и парсим
     * @param wordBook словарь, в которой добавляем слова
     * @throws InvalidCharException исключение, вызываемое при обнаружении запрещенного символа
     * @throws DublicateWordException исключение, вызываемое при дублиловании слова
     */
    public static void parseString(String str, WordBook wordBook) throws InvalidCharException, DublicateWordException{
        if(!checkStringInvalidSymbols(str)){
            String[] arrayWord=str.split(REG_PUNKT_SYMBOL);
            for (String word:arrayWord){
                if(!word.trim().equals("")) {
                    if (!wordBook.addWord(word.trim())) {
                        throw new DublicateWordException(word.trim());
                    }
                }
            }
        }
        else{
            throw new InvalidCharException(str);
        }

    }
}
