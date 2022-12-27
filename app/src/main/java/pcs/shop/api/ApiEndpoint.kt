package pcs.shop.api

import pcs.shop.response.login.LoginResponse
import pcs.shop.response.produk.ProdukResponse
import pcs.shop.response.produk.ProdukResponsePost
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiEndpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") password :String
    ) : Call<LoginResponse>

    @GET("produk")
    fun getProduk(@Header("Authorization") token : String) : Call<ProdukResponse>

    @FormUrlEncoded
    @POST("produk")
    fun postProduk(
        @Header("Authorization") token : String,
        @Field("admin_id") admin_id : Int,
        @Field("nama") nama :String,
        @Field("harga") harga :Int,
        @Field("stok") stok :Int
    ) : Call<ProdukResponsePost>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "produk", hasBody = true)
    fun deleteProduk(
        @Header("Authorization") token : String,
        @Field("id") id : Int
    ) : Call<ProdukResponsePost>

    @FormUrlEncoded
    @HTTP(method = "PUT", path = "produk", hasBody = true)
    fun putProduk(
        @Header("Authorization") token : String,
        @Field("admin_id") admin_id: Int,
        @Field("id") id : Int,
        @Field("nama") nama: String,
        @Field("harga") harga : Int,
        @Field("stok") stok : Int
    ) : Call<ProdukResponsePost>
}