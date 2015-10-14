/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServices;

import AppDAOs.StreamReader;
import AppDAOs.StreamWriter;
import AppGUI.AutoCompleteComboBox;
import AppGUI.ChoosePanel;
import AppGUI.StreamerModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author lukasgreiner
 */
public class StreamerListener extends KeyAdapter {

    private ChoosePanel pan;
    private AutoCompleteComboBox box;
    
    public StreamerListener(ChoosePanel panel,AutoCompleteComboBox box) {
        pan = panel;
        this.box= box;
    }

    
    
    @Override
    public void keyReleased(KeyEvent e) {
        boolean avaliable = false;
        if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            StreamReader reader = new StreamReader();
            String streamer = (String) box.getSelectedItem().toString();
            for (Streamer str : reader.getStreamer()) {
                if (str.getChannel().compareToIgnoreCase(streamer) == 0) {
                    avaliable = true;
                }
            }
            if (avaliable == false && streamer.contains(" ") == false && streamer.isEmpty() == false) {
                StreamWriter writer = new StreamWriter(streamer);
            }
            box.setModel(new StreamerModel());
            box.repaint();
            pan.getStartButton().doClick();
        }
    }

}
