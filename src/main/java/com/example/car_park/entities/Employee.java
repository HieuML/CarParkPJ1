package com.example.car_park.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( length = 20)
    private int id;

    @Column(length = 50)
    private String account;

    @Column(length = 10)
    private String department;

    @Column(length = 50)
    private String address;

    public Employee() {
    }

    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date birthday;


    //    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Email(message = "Nhập sai định dạng email, vui lòng nhập lại", regexp = "^[a-zA-Z]+[a-zA-Z0-9]*@[a-zA-z]+mail.com$")
    @Column(length = 50)
    private String email;


    @NotBlank(message = "Name không được phép là null")
    @Column(length = 50)
    private String name;

    @Column(length = 10)
    private String phone;

    @Column(length = 20)

    private String password;

    @Column(length = 1)
    private String sex;

    public Employee(String account, String department, String address, Date birthday, String email, String name, String phone, String password, String sex) {
        this.account = account;
        this.department = department;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.sex = sex;
    }
}
