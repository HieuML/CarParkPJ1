package com.example.car_park.service;

import com.example.car_park.Exception.DayException;
import com.example.car_park.Exception.PasswordException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorService {

    public static void passwordCheck(String password) throws PasswordException {


        Pattern pUpper = Pattern.compile("^.*[A-Z]+.*$");
        Pattern pLower = Pattern.compile("^.*[a-z]+.*$");
        Pattern pDigit = Pattern.compile("^.*[0-9]+.*$");
        Pattern pLength = Pattern.compile("^.{6,}$");

        if(!(pUpper.matcher(password).find() && pLower.matcher(password).find()
                && pDigit.matcher(password).find() && pLength.matcher(password).find())){
            throw new PasswordException("Password must include uppercase, lowercase and number");

        }

    }

    public static void dateCheck(String date) throws DayException {
        DateValidator validator = new DateValidatorUsingDateFormat("dd/MM/yyyy");
        if(!validator.isValid(date)) throw new DayException("Nhập sai định dạng ngày, vui lòng nhập lại");

    }


}
