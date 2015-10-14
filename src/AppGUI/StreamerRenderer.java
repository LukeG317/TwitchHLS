/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppGUI;

import AppServices.Streamer;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author lukasgreiner
 */
public class StreamerRenderer extends DefaultListCellRenderer {

    private static ImageIcon greenButton;
    private static ImageIcon redButton;

    public StreamerRenderer(ImageIcon greenButton, ImageIcon redButton) {
        this.greenButton = greenButton;
        this.redButton = redButton;
    }

    

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = new JLabel();
        Streamer str = (Streamer) value;
        if (str.isStreamLive()) {
            label.setIcon(greenButton);
        } else {
            label.setIcon(redButton);
        }
        
        label.setText(this.capitalize(str.getChannel()));
        if(str.getGame().equals("")){
        }
        else{
            label.setToolTipText(str.getGame());
        }
        return label;
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }


}
