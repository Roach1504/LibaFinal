package com.predposhitay.android.library.api;


import com.predposhitay.android.library.model.MessageModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UmoriliApi {
    @FormUrlEncoded
    @POST("app/changeName.php")
    Call<ResponseBody> setName(@Field("nickname") String nickname,
                               @Field("id") String id);
    @FormUrlEncoded
    @POST("app/getMessages.php")
    Call<MessageModel> getMessage(@Field("last_message_id") String last_message_id,
                                  @Field("direction")String direction,
                                  @Field("limit")String limit);

    @FormUrlEncoded
    @POST("app/register.php")
    Call<ResponseBody> registUser(@Field("nickname") String nickname);

    @FormUrlEncoded
    @POST("app/sendMessage.php")
    Call<ResponseBody> sendMessage(@Field("message") String text,
                                   @Field("id") String id);
    @GET("wp-json/wp/v2/posts")
    Call<ResponseBody> getNews(@Query("categories") String categ,
                           @Query("page") String page);

    @GET("wp-json/wp/v2/media/{id}")
    Call<ResponseBody> getImage(@Path("id") String id);

}
