/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServices;


import AppDAOs.FileAccess;
import AppDAOs.FileAccess.FileMode;
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
        this.pan = panel;
        this.box= box;
    }

    
    
    @Override
    public void keyReleased(KeyEvent e) {
        boolean avaliable = false;
        if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            FileAccess access = new FileAccess(FileMode.APPEND,"TwitchHLS.config");
            String streamer = (String) box.getSelectedItem().toString();
            for (Streamer str : access.readStreamer()) {
                if (str.getChannel().compareToIgnoreCase(streamer) == 0) {
                    avaliable = true;
                }
            }
            if (avaliable == false && streamer.contains(" ") == false && streamer.isEmpty() == false) {
                access.write(streamer);
            }
            
            pan.getStartButton().doClick();
            
        }
    }

}
