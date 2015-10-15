/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppDAOs;

import AppServices.Streamer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukasgreiner
 */
public class FileAccess {

    private String dirPath = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TwitchHLS";
    private File dir, file;
    private String fileName;
    private FileMode mode;

    public enum FileMode {
        APPEND, UNAPPEND;
    }

    public FileAccess(FileMode mode, String fileName) {
        dir = new File(this.dirPath);
        this.file = new File(this.dirPath + File.separator + fileName);
        this.createDirectory();
        this.createFile();
        this.mode = mode;
        this.fileName = fileName;

    }

    public FileAccess(String fileName) {
        this(FileMode.UNAPPEND, fileName);
    }

    public void write(String stringToWrite) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file.getAbsoluteFile(),this.mode(mode));
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("\n" + this.capitalize(stringToWrite));
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(FileAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Streamer> readStreamer() {
        String streamTyp = "";
        ArrayList<String> streamerString = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file.getAbsoluteFile());
            BufferedReader bufReader = new BufferedReader(fileReader);
            while ((streamTyp = bufReader.readLine()) != null) {
                if (streamTyp.equals("") || streamTyp.contains(" ")) {

                } else {
                    streamerString.add(streamTyp);
                }
            }
            DownloadStreamer streamer = new DownloadStreamer(streamerString);
            System.out.println("test");
            return streamer.getStreamer();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String readString() {
        FileReader fileReader = null;
        try {
            String streamTyp = "";
            fileReader = new FileReader(file.getAbsoluteFile());
            BufferedReader bufReader = new BufferedReader(fileReader);
            while ((streamTyp = bufReader.readLine()) != null) {
                if (streamTyp.equals("") || streamTyp.contains(" ")) {

                } else {
                    return streamTyp;
                }
            }
            bufReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException ex) {
            }
        }
        return null;
    }

    private void createDirectory() {
        if (dir.exists() == false) {
            if (dir.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }

    private void createFile() {
        if (file.exists() == false) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private String capitalize(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    private boolean mode(FileMode mode){
        switch(mode){
            case APPEND:
                return true;
            case UNAPPEND:
                return false;
        }
        return false;
    }
}
