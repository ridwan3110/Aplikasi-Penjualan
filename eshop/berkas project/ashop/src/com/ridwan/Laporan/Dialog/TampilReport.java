/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Laporan.Dialog;

import javax.swing.JDialog;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author ciwong
 */
public class TampilReport extends JDialog{
    
    private String title;
    private JRViewer jrv;

    public TampilReport(String title, JRViewer jrv) {
        this.title = title;
        this.jrv = jrv;
        Atur();
    }
    
    private void Atur (){
        setSize(900, 700);
        setLocationRelativeTo(null);
        setTitle(title);
        getContentPane().add(jrv);
        setVisible(true);
    }
    
}
