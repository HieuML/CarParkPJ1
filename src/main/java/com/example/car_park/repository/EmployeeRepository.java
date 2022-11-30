package com.example.car_park.repository;

import com.example.car_park.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


    @Query("SELECT t.id, t.name, t.birthday, t.address, t.phone, t.department from Employee t")
    Page<Employee> findAll(Pageable pageable);


    //Truyền tham số bằng số thứ tự
    //JPQL
    @Query("SELECT t.id, t.name, t.birthday, t.address, t.phone, t.department from Employee t where t.name like %?1%")
    Page<Employee> findByNameLike(String name, Pageable pageable);
    @Query("SELECT t.id, t.name, t.birthday, t.address, t.phone, t.department from Employee t where t.birthday= ?1")

    Page<Employee> findByBirthday(Date birthday, Pageable pageable);

    //Truyền tham số bằng tên
    //Native SQL
    @Query(value = "SELECT * from Employee where address like %:address% ", nativeQuery = true)

    Page<Employee> findByAddressContaining(@Param("address") String address, Pageable pageable);

    @Query(value = "SELECT t.id, t.name, t.birthday, t.address, t.phone, t.department from Employee t where t.phone= :phone")

    Page<Employee> findByPhone(@Param("phone") String phone, Pageable pageable);

    @Query(value = "SELECT t.id, t.name, t.birthday, t.address, t.phone, t.department from Employee t where t.department= :department")

    Page<Employee> findByDepartment(@Param("department") String department, Pageable pageable);

}
