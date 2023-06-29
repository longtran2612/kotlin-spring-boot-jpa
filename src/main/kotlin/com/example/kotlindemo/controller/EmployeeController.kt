package com.example.kotlindemo.controller

import com.example.kotlindemo.domain.entity.Employee
import com.example.kotlindemo.service.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/employee")
class EmployeeController(var employeeService: EmployeeService) {

    @GetMapping("/find-all")
    fun findAll() = employeeService.findAll()

    @GetMapping("/find-by-id/{id}")
    fun findById(id: Long) = employeeService.findById(id)

    @PostMapping("/create")
    fun create(@RequestBody employee: Employee) = employeeService.create(employee)

}