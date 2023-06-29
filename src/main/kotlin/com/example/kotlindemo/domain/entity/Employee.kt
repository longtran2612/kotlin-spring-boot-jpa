package com.example.kotlindemo.domain.entity

import jakarta.persistence.*
import java.util.*

@Entity(name = "employees")
class Employee (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "employee_id")
        var id: Long? = null,
        @Column(name = "first_name")
        var firstName: String? = null,
        @Column(name = "last_name")
        var lastName: String? = null,
        @Column(name = "email")
        var email: String? = null,
        @Column(name = "phone_number")
        var phoneNumber: String? = null,
        @Column(name = "hire_date")
        var hireDate: Date? = null,
        @Column(name = "job_id")
        var jobId: String? = null,
        @Column(name = "salary")
        var salary: Long? = null,
        @Column(name = "commission_pct")
        var commissionPct: Float? = null,
        @Column(name = "department_id")
        var departmentId: Long? = null

)