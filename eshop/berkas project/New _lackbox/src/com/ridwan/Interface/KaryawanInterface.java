/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.KaryawanModel;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface KaryawanInterface {
    public void InsertKaryawan (KaryawanModel karyawanModel);
    public void UpdateKaryawan (KaryawanModel karyawanModel);
    public void DeleteKaryawan (KaryawanModel karyawanModel);
    KaryawanModel getById (int Id);
    List<KaryawanModel> getKaryawanModels();
}
