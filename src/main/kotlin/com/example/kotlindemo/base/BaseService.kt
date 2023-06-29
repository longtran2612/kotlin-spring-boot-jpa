package com.example.kotlindemo.base

import jakarta.persistence.EntityManager
import jakarta.validation.Validator

/**
 * 9:42 PM 18-Feb-23
 * Long Tran
 */
open class BaseService(var entityManager: EntityManager, private var validator: Validator) :
    BaseLogger() {

    fun validate(`object`: Any?) {
        val violations = validator.validate(`object`)
        logger?.info("Violations: {}", violations)
        if (violations.isNotEmpty()) {
            throw RuntimeException("validate error")
        }

    }
}