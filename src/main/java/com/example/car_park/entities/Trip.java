package com.example.car_park.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "trip")
public class Trip {
    public Trip() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( length = 20)
    private int id;

    @Column(length = 11)
    private int bookedTicketNumber;

    @Column(length = 11)
    private String carType;

    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date departureDate;



    @Temporal(value = TemporalType.TIME)
    @JsonFormat(pattern = "HH/mm/ss")
    private Date departureTime;

    @Column(length = 50)
    private String destination;

    @Column(length = 11)
    private String driver;

//    @Size(max = 11, message = "Please insert data lesser 11 number")
    @Column(length = 11)
    private int maximumOnlineTicketNumber;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trip")
//    private Set<BookingOffice> listBookingOffice = new HashSet<>();


    public Trip(String carType, Date departureDate, Date departureTime, String destination, String driver, int maximumOnlineTicketNumber) {
        this.carType = carType;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.driver = driver;
        this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
    }
}
