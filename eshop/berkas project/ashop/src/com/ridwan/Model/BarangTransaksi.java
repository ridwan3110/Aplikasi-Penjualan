/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Model;

import java.util.Objects;

/**
 *
 * @author ciwong
 */
public class BarangTransaksi {
    private Barang barang;
    private Transaksi transaksi;
    private int jumlah;
    private double harga;

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.barang);
        hash = 53 * hash + Objects.hashCode(this.transaksi);
        hash = 53 * hash + this.jumlah;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.harga) ^ (Double.doubleToLongBits(this.harga) >>> 32));
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
        final BarangTransaksi other = (BarangTransaksi) obj;
        if (!Objects.equals(this.barang, other.barang)) {
            return false;
        }
        if (!Objects.equals(this.transaksi, other.transaksi)) {
            return false;
        }
        if (this.jumlah != other.jumlah) {
            return false;
        }
        if (Double.doubleToLongBits(this.harga) != Double.doubleToLongBits(other.harga)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BarangTransaksi{" + "barang=" + barang + ", transaksi=" + transaksi + ", jumlah=" + jumlah + ", harga=" + harga + '}';
    }
    
    
}
