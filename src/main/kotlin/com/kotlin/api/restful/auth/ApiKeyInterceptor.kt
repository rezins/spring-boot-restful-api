package com.kotlin.api.restful.auth

import com.kotlin.api.restful.error.UnauthorizedException
import com.kotlin.api.restful.repository.ApiKeyRepository
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class ApiKeyInterceptor(val apiKeyRepository: ApiKeyRepository) : WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val apiKey = request.getHeader("X-Api-Key")
        if(apiKey == null){
            throw UnauthorizedException()
        }

        if(!apiKeyRepository.existsById(apiKey)){
            throw UnauthorizedException()
        }
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        //
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        //
    }
}