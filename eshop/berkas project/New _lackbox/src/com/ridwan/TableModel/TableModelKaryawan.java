/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Dao.KaryawanDao;
import com.ridwan.Model.KaryawanModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelKaryawan extends AbstractTableModel{
    
    
    List<KaryawanModel> list = new ArrayList<>();
            
            

    @Override
    public int getRowCount() {
      return list.size();
    }

    @Override
    public int getColumnCount() {
    return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0 : return list.get(rowIndex).getKaryawanId();
            case 1 : return list.get(rowIndex).getNama();
            case 2 : return list.get(rowIndex).getStatus();
            default : return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch (column){
            case 0 : return "Karyawan ID";
            case 1 : return "Nama Karyawan";
            case 2 : return "Status Karyawan";
            default : return null;
        }
       
    }
    
    
    public void InsertKaryawan (KaryawanModel km){
        this.list.add(km);
        fireTableDataChanged();
    }
    
    public void UpdateKaryawan (int index , KaryawanModel km){
        this.list.set(index, km);
        fireTableDataChanged();
    }
    
    public void DeleteKaryawan (int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public KaryawanModel getKaryawan (int index){
        return list.get(index);
    }
    
    public void setData (List<KaryawanModel> list ){
        this.list = list;
        fireTableDataChanged();
    }
    
    public void clear (){
        list.clear();
        fireTableDataChanged();
    }
    
}
