package com.example.kotlindemo.service

import com.example.kotlindemo.base.BaseService
import com.example.kotlindemo.domain.entity.User
import com.example.kotlindemo.domain.dto.request.CreateUserRequest
import com.example.kotlindemo.domain.dto.request.SearchUserRequest
import com.example.kotlindemo.repository.UserRepository
import com.example.kotlindemo.utils.MappingUtils
import com.example.kotlindemo.utils.PageableUtils
import com.example.kotlindemo.utils.QueryBuilderUtils
import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.Predicate
import jakarta.validation.Valid
import jakarta.validation.Validator
import org.springframework.data.domain.Page
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.ArrayList

@Service
class UserService(
    var userRepository: UserRepository, entityManager: EntityManager,
    validator: Validator
) : BaseService(entityManager, validator) {

    fun search(request: SearchUserRequest): Page<User> {
        try {
            val pageable = PageableUtils.convertPageableAndSort(request.pageNumber, request.pageSize, request.sort)
            val criteriaBuilder = entityManager.criteriaBuilder
            val criteriaQuery = criteriaBuilder.createQuery(User::class.java)
            val root = criteriaQuery.from(User::class.java)
            val regex: MutableCollection<Predicate> = ArrayList()
            val filter: MutableCollection<Predicate> = ArrayList()
            QueryBuilderUtils.addMultipleRegexSearch(
                regex,
                criteriaBuilder,
                root,
                request.keyword,
                "name",
                "email",
                "phone"
            )
            QueryBuilderUtils.addSingleValueFilter(filter, criteriaBuilder, root, "name", request.name)
            QueryBuilderUtils.addSingleValueFilter(filter, criteriaBuilder, root, "email", request.email)
            QueryBuilderUtils.addSingleValueFilter(filter, criteriaBuilder, root, "phone", request.phone)
            QueryBuilderUtils.addSingleValueFilter(filter, criteriaBuilder, root, "age", request.age)
            criteriaQuery.where(*filter.toTypedArray(), *regex.toTypedArray())
            val query = entityManager.createQuery(criteriaQuery)
            query.firstResult = pageable.offset.toInt()
            query.maxResults = pageable.pageSize
            val result = query.resultList
            val countQuery = criteriaBuilder.createQuery(Long::class.java)
            val countRoot = countQuery.from(User::class.java)
            countQuery.select(criteriaBuilder.count(countRoot))
            countQuery.where(*filter.toTypedArray(), *regex.toTypedArray())
            return PageableExecutionUtils.getPage(result, pageable) { entityManager.createQuery(countQuery).singleResult }
        } catch (e: Exception) {
            println(e.message)
            return Page.empty();
        }
    }

    fun findByName(name: String) = userRepository.findByName(name)

    fun findById(id: Long) = userRepository.findById(id)

    fun findAll() = userRepository.findAll()

    @Transactional(rollbackFor = [Exception::class])
    fun create(@Valid request: CreateUserRequest): User {
        return userRepository.save(MappingUtils.mapObject(request, User::class.java))
    }

}