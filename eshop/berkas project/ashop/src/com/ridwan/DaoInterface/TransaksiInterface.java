/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DaoInterface;

import com.ridwan.Model.ReportTransaksi;
import com.ridwan.Model.Transaksi;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface TransaksiInterface {
    boolean insertTransaksi (Transaksi transaksi);
    List<ReportTransaksi> getTransaksi();
    List<ReportTransaksi> getTransaksi(Date tglMulai, Date tglAkhir);
    List<ReportTransaksi> getTransaksi(Transaksi transaksi);
    int setTransaksiId();
}
