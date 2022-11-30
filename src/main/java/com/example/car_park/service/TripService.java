package com.example.car_park.service;

import com.example.car_park.entities.Employee;
import com.example.car_park.entities.Trip;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public interface TripService {

    public Map<String, Object> getAllTripService(String destination, Date departureDate, Date departureTime, String driver,
                                                 String carType, int page, int size);
    public void createTripService(Trip trip);

    public Optional<Trip> getTripById(int id);

    public Trip updateTripService(int id , Trip trip);

    public void deleteTripService(int id);


}
