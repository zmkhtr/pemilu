package com.sahabatpnj.pemilu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText mNama, mAlamat, mTanggaLahir;
    private RadioGroup mRadioGrup;
    private RadioButton mRadiobutton;
    private MaterialSpinner mSpinner;
    private Calendar mCalender;
    private SimpleDateFormat mSimpleDateFormat;
    private Integer id;
    private Button mButton, mButtonLihat;
    private ProgressBar mProgressBar;
    private JSONObject jsonObject;

    private String nama, alamat, tanggalLahir, jenisKelamin, partai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNama = findViewById(R.id.editTextRelawanNama);
        mAlamat = findViewById(R.id.editTextRelawanAlamat);
        mTanggaLahir = findViewById(R.id.editTextRelawanTanggalLahir);
        mRadioGrup = findViewById(R.id.radiGroupRelawanKelamin);
        mCalender = Calendar.getInstance();

        mProgressBar = findViewById(R.id.progressBarRelawanLoading);
        mButton = findViewById(R.id.buttonRelawanTambah);
        mSpinner = findViewById(R.id.spinnerRelawanPartai);
        mButtonLihat = findViewById(R.id.buttonRelawanLihat);

        //getValueRadioButton();
        //updateLabel();
        getDate();
        getValueSpinner();

        mProgressBar.setVisibility(View.INVISIBLE);
        AndroidNetworking.initialize(getApplicationContext());

        ekseskusiPerintah();
        AndroidNetworking.enableLogging();
        lihatData();
        mSpinner.setText("Pilih partai");

    }

    public void ekseskusiPerintah(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = mNama.getText().toString();
                alamat = mAlamat.getText().toString();
                tanggalLahir = mTanggaLahir.getText().toString();
                partai = mSpinner.getText().toString();
                if (nama.equals("") || alamat.equals("") || tanggalLahir.equals("") || partai.equals("Pilih partai")){
                    Toast.makeText(getApplicationContext(), "Semua kolom harus diisi" , Toast.LENGTH_SHORT).show();
                } else if (nama.equals(nama)&& alamat.equals(alamat)&& tanggalLahir.equals(tanggalLahir)&&
                        partai.equals(partai)) {
                    sendToDatabase();
                    mNama.setText("");
                    mAlamat.setText("");
                    mTanggaLahir.setText("");
                    mSpinner.setText("Pilih partai");
                }

            }
        });

    }
    public void sendToDatabase(){
        int selectedId = mRadioGrup.getCheckedRadioButtonId();
        mRadiobutton = findViewById(selectedId);
        mProgressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.post("http://api.sahabatpnj.com/tambah_data.php")
                .addBodyParameter("id_relawan", "")
                .addBodyParameter("nama_relawan", mNama.getText().toString())
                .addBodyParameter("alamat_relawan", mAlamat.getText().toString())
                .addBodyParameter("tanggal_lahir", mTanggaLahir.getText().toString())
                .addBodyParameter("jenis_kelamin", mRadiobutton.getText().toString())
                .addBodyParameter("id_partai", mSpinner.getText().toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        Toast.makeText(getApplicationContext(), "Berhasil tambah data" + response, Toast.LENGTH_SHORT).show();
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(getApplicationContext(), "Sukses tambah data" , Toast.LENGTH_SHORT).show();
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

    public void getValueSpinner(){
        mSpinner.setItems("PKS", "Gerinda", "PDIP", "PKB", "PAN", "Demokrat", "Perindo", "Golkar");
        mSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                partai = item;
                partai = mSpinner.getText().toString();
            }
        });
    }


    public void lihatData(){
        mButtonLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListRelawanActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getDate(){
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalender.set(Calendar.YEAR, year);
                mCalender.set(Calendar.MONTH, monthOfYear);
                mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        mTanggaLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, mCalender
                .get(Calendar.YEAR), mCalender.get(Calendar.MONTH), mCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
        mTanggaLahir.setText(mSimpleDateFormat.format(mCalender.getTime()));
        mTanggaLahir.getText().toString();
    }
}
