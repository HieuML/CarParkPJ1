package com.example.car_park.controller;


import com.example.car_park.Exception.DayException;
import com.example.car_park.Exception.PasswordException;
import com.example.car_park.entities.BookingOffice;
import com.example.car_park.entities.Employee;
//import com.example.car_park.service.BookingOfficeService;
import com.example.car_park.service.BookingOfficeService;
import com.example.car_park.service.TripService;
import com.example.car_park.service.ValidatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api")
public class BookingOfficeController {

    @Autowired
    BookingOfficeService bookingOfficeService;


    @PostMapping("/trips/{destination}/bookingoffices")
    public ResponseEntity<String> createBookingOffice( @PathVariable String destination,@RequestBody BookingOffice bookingOffice)  {

        bookingOfficeService.createBookingOffice(destination,bookingOffice);

        return new ResponseEntity<>("Add booking office successfully",HttpStatus.OK);


    }




}
