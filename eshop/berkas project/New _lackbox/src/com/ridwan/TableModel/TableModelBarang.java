/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.TableModel;

import com.ridwan.Model.BarangModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ciwong
 */
public class TableModelBarang extends AbstractTableModel{
    
    List<BarangModel> list = new ArrayList<>();

    public TableModelBarang() {
    
    }
    
    

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
     return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       switch (columnIndex){
           case 0 : return list.get(rowIndex).getBarangId();
           case 1 : return list.get(rowIndex).getNamaBarang();
           case 2 : return list.get(rowIndex).getSerialNumber();
           case 3 : return list.get(rowIndex).getUntukPerangkat();
           case 4 : return list.get(rowIndex).getStasiun();
           case 5 : return list.get(rowIndex).getTanggalKeluar();
           case 6 : return list.get(rowIndex).getKaryawan().getNama();
           default : return null;
       }
    }
    
    @Override
    public String getColumnName (int column){
        switch (column){
            case 0 : return "Barang ID";
            case 1 : return "Nama Barang";
            case 2 : return "Serial Number";
            case 3 : return "Untuk Perangkat";
            case 4 : return "Stasiun";
            case 5 : return "Tanggal Keluar";
            case 6 : return "Nama Karyawan";
            default : return null;
        }
    }
    
    public void InsertBarang (BarangModel bm){
        this.list.add(bm);
        fireTableDataChanged();
    }
    
    public void UpdateBarang (int index, BarangModel bm){
        this.list.set(index, bm);
        fireTableDataChanged();
    }
    
    public void DeleteBarang (int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public void getBarang (int index){
        this.list.get(index);
    }
    
    public void setData (List<BarangModel> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public void clear(){
        list.clear();
    }
}
