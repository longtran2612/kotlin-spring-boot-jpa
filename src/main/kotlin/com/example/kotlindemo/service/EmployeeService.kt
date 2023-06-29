package com.example.kotlindemo.service

import com.example.kotlindemo.domain.entity.Employee
import com.example.kotlindemo.repository.EmployeeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EmployeeService(var employeeRepository: EmployeeRepository) {
    fun findAll() = employeeRepository.findAll()

    fun create(employee: Employee) = employeeRepository.save(employee)

    fun findById(id: Long) = employeeRepository.findById(id)


}