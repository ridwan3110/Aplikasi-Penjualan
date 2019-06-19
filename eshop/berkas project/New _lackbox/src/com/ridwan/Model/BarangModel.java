/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Model;

import java.util.Date;

/**
 *
 * @author ciwong
 */
public class BarangModel {
    private int BarangId;
    private String NamaBarang;
    private String SerialNumber;
    private Date TanggalKeluar;
    private String UntukPerangkat;
    private String Stasiun;
    private KaryawanModel karyawan;

    public int getBarangId() {
        return BarangId;
    }

    public void setBarangId(int BarangId) {
        this.BarangId = BarangId;
    }

    public String getNamaBarang() {
        return NamaBarang;
    }

    public void setNamaBarang(String NamaBarang) {
        this.NamaBarang = NamaBarang;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String SerialNumber) {
        this.SerialNumber = SerialNumber;
    }

    public Date getTanggalKeluar() {
        return TanggalKeluar;
    }

    public void setTanggalKeluar(Date TanggalKeluar) {
        this.TanggalKeluar = TanggalKeluar;
    }

    public String getUntukPerangkat() {
        return UntukPerangkat;
    }

    public void setUntukPerangkat(String UntukPerangkat) {
        this.UntukPerangkat = UntukPerangkat;
    }

    public String getStasiun() {
        return Stasiun;
    }

    public void setStasiun(String Stasiun) {
        this.Stasiun = Stasiun;
    }

    public KaryawanModel getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(KaryawanModel karyawan) {
        this.karyawan = karyawan;
    }
    
}
