package com.example.car_park.repository;


import com.example.car_park.entities.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Integer> {

    @Query("SELECT t.destination, t.departureTime, t.driver, t.carType, t.bookedTicketNumber from Trip t")
    Page<Trip> findAll(Pageable pageable);

    @Query("SELECT t.destination, t.departureTime, t.driver, t.carType, t.bookedTicketNumber from Trip t where t.destination = ?1")
    Page<Trip> findByDestination(String destination, Pageable pageable);

//    List<Trip> findByDesination(String destination);

    @Query("SELECT t.destination, t.departureTime, t.driver, t.carType, t.bookedTicketNumber from Trip t where t.departureDate = ?1")
    Page<Trip> findByDepartureDate(Date departureDate, Pageable pageable);
    @Query("SELECT t.destination, t.departureTime, t.driver, t.carType, t.bookedTicketNumber from Trip t where t.departureTime = ?1")
    Page<Trip> findByDepartureTime(Date departureTime, Pageable pageable);

    @Query("SELECT t.destination, t.departureTime, t.driver, t.carType, t.bookedTicketNumber from Trip t where t.driver = ?1")
    Page<Trip> findByDriver(String driver, Pageable pageable);

    @Query("SELECT t.destination, t.departureTime, t.driver, t.carType, t.bookedTicketNumber from Trip t where t.carType = ?1")
    Page<Trip> findByCarType(String carType, Pageable pageable);


    List<Trip> findByDestination(String destination);
}
