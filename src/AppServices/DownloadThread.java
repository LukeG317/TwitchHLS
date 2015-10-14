/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServices;

import java.util.ArrayList;

/**
 *
 * @author lukasgreiner
 */
public class DownloadThread extends Thread {

    private String channel;
    private Lock lock;
    private ArrayList<Streamer> str = new ArrayList<>();

    public DownloadThread(String Url, Lock lock) {
        this.channel = "https://api.twitch.tv/kraken/streams/" + Url;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.addRunningThread();
            str.add(new Streamer(channel));
            lock.removeRunningThread();
            synchronized (lock) {
                lock.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Streamer> getStreamer() {
        if(str.isEmpty()){
            str.add(null);
        }
        return str;
    }

}
