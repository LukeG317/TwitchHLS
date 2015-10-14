/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppDAOs;

import AppServices.DownloadThread;
import AppServices.Lock;
import AppServices.Streamer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukasgreiner
 */
public class StreamReader {

    private ArrayList<String> streamerString = new ArrayList<>();
    private ArrayList<Streamer> streamer = new ArrayList<>();

    public StreamReader() {
        FileReader fw = null;
        String streamTyp = "";
        File dir = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TwitchHLS");
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        try {
            File file = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TwitchHLS" + File.separator + "TwitchHLS.config");
            if (file.exists() == false) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(StreamWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            fw = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fw);
            while ((streamTyp = br.readLine()) != null) {
                if (streamTyp.equals("") || streamTyp.contains(" ")) {

                } else {
                    streamerString.add(streamTyp);
                }
            }
            boolean nameIndex = false;
            Lock lock = new Lock();
            DownloadThread dt = null;
            for (String url : this.streamerString) {
                dt = new DownloadThread(url, lock, this.streamer);
                dt.start();
            }
            while (lock.getRunningThreadsNumber() > 0) {
                Lock lock2 = lock;
                synchronized (lock2) {
                    lock.wait();
                    continue;
                }
            }
            
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(StreamWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(StreamReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(StreamWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<Streamer> getStreamer() {
        return streamer;
    }
}
