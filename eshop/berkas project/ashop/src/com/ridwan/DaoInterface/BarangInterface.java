/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DaoInterface;

import com.ridwan.Model.Barang;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface BarangInterface {
    boolean insert (Barang barang);
    void update (Barang barang);
    boolean delete (Barang barang);
    void kurangJumlahstock(int jumlah, Barang barang);
    boolean tambahJumlahstock(int jumlah, Barang barang);
    Barang getById (int id);
    List<Barang> getBarang();
}
