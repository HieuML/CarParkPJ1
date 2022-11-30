package com.example.car_park.controller;

import com.example.car_park.Exception.PasswordException;
import com.example.car_park.Exception.ResourceNotFoundException;
import com.example.car_park.entities.Employee;
import com.example.car_park.entities.Trip;
import com.example.car_park.service.TripService;
import com.example.car_park.service.ValidatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class TripController {

    @Autowired
    TripService tripService;

    @GetMapping("/trips")
    public ResponseEntity<Map<String,Object>> getAllTrips(@RequestParam(required = false) String destination,
                                                          @RequestParam(required = false) Date departureDate,
                                                          @RequestParam(required = false) Date departureTime,
                                                              @RequestParam(required = false) String driver,
                                                              @RequestParam(required = false) String carType,
                                                              @RequestParam(defaultValue = "0" ) int page,
                                                              @RequestParam(defaultValue = "3") int size
    ) throws ResourceNotFoundException{


        Map<String,Object> response = tripService.getAllTripService(destination,departureDate,departureTime,driver,carType,page,size);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PostMapping("/trips")
    public ResponseEntity<String> createTrip( @RequestBody Trip trip)  {
        tripService.createTripService(trip);
        return new ResponseEntity<>("Add trip successfully", HttpStatus.OK);


    }
    @GetMapping("/trips/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable int id) {

        Optional<Trip> tripData = tripService.getTripById(id);

        if (tripData.isPresent()) {
            return new ResponseEntity<>(tripData.get(), HttpStatus.OK);
        } else {
            throw  new ResourceNotFoundException("Not found Trip with id = " + id);
        }
    }

    @PutMapping("/trips/{id}")
    public ResponseEntity<String> updateTrip(@PathVariable int id, @RequestBody Trip trip) {

        tripService.updateTripService(id,trip);
        return new ResponseEntity<>("Update seccesfully",HttpStatus.OK);

    }
    @DeleteMapping("/trips/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable int id) throws ResourceNotFoundException {

        tripService.deleteTripService(id);

        return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }
}
