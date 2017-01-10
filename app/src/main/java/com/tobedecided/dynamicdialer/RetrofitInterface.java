package com.tobedecided.dynamicdialer;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @Headers({
            "Authorization:Bearer K3EN5lhzEppboO5+mvwj5BClQ2jkKGGMmI0Bb+9ExkchIw2m9FRJzwg1as1/bRAGIY2dzQH02exWEwdVxoRGSQ==",
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("execute")
    Call<GsonModels.Response> getPredictions(@Query("api-version") String version,
                                             @Query("details") String details,
                                             @Body RequestBody body);
}
