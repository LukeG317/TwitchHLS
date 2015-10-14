/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppDAOs;

import AppServices.DownloadThread;
import AppServices.Lock;
import AppServices.Streamer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukasgreiner
 */
public class DownloadStreamer {
    private DownloadThread thread;

    public DownloadStreamer(ArrayList<String> streamerString) {
        Lock lock = new Lock();
        DownloadThread dt = null;
        for (String url : streamerString) {
            dt = new DownloadThread(url, lock);
            dt.start();
        }
        while (lock.getRunningThreadsNumber() > 0) {
            Lock lock2 = lock;
            synchronized (lock2) {
                try {
                    lock.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(DownloadStreamer.class.getName()).log(Level.SEVERE, null, ex);
                }
                continue;
            }
        }
    }

    public ArrayList<Streamer> getStreamer() {
        return thread.getStreamer();
    }
    
    
    
    

}
