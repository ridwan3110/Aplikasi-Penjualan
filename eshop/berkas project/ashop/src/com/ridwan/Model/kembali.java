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
public class kembali {
    private int returnId;
    private Barang barang;
    private int jumlah;
    private Date tanggal;
    private String keterangan;

    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.returnId;
        hash = 53 * hash + Objects.hashCode(this.barang);
        hash = 53 * hash + this.jumlah;
        hash = 53 * hash + Objects.hashCode(this.tanggal);
        hash = 53 * hash + Objects.hashCode(this.keterangan);
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
        final kembali other = (kembali) obj;
        if (this.returnId != other.returnId) {
            return false;
        }
        if (!Objects.equals(this.barang, other.barang)) {
            return false;
        }
        if (this.jumlah != other.jumlah) {
            return false;
        }
        if (!Objects.equals(this.tanggal, other.tanggal)) {
            return false;
        }
        if (!Objects.equals(this.keterangan, other.keterangan)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kembali{" + "returnId=" + returnId + ", barang=" + barang + ", jumlah=" + jumlah + ", tanggal=" + tanggal + ", keterangan=" + keterangan + '}';
    }
    
    
    
    
    
}
