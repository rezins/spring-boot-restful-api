package com.kotlin.api.restful.repository

import com.kotlin.api.restful.entity.APIKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository : JpaRepository<APIKey, String> {
}