/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Editor;

import com.ridwan.Model.Barang;
import com.ridwan.Pencarian.CariBarang;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author ciwong
 */
public class BarangEditor extends AbstractCellEditor implements TableCellEditor{
    private CariBarang  cariBarang = new CariBarang();

    public BarangEditor(CariBarang barang) {
        this.cariBarang = barang;
        
    }
    
    

    @Override
    public Object getCellEditorValue() {
        return cariBarang.getBarang();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        Barang barang = null;
        if (value instanceof Barang){
            barang = (Barang) value;
        }
        cariBarang.setBarang(barang);
        return cariBarang;
    }
    
}
