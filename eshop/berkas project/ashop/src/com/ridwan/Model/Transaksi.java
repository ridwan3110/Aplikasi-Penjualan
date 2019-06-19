/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ciwong
 */
public class Transaksi {
    private int transaksiId;
    private double ppn;
    private Date tanggal;
    private Karyawan karyawan;
    private List<BarangTransaksi> barangTransaksi;

    public int getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(int transaksiId) {
        this.transaksiId = transaksiId;
    }

    public double getPpn() {
        return ppn;
    }

    public void setPpn(double ppn) {
        this.ppn = ppn;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public List<BarangTransaksi> getBarangTransaksi() {
        return barangTransaksi;
    }

    public void setBarangTransaksi(List<BarangTransaksi> barangTransaksi) {
        this.barangTransaksi = barangTransaksi;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.transaksiId;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.ppn) ^ (Double.doubleToLongBits(this.ppn) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.tanggal);
        hash = 43 * hash + Objects.hashCode(this.karyawan);
        hash = 43 * hash + Objects.hashCode(this.barangTransaksi);
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
        final Transaksi other = (Transaksi) obj;
        if (this.transaksiId != other.transaksiId) {
            return false;
        }
        if (Double.doubleToLongBits(this.ppn) != Double.doubleToLongBits(other.ppn)) {
            return false;
        }
        if (!Objects.equals(this.tanggal, other.tanggal)) {
            return false;
        }
        if (!Objects.equals(this.karyawan, other.karyawan)) {
            return false;
        }
        if (!Objects.equals(this.barangTransaksi, other.barangTransaksi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transaksi{" + "transaksiId=" + transaksiId + ", ppn=" + ppn + ", tanggal=" + tanggal + ", karyawan=" + karyawan + ", barangTransaksi=" + barangTransaksi + '}';
    }
    
    
    
}
