package com.tobedecided.dynamicdialer;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {
    //    @Headers({
//            "Authorization:Bearer K3EN5lhzEppboO5+mvwj5BClQ2jkKGGMmI0Bb+9ExkchIw2m9FRJzwg1as1/bRAGIY2dzQH02exWEwdVxoRGSQ==",
//            "Accept: application/json",
//            "Content-Type: application/json"
//    })
//    @POST("execute")
//    Call<GsonModels.Response> getPredictions(@Query("api-version") String version,
//                                             @Query("details") String details,
//                                             @Body RequestBody body);
    @POST("prediction")
    Call<GsonModels.BigMLPredictionsResponse> getPredictions(@Header("Content-Type") String content_type,
                                                             @Query("username") String username,
                                                             @Query("api_key") String api_key,
                                                             @Body RequestBody body);

    @POST("source")
    Call<GsonModels.BigMLSourceResponse> sendSource(@Header("Content-Type") String content_type,
                                                    @Query("username") String username,
                                                    @Query("api_key") String api_key,
                                                    @Body RequestBody body);

    @POST("dataset")
    Call<GsonModels.BigMLDatasetResponse> sendDataset(@Header("Content-Type") String content_type,
                                                      @Query("username") String username,
                                                      @Query("api_key") String api_key,
                                                      @Body RequestBody body);

    @POST("model")
    Call<GsonModels.BigMLModelResponse> sendModel(@Header("Content-Type") String content_type,
                                                  @Query("username") String username,
                                                  @Query("api_key") String api_key,
                                                  @Body RequestBody body);
}
