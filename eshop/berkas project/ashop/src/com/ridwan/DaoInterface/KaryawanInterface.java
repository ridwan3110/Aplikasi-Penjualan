/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DaoInterface;

import com.ridwan.Model.Karyawan;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface KaryawanInterface {
    
    boolean insert (Karyawan karyawan);
    void update (Karyawan karyawan);
    boolean delete (Karyawan karyawan);
    Karyawan getById(int id);
    List<Karyawan> getKaryawan();
    Karyawan login (String username, String password);
    boolean ubahPassword (String oldUsername, String oldPassword, String newUsername, String newPassword);
}
