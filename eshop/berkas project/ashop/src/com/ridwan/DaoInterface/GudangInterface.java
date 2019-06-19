/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DaoInterface;

import com.ridwan.Model.Gudang;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface GudangInterface {
   boolean insert (Gudang gudang);
    void update (Gudang gudang);
    boolean delete (Gudang gudang);
    Gudang getById(int id);
    List<Gudang> getGudangs();
    
}
