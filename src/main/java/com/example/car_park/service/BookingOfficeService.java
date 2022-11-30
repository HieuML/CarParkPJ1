package com.example.car_park.service;

import com.example.car_park.entities.BookingOffice;
import org.springframework.stereotype.Service;

@Service
public interface BookingOfficeService {


    public void createBookingOffice(String destination,BookingOffice bookingOffice);
}
