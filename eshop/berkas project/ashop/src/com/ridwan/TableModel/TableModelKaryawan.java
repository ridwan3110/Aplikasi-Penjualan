/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.Karyawan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelKaryawan extends AbstractTableModel {
    
    List<Karyawan> list = new ArrayList<>();

    public TableModelKaryawan() {
    }
    

    @Override
    public int getRowCount() {
       return list.size();
    }

    @Override
    public int getColumnCount() {
     
        return 7 ;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
     switch (columnIndex){
         case 0 : return list.get(rowIndex).getKaryawanId();
         case 1 : return list.get(rowIndex).getUsername();
         case 2 : return list.get(rowIndex).getNama();
         case 3 : return list.get(rowIndex).getStatus();
         case 4 : return list.get(rowIndex).getTelepon();
         case 5 : return list.get(rowIndex).getAlamat();
         case 6 : return list.get(rowIndex).isCek();
         default : return null;
     }
    }
    
    @Override
    public String getColumnName(int column){
        switch (column){
            case 0 : return "Kode Karyawan";
            case 1 : return "Username";
            case 2 : return "Nama";
            case 3 : return "Status";
            case 4 : return "Telepon";
            case 5 : return "Alamat";
            case 6 : return "";
            default : return null;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowindex, int columnindex){
        if (aValue!=null&&aValue instanceof Boolean&&columnindex==6){
            Boolean cek = (Boolean) aValue;
            list.get(rowindex).setCek(cek);
        }
        
    }
    
    
    @Override
    public Class<?> getColumnClass(int columnindex){
        if (columnindex == 6){
            return Boolean.class;
        }else {
            return super.getColumnClass(columnindex);
        }
        
    }
    
    public List<Karyawan> getkKaryawans(){
        List ls = new ArrayList();
        for (Karyawan k : list){
            if (k.isCek()){
                ls.add(k);
            }
        }
        return ls;
    }
    
    
    
    
    @Override
    public boolean isCellEditable (int rowindex, int columnindex){
     return true;   
    }
    
    
    
    public void insertKaryawan(Karyawan karyawan){
        this.list.add(karyawan);
        fireTableDataChanged();
    }
    
    public void updateKaryawan(int index, Karyawan karyawan){
        this.list.set(index, karyawan);
        fireTableDataChanged();
    }
    
    
    public void deleteKaryawan (int index){
        this.list.remove(index);
        fireTableDataChanged();;
    }
    
    public Karyawan getKaryawan (int index){
        return list.get(index);
    }
    
    public void setData (List<Karyawan> list){
        this.list  = list;
        fireTableDataChanged();
        
    }
    
    public void clear (){
        list.clear();
    }
    
    
    
}
