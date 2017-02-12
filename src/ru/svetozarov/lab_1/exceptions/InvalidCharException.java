package ru.svetozarov.lab_1.exceptions;

/**
 * Класс исключения, возникающего при обнаружении @{@link ru.svetozarov.lab_1.handler_file.Parser}
 * запрещенного символа
 * @author Evgenij Svetozarov
 */
public class InvalidCharException extends Exception {

    public InvalidCharException(String message) {
        super(message);
    }
}
