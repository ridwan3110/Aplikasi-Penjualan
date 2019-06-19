/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ciwong
 */
public class Barang {
    
    private int barangId;
    private String nama;
    private double hargabeli;
    private double hargajual;
    private Date tanggal;
    private int jumlah;
    private Gudang gudang;
    private boolean cek;

    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }
    

    public int getBarangId() {
        return barangId;
    }

    public void setBarangId(int barangId) {
        this.barangId = barangId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHargabeli() {
        return hargabeli;
    }

    public void setHargabeli(double hargabeli) {
        this.hargabeli = hargabeli;
    }

    public double getHargajual() {
        return hargajual;
    }

    public void setHargajual(double hargajual) {
        this.hargajual = hargajual;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Gudang getGudang() {
        return gudang;
    }

    public void setGudang(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.barangId;
        hash = 79 * hash + Objects.hashCode(this.nama);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.hargabeli) ^ (Double.doubleToLongBits(this.hargabeli) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.hargajual) ^ (Double.doubleToLongBits(this.hargajual) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.tanggal);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.jumlah) ^ (Double.doubleToLongBits(this.jumlah) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.gudang);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Barang other = (Barang) obj;
        if (this.barangId != other.barangId) {
            return false;
        }
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        if (Double.doubleToLongBits(this.hargabeli) != Double.doubleToLongBits(other.hargabeli)) {
            return false;
        }
        if (Double.doubleToLongBits(this.hargajual) != Double.doubleToLongBits(other.hargajual)) {
            return false;
        }
        if (!Objects.equals(this.tanggal, other.tanggal)) {
            return false;
        }
        if (Double.doubleToLongBits(this.jumlah) != Double.doubleToLongBits(other.jumlah)) {
            return false;
        }
        if (!Objects.equals(this.gudang, other.gudang)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nama;
    }
    
    
    
}
