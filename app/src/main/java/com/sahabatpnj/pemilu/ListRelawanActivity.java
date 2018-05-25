package com.sahabatpnj.pemilu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListRelawanActivity extends AppCompatActivity {


    private List<DataRelawan> dataRelawan;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_relawan);

        recyclerView = findViewById(R.id.recyclerListRelawan);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataRelawan = new ArrayList<>();
        AndroidNetworking.initialize(getApplicationContext());
        getData();
    }

    public void getData(){
        AndroidNetworking.get("http://api.sahabatpnj.com/semua_relawan.php")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        {
                            //getting product object from json array
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject data = response.getJSONObject(i);
                                    //adding the product to product list
                                    dataRelawan.add(new DataRelawan(
                                            data.getInt("id_relawan"),
                                            data.getString("nama_relawan"),
                                            data.getString("alamat_relawan"),
                                            data.getString("tanggal_lahir"),
                                            data.getString("jenis_kelamin"),
                                            data.getString("id_partai")
                                    ));
                                }
                                RecyclerViewAdapter adapter = new RecyclerViewAdapter(ListRelawanActivity.this, dataRelawan);
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
                }

    }

