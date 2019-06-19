/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.Barang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelBarang extends AbstractTableModel{

    List<Barang> list = new ArrayList<>();
    public TableModelBarang() {
        
    }
    
    

    @Override
    public int getRowCount() {
      return list.size();
    }

    @Override
    public int getColumnCount() {
        return 8 ;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       switch (columnIndex){
           case 0 : return list.get(rowIndex).getBarangId();
           case 1 : return list.get(rowIndex).getNama();
           case 2 : return list.get(rowIndex).getHargabeli();
           case 3 : return list.get(rowIndex).getHargajual();
           case 4 : return list.get(rowIndex).getJumlah();
           case 5 : return list.get(rowIndex).getTanggal();
           case 6 : return list.get(rowIndex).getGudang();
           case 7 : return list.get(rowIndex).isCek();
           default : return null;
       }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowindex, int columnindex){
        if (aValue!=null&&aValue instanceof Boolean&&columnindex==7){
            Boolean cek = (Boolean) aValue;
            list.get(rowindex).setCek(cek);
        }
    }
    
    
    @Override
    public boolean isCellEditable (int rowindex, int columnindex){
        return true;
    }
    @Override
    public Class<?> getColumnClass(int columnindex){
        if (columnindex == 7){
            return Boolean.class;
        }
        else {
            return super.getColumnClass(columnindex);
            
        }
        
    }
    
    @Override
    public String getColumnName (int column){
        switch (column){
            case 0 : return "ID Barang";
            case 1 : return "Nama";
            case 2 : return "Hargabeli";
            case 3  : return "Harga Jual";
            case 4 : return "Jumlah";
            case 5 : return "Tanggal";
            case 6 : return "Gudang";
            case 7 : return "";
            default : return null;
        }
    }
    
    public void insertBarang (Barang barang){
        this.list.add(barang);
        fireTableDataChanged();
    }
    
    public void updateBarang (int index, Barang barang){
        this.list.set(index, barang);
        fireTableDataChanged();
    }
    
    public void deleteBarang (int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public void setData (List<Barang> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public Barang getBarang(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
    }
    
    public List<Barang> getBarangs(){
        List ls = new ArrayList();
        for (Barang b : list){
            if (b.isCek()){
               ls.add(b);
            }
        }
        return ls;
    }
}
