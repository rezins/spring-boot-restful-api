package com.kotlin.api.restful.config

import com.kotlin.api.restful.entity.APIKey
import com.kotlin.api.restful.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ApiKeySeeder(val apiKeyRepository: ApiKeyRepository) : ApplicationRunner {

    val apiKey = "SECRET"

    override fun run(args: ApplicationArguments?) {
        if(!apiKeyRepository.existsById(apiKey)){
            val entityKey = APIKey(id = apiKey)
            apiKeyRepository.save(entityKey)
        }
    }
}