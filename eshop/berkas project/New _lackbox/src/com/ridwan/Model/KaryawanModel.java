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
public class KaryawanModel {
    private int KaryawanId;
    private String Nama;
    private String Status;

    public int getKaryawanId() {
        return KaryawanId;
    }

    public void setKaryawanId(int KaryawanId) {
        this.KaryawanId = KaryawanId;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    

    @Override
    public String toString() {
        return Nama;
    }
    
    
}
