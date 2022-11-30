package com.example.car_park.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){

        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(),new Date(),ex.getMessage()
                ,request.getDescription(false));
        return message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage emailException(Exception ex, WebRequest request){

        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),"Email invalid"
                ,request.getDescription(false));
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage dbConnectException(Exception ex, WebRequest request){

        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),"Database connect error"
                ,request.getDescription(false));
        return message;
    }

    @ExceptionHandler(DayException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage dayException(DayException ex, WebRequest request){

        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),ex.getMessage()
                ,request.getDescription(false));
        return message;
    }



    @ExceptionHandler(PasswordException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage passwordException(PasswordException ex, WebRequest request){

        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),ex.getMessage()
                ,request.getDescription(false));
        return message;
    }



}
