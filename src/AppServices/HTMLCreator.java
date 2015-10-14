/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServices;

import AppDAOs.StreamWriter;
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
public class HTMLCreator {

    private String channel;
    private String streamURL;
    private String html;
    private boolean auto;

    public HTMLCreator(String channel, String streamURL) {
        this.channel = channel;
        this.streamURL = streamURL;
        this.auto = false;
        this.createHTMLString();
        this.createHTMLFile();
    }
    public HTMLCreator(String channel, String streamURL,boolean autoQuality) {
        this.channel = channel;
        this.streamURL = streamURL;
        this.auto=autoQuality;
        if(auto == true){
            this.streamURL = "http://www.twitch.tv/"+this.channel+"/hls";
        }
        this.createHTMLString();
        this.createHTMLFile();
    }

    private void createHTMLString() {
        this.html = "<!doctype html>\n"
                + "<html lang=\"de\">\n"
                + "    <head>\n"
                + "    <style>\n"
                + "        body {background-color:rgb(38,38,38)}\n"
                + "    </style>\n"
                + "    </head>\n"
                + "    <body scroll=\"no\">\n"
                + "        <iframe frameborder=\"0\" \n"
                + "            scrolling=\"no\" \n"
                + "            id=\"chat_embed\" \n"
                + "            src=\""+this.streamURL+"\" \n"
                + "            style=\"border-style: none;width: 78%; min-height: 98vh;\"\n"
                + "            height=\"100%\" \n"
                + "            width=\"75%\"\n"
                + "            align=\"left\">\n"
                + "        </iframe>\n"
                + "        <iframe frameborder=\"0\" \n"
                + "            scrolling=\"no\" \n"
                + "            id=\"chat_embed\" \n"
                + "            src=\"http://www.twitch.tv/"+this.channel.toLowerCase()+"/chat\" \n"
                + "            style=\"border-style: none;width: 22%; min-height: 98vh;\"\n"
                + "            height=\"100%\" \n"
                + "            align=\"right\">\n"
                + "        </iframe>\n"
                + "    </body>\n"
                + "</html>";
    }
    
    
    private void createHTMLFile(){
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
            File file = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TwitchHLS" + File.separator + "TwitchHLS.html");
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.html);
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
    
    public String getHTMLPath(){
        return System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TwitchHLS" + File.separator + "TwitchHLS.html";
    }
    
    
    

}
