/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppDAOs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukasgreiner
 */
public class ReadWriteUsername {

    public ReadWriteUsername() {
    }

    public String read() {
        FileReader fw = null;
        String streamTyp = "";
        String user = "";
        File dir = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TwitchHLS");
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        try {
            File file = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TwitchHLS" + File.separator + "User.txt");
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
                    return streamTyp;
                }
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(StreamWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(StreamWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void write(String username) {
        FileWriter fw = null;
        File dir = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TwitchHLS");
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        try {
            File file = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TwitchHLS" + File.separator + "User.txt");
            if (file.exists() == false) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(StreamWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(username);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(StreamWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(StreamWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
