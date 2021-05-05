package com.example.data

import com.squareup.moshi.Json

data class ServerErrorEntity (
    @Json(name = "description") val description : String?,
    @Json(name = "errorCode") val errorCode : String,
    @Json(name = "fieldValidationErrors") val fieldValidationErrors : List<FieldValidationError>?
) {
    data class FieldValidationError (
        @Json(name = "field") val field : String,
        @Json(name = "message") val message : String
    )
}