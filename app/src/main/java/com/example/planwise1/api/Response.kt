package com.example.planwise1.api

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("last_page")
	val lastPage: Int? = null,

	@field:SerializedName("from")
	val from: Int? = null,

	@field:SerializedName("to")
	val to: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
)

data class DefaultImage(

	@field:SerializedName("license")
	val license: Int? = null,

	@field:SerializedName("regular_url")
	val regularUrl: String? = null,

	@field:SerializedName("license_name")
	val licenseName: String? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("small_url")
	val smallUrl: String? = null,

	@field:SerializedName("original_url")
	val originalUrl: String? = null,

	@field:SerializedName("medium_url")
	val mediumUrl: String? = null,

	@field:SerializedName("license_url")
	val licenseUrl: String? = null
)

data class DataItem(

	@field:SerializedName("sunlight")
	val sunlight: List<String?>? = null,

	@field:SerializedName("watering")
	val watering: String? = null,

	@field:SerializedName("scientific_name")
	val scientificName: List<String?>? = null,

	@field:SerializedName("other_name")
	val otherName: List<String?>? = null,

	@field:SerializedName("default_image")
	val defaultImage: DefaultImage? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("common_name")
	val commonName: String? = null,

	@field:SerializedName("cycle")
	val cycle: String? = null
)
