package ru.svetozarov.lab_1.exceptions;

/**
 * @author Evgenij Svetozarov
 * Класс исключения, возникающего при обнаружении @{@link ru.svetozarov.lab_1.handler_file.Parser}
 * дублирующего слова
 */

public class DublicateWordException extends Exception {
    public DublicateWordException() {
        super();
    }

    public DublicateWordException(String message) {
        super(message);
    }
}
