package ru.svetozarov.lab_1.exceptions;

/**
 * Created by Шмыга on 09.02.2017.
 */

public class DublicateWordException extends Exception {
    public DublicateWordException() {
        super();
    }

    public DublicateWordException(String message) {
        super(message);
    }
}
