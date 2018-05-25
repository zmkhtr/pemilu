package com.sahabatpnj.pemilu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataRelawan {

    @SerializedName("id_relawan")
    @Expose
    private int idRelawan;
    @SerializedName("nama_relawan")
    @Expose
    private String namaRelawan;
    @SerializedName("alamat_relawan")
    @Expose
    private String alamatRelawan;
    @SerializedName("tanggal_lahir")
    @Expose
    private String tanggalLahir;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("id_partai")
    @Expose
    private String idPartai;

    public DataRelawan(int idRelawan, String namaRelawan, String alamatRelawan, String tanggalLahir, String jenisKelamin, String idPartai) {
        this.idRelawan = idRelawan;
        this.namaRelawan = namaRelawan;
        this.alamatRelawan = alamatRelawan;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.idPartai = idPartai;
    }

    public int getIdRelawan() {
        return idRelawan;
    }

    public void setIdRelawan(int idRelawan) {
        this.idRelawan = idRelawan;
    }

    public String getNamaRelawan() {
        return namaRelawan;
    }

    public void setNamaRelawan(String namaRelawan) {
        this.namaRelawan = namaRelawan;
    }

    public String getAlamatRelawan() {
        return alamatRelawan;
    }

    public void setAlamatRelawan(String alamatRelawan) {
        this.alamatRelawan = alamatRelawan;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getIdPartai() {
        return idPartai;
    }

    public void setIdPartai(String idPartai) {
        this.idPartai = idPartai;
    }

}