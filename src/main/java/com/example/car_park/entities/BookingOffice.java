package com.example.car_park.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@Table(name = "bookingoffice")
public class BookingOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endContractDeadline;


    @Column(length = 50)
    private String officeName;

    @Column(length = 11)
    private String officePhone;

    @Column(length = 50)
    private String officePlace;

    @Column(length = 20)
    private int officePrice;

    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startContractDeadline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_id", nullable = false )
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Trip trip;

    public BookingOffice(Date endContractDeadline, String officeName, String officePhone, String officePlace, int officePrice, Date startContractDeadline) {
        this.endContractDeadline = endContractDeadline;
        this.officeName = officeName;
        this.officePhone = officePhone;
        this.officePlace = officePlace;
        this.officePrice = officePrice;
        this.startContractDeadline = startContractDeadline;
    }

    public BookingOffice() {
    }
}
