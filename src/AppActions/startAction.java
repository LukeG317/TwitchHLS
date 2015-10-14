/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppActions;

import AppGUI.ChoosePanel;
import AppServices.HTMLCreator;
import java.awt.event.ActionEvent;
import javax.swing.*;
import AppServices.SelectionPOJO;
import AppServices.Stream;
import java.awt.Desktop;
import java.io.File;
import java.net.URI;

/**
 *
 * @author lukasgreiner
 */
public class startAction extends AbstractAction {

    private ChoosePanel source;

    public startAction(ChoosePanel source) {
        this.source = source;
        this.putValue(NAME, "Start");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        URI streamURI = null;
        String selectedChannel = (String) source.getInputBox().getSelectedItem().toString();
        String selectedQuality = (String) source.getQualityBox().getSelectedItem().toString();
        SelectionPOJO selected = new SelectionPOJO(selectedChannel, selectedQuality);
        boolean chat = source.getChatBox().isSelected();
        boolean autoQuality = source.getAutoQualityBox().isSelected();
        Stream stream = new Stream(selected);
        String streamURL = stream.getStreamURL();
        
        
        if (selectedChannel.equals("") == false && selectedChannel.contains(" ") == false && selectedChannel.equals("null") == false) {
            if (Desktop.isDesktopSupported()) {
                try {
                    if (chat == true && selected.isStreamLive()) {
                        HTMLCreator html = null;
                        if (autoQuality == false) {
                            html = new HTMLCreator(selectedChannel, streamURL);
                        } else {
                            html = new HTMLCreator(selectedChannel, streamURL, true);
                        }
                        File htmlFile = new File(html.getHTMLPath());
                        streamURI = htmlFile.toURI();

                    }
                    if (chat == false && selected.isStreamLive()) {
                        if (autoQuality == false) {
                            streamURI = URI.create(streamURL);
                        } else {
                            streamURI = URI.create("http://www.twitch.tv/"+selected.getChannel()+"/hls");
                        }
                    }
                    Desktop desktop = Desktop.getDesktop();
                    desktop.browse(streamURI);
                    System.exit(0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "There is no Stream avaiable", "Stream Info", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}
