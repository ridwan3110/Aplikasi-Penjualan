/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Interface;

import com.ridwan.Model.BarangModel;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface BarangInterface {
    public void InsertBarang (BarangModel barangModel);
    public void UpdateBarang (BarangModel barangModel);
    public void DeleteBarang (BarangModel barangModel);
   BarangModel getById (int id);
    List<BarangModel> getBarangModels();
}
