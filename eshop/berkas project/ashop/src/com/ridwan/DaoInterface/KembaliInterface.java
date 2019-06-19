/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.DaoInterface;

import com.ridwan.Model.kembali;
import java.util.List;

/**
 *
 * @author ciwong
 */
public interface KembaliInterface {
    boolean insertReturn (kembali kembalian);
    List<kembali> getReturn();
    
}
