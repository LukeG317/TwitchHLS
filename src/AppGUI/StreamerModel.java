/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppGUI;

import AppDAOs.FileAccess;
import AppServices.Following;
import AppServices.Streamer;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author lukasgreiner
 */
public class StreamerModel extends DefaultComboBoxModel {

    private ArrayList<Streamer> streamer = new ArrayList<>();
    private Streamer selectedStreamTyp = null;
    private String selectedStreamTypString = null;
    private Following follow;

    public StreamerModel(Following follow) {
        this.follow = follow;
        this.sortStreamer(follow.getStreamer());
        this.sortStreamer(streamer);
    }

    public StreamerModel() {
        FileAccess access = new FileAccess("TwitchHLS.config");
        this.sortStreamer(access.readStreamer());
        this.sortStreamer(streamer);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof Streamer) {
            Streamer streamer = (Streamer) anItem;
            this.selectedStreamTyp = streamer;
        }
        if (anItem instanceof String) {
            this.selectedStreamTypString = (String) anItem;
        }

    }

    @Override
    public Object getSelectedItem() {
        if (this.selectedStreamTyp != null) {
            return this.selectedStreamTyp;
        }
        if (this.selectedStreamTypString != null) {
            return this.selectedStreamTypString;
        }
        return null;
    }

    @Override
    public int getSize() {
        return this.streamer.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.streamer.get(index);
    }

    private void sortStreamer(ArrayList<Streamer> str) {
        ArrayList<Streamer> live = new ArrayList<>();
        for (int i = 0; i < str.size(); i++) {
            if (str.get(i).isStreamLive() == true) {
                live.add(str.get(i));
                str.remove(str.get(i));
            }
        }
        Collections.sort(live);
        Collections.sort(str);
        ArrayList<Streamer> fin = new ArrayList<Streamer>();
        if (live.isEmpty() == false) {
            fin.addAll(live);
        }
        fin.addAll(str);
        this.streamer = fin;
    }

}
