package com.ciktech.portalcovid;



import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ciktech.portalcovid.adapter.RecyclerReg;
import com.ciktech.portalcovid.models.Prov;
import com.ciktech.portalcovid.trx.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class CovidRegion extends AppCompatActivity {
    private RecyclerReg viewAdapter;
    ProgressBar mProgressbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_region);
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));



        load();
    }
    private void load(){

        // Create a very simple REST adapter which points the GitHub API endpoint.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.kawalcorona.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        ApiInterface client = retrofit.create(ApiInterface.class);
        // Fetch and print a list of the contributors to this library.
        Call<String> call = client.get_provinsi();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }



    private void writeRecycler(String response){

        try {
            //getting the whole json object from the response
            JSONArray dataArray = new JSONArray(response);



                ArrayList<Prov> modelRecyclerArrayList = new ArrayList<>();
            ArrayList<HashMap<String, String>> data = new ArrayList<>();

                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    String attr =dataobj.getString("attributes");
                    JSONObject dataobj2 = new JSONObject(attr);
                    Prov modelRecycler = new Prov();
                     boolean found = false;
                    if (dataobj2.getString("Provinsi").equals("DKI Jakarta")) {
                              found =true;
                            Log.e("s", "prov bro "+ dataobj2.getString("Kasus_Posi"));
                    }


                    if (dataobj2.has("Provinsi")) {
                        modelRecycler.setProvinsi(dataobj2.getString("Provinsi"));
                        Log.e("s", "prov bro "+ dataobj2.getString("Provinsi"));
                    }
                    if (dataobj2.has("Kasus_Semb")){
                        modelRecycler.setKasus_Semb(dataobj2.getString("Kasus_Semb"));
                    }
                    if (dataobj2.has("Kasus_Meni")){
                        modelRecycler.setKasus_Meni(dataobj2.getString("Kasus_Meni"));
                    }
                    if (dataobj2.has("Kasus_Posi")){
                        modelRecycler.setKasus_Posi(dataobj2.getString("Kasus_Posi"));
                    }

                    HashMap<String,String> dl = new HashMap<>();
                    dl.put("prov", dataobj2.getString("Provinsi"));
                    dl.put("ks", dataobj2.getString("Kasus_Semb"));
                    dl.put("km", dataobj2.getString("Kasus_Meni"));
                    dl.put("kp", dataobj2.getString("Kasus_Posi"));

                       data.add(dl);

                    viewAdapter = new RecyclerReg(this,modelRecyclerArrayList);

                    recyclerView.setAdapter(viewAdapter);
                   

            }
            Log.e("s", "prov bro "+data.get(2).get("prov"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
