/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppDAOs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukasgreiner
 */
public class StreamWriter {

    public StreamWriter(String name) {
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
            File file = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TwitchHLS" + File.separator + "TwitchHLS.config");
            if (file.exists() == false) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(StreamWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n" + this.capitalize(name));
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

    private String capitalize(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

}
