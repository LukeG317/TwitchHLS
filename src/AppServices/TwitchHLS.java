/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServices;

import AppGUI.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author lukasgreiner
 */
public class TwitchHLS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(System.getProperty("system.os").startsWith("Windows")){
            try {
                UIManager.setLookAndFeel("com.alee.laf.WebLookAndFee");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(TwitchHLS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Following follow = new Following();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame(follow);
            }
        });
    }

}
