package com.example.kotlindemo.repository

import com.example.kotlindemo.domain.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long> {
}