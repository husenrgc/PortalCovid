package com.ciktech.portalcovid.trx;

import com.ciktech.portalcovid.models.Prov;
import com.ciktech.portalcovid.models.ResponseCovid19;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("indonesia")
    Call<List<ResponseCovid19>> get_covid19_indonesia();

    @GET("indonesia/provinsi")
    Call<String> get_provinsi();


}
