package ru.svetozarov.lab_1.exceptions;

/**
 * Класс исключения, возникающего при обнаружении @{@link ru.svetozarov.lab_1.handler_file.Parser}
 * дублирующего слова
 * @author Evgenij Svetozarov
 */

public class DublicateWordException extends Exception {

    public DublicateWordException(String message) {
        super(message);
    }
}
