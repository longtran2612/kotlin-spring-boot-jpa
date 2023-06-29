package com.example.kotlindemo.controller

import com.example.kotlindemo.domain.dto.request.CreateUserRequest
import com.example.kotlindemo.domain.dto.request.SearchUserRequest
import com.example.kotlindemo.service.UserService
import com.example.springoracledemo.domain.wrapper.WrapResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/api/user")
class UserController(var userService: UserService) {

    @GetMapping("/find-by-name/{name}")
    fun findByName(name: String) = userService.findByName(name)

    @GetMapping("/find-by-id/{id}")
    fun findById(id: Long) = userService.findById(id)

    @GetMapping("/find-all")
    fun findAll() = userService.findAll()

    @PostMapping(path= ["/create"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody createUserRequest: CreateUserRequest) : CompletableFuture<Any?> = CompletableFuture.supplyAsync {userService.create(createUserRequest)}

    @PostMapping(path = ["/search"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun search(@RequestBody searchUserRequest: SearchUserRequest): CompletableFuture<Any?> = CompletableFuture.supplyAsync { WrapResponse.ok(userService.search(searchUserRequest)) }

}