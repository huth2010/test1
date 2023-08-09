package com.example.test1.data.network;

import com.example.test1.data.model.Comic;
import com.example.test1.data.model.Comment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService{
    //Post: https://64d25bcbf8d60b174361ef5f.mockapi.io/api/comic
    //GetAll: https://64d25bcbf8d60b174361ef5f.mockapi.io/api/comic
    //GetOne: https://64d25bcbf8d60b174361ef5f.mockapi.io/api/comic/:id
    //Delete: https://64d25bcbf8d60b174361ef5f.mockapi.io/api/comic/:id
    //put: https://64d25bcbf8d60b174361ef5f.mockapi.io/api/comic/:id

    @GET("api/comic/")
    Call<List<Comic>> getAllComic();

    @POST("api/comic")
    Call<Comic> postComic(@Body Comic comic);

    @GET("api/comic/{id}")
    Call<Comic> getComicById(@Path("id") String id,@Body Comic comic);

    @PUT("api/comic/{id}")
    Call<Comic> editComic(@Path("id") String id,@Body Comic comic);

    @DELETE("api/comic/{id}")
    Call<Comic> deleteComic(@Path("id") String id);

    //model: comment

    @GET("api/comment")
    Call<List<Comment>> getAllComment(@Query("id_comic") String idComic);


    @POST("api/comment")
    Call<Comment> postComment(@Body Comment comment);

    @GET("api/comment/{id}")
    Call<Comment> getCommentById(@Path("id") String id,@Body Comment comment);

    @PUT("api/comment/{id}")
    Call<Comment> editComment(@Path("id") String id,@Body Comment comment);

    @DELETE("api/comment/{id}")
    Call<Comment> deleteComment(@Path("id") String id);

    class Factory {
        static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
                .build();
        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64d25bcbf8d60b174361ef5f.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        public static ApiService create() {
            return retrofit.create(ApiService.class);
        }
    }
}
