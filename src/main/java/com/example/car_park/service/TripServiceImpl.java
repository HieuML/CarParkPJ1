package com.example.car_park.service;

import com.example.car_park.Exception.ResourceNotFoundException;
import com.example.car_park.entities.Trip;
import com.example.car_park.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TripServiceImpl implements TripService{


    @Autowired
    TripRepository tripRepository;

    @Override
    public Map<String, Object> getAllTripService(String destination, Date departureDate, Date departureTime, String driver, String carType, int page, int size) {


        List<Trip> trips ;

        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"id");
        Pageable pagingSort = PageRequest.of(page,size,Sort.by(order));
        Page<Trip> pageTrips;


        if(destination != null) {
            pageTrips = tripRepository.findByDestination(destination,pagingSort);

        }
        else if(departureDate != null) {
            pageTrips = tripRepository.findByDepartureDate(departureDate,pagingSort);

        }
        else if(departureTime != null) {
            pageTrips =tripRepository.findByDepartureTime(departureTime,pagingSort);

        }
        else if(driver != null) {
            pageTrips = tripRepository.findByDriver(driver,pagingSort);

        }
        else if(carType != null) {
            pageTrips = tripRepository.findByCarType(carType,pagingSort);

        }

        else
        {
            pageTrips = tripRepository.findAll(pagingSort);

        }
        trips = pageTrips.getContent();
        if(trips.isEmpty()) {
            throw new ResourceNotFoundException("No matches");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("trips", trips);
        response.put("currentPage", pageTrips.getNumber());
        response.put("totalIteams", pageTrips.getTotalElements());
        response.put("totalPages", pageTrips.getTotalPages());

        return response;

    }



    @Override
    public void createTripService(Trip trip) {
        tripRepository.save(new Trip(trip.getCarType(),trip.getDepartureDate(),trip.getDepartureTime()
                ,trip.getDestination(),trip.getDriver(),trip.getMaximumOnlineTicketNumber()));
    }

    @Override
    public Optional<Trip> getTripById(int id) {
        return tripRepository.findById(id);
    }

    @Override
    public Trip updateTripService(int id, Trip trip) {
        Optional<Trip> tripData = tripRepository.findById(id);
        if(tripData.isPresent()) {
            Trip _trip = tripData.get();
            _trip.setCarType(trip.getCarType());
            _trip.setDepartureDate(trip.getDepartureDate());
            _trip.setDepartureTime(trip.getDepartureTime());
            _trip.setDestination(trip.getDestination());
            _trip.setDriver(trip.getDriver());
            _trip.setMaximumOnlineTicketNumber(trip.getMaximumOnlineTicketNumber());
            return  tripRepository.save(_trip);
        }
        else {
            throw new ResourceNotFoundException("Không có trip có id là: " );
        }
    }

    @Override
    public void deleteTripService(int id) {
        Optional<Trip> tripData = tripRepository.findById(id);
        if(tripData.isPresent()) {
            tripRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Không có trip có id là " + id);
        }
    }


}
