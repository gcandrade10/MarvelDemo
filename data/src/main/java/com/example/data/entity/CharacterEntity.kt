package com.example.data.entity

import com.squareup.moshi.Json

data class CharacterResponse(
    @Json(name = "data") val data: DataResponse?
)

data class DataResponse(
    @Json(name = "offset") val offset: Int?,
    @Json(name = "total") val total: Int?,
    @Json(name = "results") val list: List<CharacterEntity>?
)

data class CharacterEntity(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "thumbnail") val thumbnail: ThumbnailEntity?
)

data class ThumbnailEntity(
    @Json(name = "path") val path: String?,
    @Json(name = "extension") val extension: String?
)