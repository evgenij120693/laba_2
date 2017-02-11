package ru.svetozarov.lab_1.exceptions;

/**
 * Created by Шмыга on 09.02.2017.
 */
public class InvalidCharException extends Exception {
    public InvalidCharException(){
        super();
    }

    public InvalidCharException(String message) {
        super(message);
    }
}
