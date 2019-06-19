/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.Gudang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelGudang extends AbstractTableModel{
    
    List<Gudang> list = new ArrayList<>();

    public TableModelGudang() {
    }
    
    
    

    @Override
    public int getRowCount() {
    return list.size();
    }
    

    @Override
    public int getColumnCount() {
     
        return 4;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       switch (columnIndex){
           case 0 : return list.get(rowIndex).getGudangId();
           case 1 : return list.get(rowIndex).getNama();
           case 2 : return list.get(rowIndex).getKeterangan();
           case 3 : return list.get(rowIndex).isCek();
           default : return null;
       }
    }
    
    @Override
     public String getColumnName (int column){
        switch (column){
            case 0 : return "ID Gudang";
            case 1 : return "Nama Gudang";
            case 2 : return "Keterangan";
            case 3 : return "";
            default : return null;
        }
    }
     
     public List<Gudang> getGudangs(){
         List<Gudang> ls = new ArrayList<>();
         for (Gudang g : list){
             if (g.isCek()){
                 ls.add(g);
             }
         }
      return ls;   
     }
     
    @Override
     public boolean isCellEditable (int rowindex, int columnindex){
         return true;
     }
     
    @Override
     public void setValueAt (Object aValue, int rowindex, int columnindex){
         if (aValue!=null&&aValue instanceof Boolean&&columnindex==3){
             Boolean cek = (Boolean) aValue;
             list.get(rowindex).setCek(cek);
         }
     }
     
    @Override
     public Class<?> getColumnClass(int columnindex){
         if (columnindex==3){
             return Boolean.class;
         }
         else {
             return super.getColumnClass(columnindex);
         }
         
     }
     
     public void insertGudang(Gudang gudang){
         this.list.add(gudang);
         fireTableDataChanged();
     }
     
     public void updateGudang(int index, Gudang gudang){
         this.list.set(index, gudang);
         fireTableDataChanged();
     }
    
     public void deleteGudang(int index){
         this.list.remove(index);
         fireTableDataChanged();
     }
     
     public Gudang getGudang(int index){
         return list.get(index);
     }
     
     public void setData(List<Gudang> list){
         this.list = list;
         fireTableDataChanged();
     }
     
     public void clear (){
         list.clear();
     }
}
