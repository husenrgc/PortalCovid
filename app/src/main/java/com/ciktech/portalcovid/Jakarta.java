package com.ciktech.portalcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;





import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.ciktech.portalcovid.models.Prov;
import com.ciktech.portalcovid.models.ResponseCovid19;
import com.ciktech.portalcovid.trx.ApiInterface;
import com.ciktech.portalcovid.trx.ServiceGenerator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Jakarta extends AppWidgetProvider {

    RemoteViews views;



    static void updateAppWidget(Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.jabar);
        // Construct the RemoteViews object
        final RemoteViews viewss = new RemoteViews(context.getPackageName(), R.layout.activity_jakarta);

        ArrayList<HashMap<String, String>> data;
        // Create a very simple REST adapter which points the GitHub API endpoint.
        // Create a very simple REST adapter which points the GitHub API endpoint.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.kawalcorona.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        ApiInterface client2 = retrofit.create(ApiInterface.class);
        // Fetch and print a list of the contributors to this library.
        Call<String> call2 = client2.get_provinsi();

        call2.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {

                    String jsonresponse = response.body().toString();

                    JSONArray dataArray = new JSONArray(jsonresponse);
                    ArrayList<HashMap<String, String>> rov = new ArrayList<>();

                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject dataobj = dataArray.getJSONObject(i);
                        String attr =dataobj.getString("attributes");
                        JSONObject dataobj2 = new JSONObject(attr);
                        Prov modelRecycler = new Prov();
                        boolean found = false;


                        HashMap<String,String> dl = new HashMap<>();
                        dl.put("prov", dataobj2.getString("Provinsi"));
                        dl.put("ks", dataobj2.getString("Kasus_Semb"));
                        dl.put("km", dataobj2.getString("Kasus_Meni"));
                        dl.put("kp", dataobj2.getString("Kasus_Posi"));


                        rov.add(dl);



                    }
                    viewss.setTextViewText(R.id.positif2,rov.get(0).get("kp"));
                    viewss.setTextViewText(R.id.sembuh2, rov.get(0).get("ks"));
                    viewss.setTextViewText(R.id.meninggal2, rov.get(0).get("km"));
                    Log.e("s", "prov bro "+ rov.get(0).get("km"));

                    appWidgetManager.updateAppWidget(appWidgetId, viewss);


                }catch (Exception e) {
                    e.printStackTrace();
                } }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });




    }

    private void get_covid19_indonesia(Context context){

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


