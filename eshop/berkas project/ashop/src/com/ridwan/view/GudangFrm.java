/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.view;

/**
 *
 * @author ciwong
 */
public class GudangFrm extends javax.swing.JInternalFrame {

    /**
     * Creates new form GudangFrm
     */
    public GudangFrm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gudangPnl1 = new com.ridwan.view.panel.GudangPnl();

        setClosable(true);
        setIconifiable(true);
        setTitle("Master Gudang");
        getContentPane().add(gudangPnl1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ridwan.view.panel.GudangPnl gudangPnl1;
    // End of variables declaration//GEN-END:variables
}
