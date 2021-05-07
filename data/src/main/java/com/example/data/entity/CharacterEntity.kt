package com.example.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "data") val data: DataResponse?
)

@JsonClass(generateAdapter = true)
data class DataResponse(
    @Json(name = "offset") val offset: Int?,
    @Json(name = "total") val total: Int?,
    @Json(name = "results") val list: List<CharacterEntity>?
)

@JsonClass(generateAdapter = true)
data class CharacterEntity(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "thumbnail") val thumbnail: ThumbnailEntity?
)

@JsonClass(generateAdapter = true)
data class ThumbnailEntity(
    @Json(name = "path") val path: String?,
    @Json(name = "extension") val extension: String?
)