/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServices;

import AppGUI.MainFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author lukasgreiner
 */
public class TwitchHLS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Following follow = new Following();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame(follow);
            }
        });
    }

}
