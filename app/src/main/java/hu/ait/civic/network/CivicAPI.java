package hu.ait.civic.network;

import hu.ait.civic.data.CivicResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CivicAPI {

    @GET("representatives")
    Call<CivicResult> getRepresentatives(@Query("address") String address,
                                         @Query("key") String key);
}

