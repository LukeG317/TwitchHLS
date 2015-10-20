/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServices;

import com.eclipsesource.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author lukasgreiner
 */
public class SelectionPOJO {

    private String channel;
    private String quality;

    public SelectionPOJO(String channel, String quality) {
        this.channel = channel;
        this.quality = quality;
    }

    public String getChannel() {
        if (channel == null) {
            return "  ";
        }
        return channel.toLowerCase();
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getQuality() {
        if (quality.equals("Source")) {
            return "chunked";
        } else {
            return quality.toLowerCase();
        }
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public boolean isStreamLive() {
        try {
            URL url = new URL("https://api.twitch.tv/kraken/streams/" + channel);
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine = br.readLine();
            br.close();
            if(inputLine == null){
                return false;
            }
            JsonObject jsonObj = JsonObject.readFrom(inputLine);
            return (jsonObj.get("stream").isNull()) ? false : true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
