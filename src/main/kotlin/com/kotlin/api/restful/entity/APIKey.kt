package com.kotlin.api.restful.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "api_keys")
data class APIKey(

        @Id
        val id: String

)