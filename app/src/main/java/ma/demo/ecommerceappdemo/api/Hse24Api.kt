package ma.demo.ecommerceappdemo.api

import com.google.gson.JsonElement
import ma.demo.ecommerceappdemo.model.Category
import ma.demo.ecommerceappdemo.model.Product
import ma.demo.ecommerceappdemo.model.ProductResult
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Elmehdi Mellouk on 8/7/20.
 * API communication setup
 * elmehdi.mellouk@xpi.com
 */
 interface Hse24Api {


    @Headers("appDevice: ANDROID_PHONE","locale:de_DE")
    @GET("/ext-api/app/1/category/tree")
    suspend fun getCategoryTree(): Category

    @Headers("appDevice: ANDROID_PHONE","locale:de_DE")
    @GET("/ext-api/app/1/c/**/*-{idCategory}")
    suspend fun getProducts(@Path("idCategory") idCategory: String): ProductResult


    /**
     * Result : 400 Bad request
     */

    @Headers("appDevice: ANDROID_PHONE","locale:de_DE")
    @GET("/ext-api/app/1/c/**/*-\\{{idCategory}:[{start}-{end}]\\}")
    suspend fun getProductsByPagination(@Path("idCategory") idCategory: String,
    @Path("start") start:String,@Path("end") end:String): ProductResult

    @Headers("appDevice: ANDROID_PHONE","locale:de_DE")
    @GET("/ext-api/app/1/c/**/*-{idCategory}")
    suspend fun getJsonProducts(@Path("idCategory") idCategory: String): JsonElement

    @Headers("appDevice: ANDROID_PHONE","locale:de_DE")
    @GET("/ext-api/app/1/product/{idProduct}")
    suspend fun getDetailProduct(@Path("idProduct") idProduct: String): Product

    companion object {
        private const val BASE_URL = "https://www.hse24.de"
        const val BASE_URL_IMAGE = "https://pic.hse24-dach.net/media/de/products/"
        fun create(): Hse24Api {

            val client = OkHttpClient.Builder()
                .build()
            return Retrofit.Builder()
                .baseUrl(HttpUrl.parse(BASE_URL)!!)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Hse24Api::class.java)
        }
    }
}