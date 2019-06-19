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
public class Gudang {
    private int gudangId;
    private String nama;
    private String keterangan;
    private boolean cek;

    public boolean isCek() {
        return cek;
    }

    public void setCek(boolean cek) {
        this.cek = cek;
    }

    public int getGudangId() {
        return gudangId;
    }

    public void setGudangId(int gudangId) {
        this.gudangId = gudangId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.gudangId;
        hash = 37 * hash + Objects.hashCode(this.nama);
        hash = 37 * hash + Objects.hashCode(this.keterangan);
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
        final Gudang other = (Gudang) obj;
        if (this.gudangId != other.gudangId) {
            return false;
        }
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        if (!Objects.equals(this.keterangan, other.keterangan)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nama;
    }
    
    
    
    
}
