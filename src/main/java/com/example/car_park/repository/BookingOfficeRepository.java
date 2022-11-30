package com.example.car_park.repository;

import com.example.car_park.entities.BookingOffice;
import com.example.car_park.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BookingOfficeRepository extends JpaRepository<BookingOffice,Integer> {



}
