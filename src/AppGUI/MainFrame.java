/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppGUI;

import AppServices.Following;
import javax.swing.JFrame;


/**
 *
 * @author lukasgreiner
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        ChoosePanel contPan = new ChoosePanel();
        this.setTitle("TwitchHLS");
        this.setContentPane(contPan);
        this.getRootPane().setDefaultButton(contPan.getStartButton());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(310, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
