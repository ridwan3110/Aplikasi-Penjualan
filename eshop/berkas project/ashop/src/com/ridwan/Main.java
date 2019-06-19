/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan;

import com.ridwan.DBConnection.DBConnection;
import com.ridwan.Dialog.Splashscreen;
import com.ridwan.view.MainMenu;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Screen;

/**
 *
 * @author ciwong
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Splashscreen screen = new Splashscreen();
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        screen.setVisible(true);
        for ( int i = 0 ; i<=100 ; i++){
            try {
                screen.getProgressBar().setValue(i);
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        screen.dispose();
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
    }
    
    
    
}
