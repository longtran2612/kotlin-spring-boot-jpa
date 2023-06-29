package com.example.kotlindemo.domain.dto.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotNull
import lombok.*


@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class CreateUserRequest {
    @NotNull(message = "Name is required")
    var name: String? = null
    var email: String? = null
    var age: Int? = null
    var phone: String? = null
}