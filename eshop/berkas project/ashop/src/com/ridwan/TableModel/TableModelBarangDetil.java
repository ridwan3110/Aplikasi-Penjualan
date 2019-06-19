/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.Barang;
import com.ridwan.Model.BarangTransaksi;
import com.ridwan.Model.Transaksi;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelBarangDetil extends AbstractTableModel{
    
    List<BarangTransaksi> list = new ArrayList<>();

    public TableModelBarangDetil() {
    }
    
    
    

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
           case 0 : return list.get(rowIndex).getBarang();
           case 1 : return list.get(rowIndex).getJumlah();
           case 2 : return list.get(rowIndex).getHarga();
           default : return null;
       }
    }
    
    
    
     
    @Override
     public String getColumnName (int column){
        switch (column){
            case 0 : return "Barang";
            case 1 : return "Jumlah";
            case 2 : return "Harga";
            default : return null;
        }
    }
     
     public void insertBarangTransaksi(BarangTransaksi barangTransaksi){
         this.list.add(barangTransaksi);
         fireTableDataChanged();
     }
     
     public void updateBarangTransaksi(int index, BarangTransaksi barangTransaksi){
         this.list.set(index, barangTransaksi);
         fireTableDataChanged();
     }
    
     public void deleteBarang(int index){
         this.list.remove(index);
         fireTableDataChanged();;
     }
     
     public BarangTransaksi getBarangTransaksi(int index){
         return list.get(index);
     }
     
     public void setData(List<BarangTransaksi> list){
         this.list = list;
         fireTableDataChanged();
     }
     
     public void clear (){
         list.clear();
         fireTableDataChanged();
     }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
      switch (columnIndex){
          case 0 : return Barang.class;
          case 1 : return Integer.class;
          case 2 : return Double.class;
          default : return Object.class;
      }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    return true;
    }
 
    
    public Double hitungHarga(int rowIndex){
        Double harga =0.0;
        if (list.get(rowIndex).getBarang()!=null){
            double barang=list.get(rowIndex).getBarang().getHargajual();
            Integer jumlah=list.get(rowIndex).getJumlah();
            harga = barang*jumlah;
            
            
        }
        return harga;
    }
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
     if (aValue!=null &&aValue instanceof Barang&&columnIndex==0){
         Barang barang = (Barang) aValue;
         list.get(rowIndex).setBarang(barang);
         Double hitungHarga = hitungHarga(rowIndex);
         list.get(rowIndex).setHarga(hitungHarga);
     }
    if (aValue!=null&&aValue instanceof Integer&&columnIndex==1){
        Integer jumlah = (Integer) aValue;
        list.get(rowIndex).setJumlah(jumlah);
        Double hitungHarga = hitungHarga(rowIndex);
        list.get(rowIndex).setHarga(hitungHarga);
    }
    
    
    }  
     
     
}
