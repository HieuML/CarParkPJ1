package com.example.car_park.service;

import com.example.car_park.Exception.ResourceNotFoundException;
import com.example.car_park.entities.BookingOffice;
import com.example.car_park.entities.Trip;
import com.example.car_park.repository.BookingOfficeRepository;
import com.example.car_park.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingOfficeServiceImpl implements BookingOfficeService {

    @Autowired
    BookingOfficeRepository bookingOfficeRepository;

    @Autowired
    TripRepository tripRepository;

    @Override
    public void createBookingOffice(String destination, BookingOffice bookingOffice) {

        List<Trip> checkTripExist = tripRepository.findByDestination(destination);
        if(checkTripExist.isEmpty()) throw new ResourceNotFoundException("Không tồn tại trip này");

        bookingOffice.setTrip(checkTripExist.get(0));

        bookingOfficeRepository.save(bookingOffice);

    }
}
