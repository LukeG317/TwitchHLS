/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServices;

/**
 *
 * @author lukasgreiner
 */
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
public class Streamer implements Comparable {

    private String channel;
    private String inputLine;
    private boolean status;
    private String game = null;

    public Streamer(String channel) {
        try {
            URL url = new URL(channel);
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            inputLine = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.channel = channel;
    }

    public String getChannel() {
        if (channel == null) {
            return "  ";
        }
        return channel.toLowerCase().substring(37);
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public boolean isStreamLive() {
        JsonObject jsonObj = JsonObject.readFrom(inputLine);
        this.status = (jsonObj.get("stream").isNull()) ? false : true;
        return this.status;
    }

    @Override
    public String toString() {
        return this.capitalize(this.getChannel());
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public String getGame() {
        JsonObject jsonObj = JsonObject.readFrom(inputLine);
        if (this.isStreamLive() == false) {
            return "";
        } else {
            if (jsonObj.get("stream").isObject()) {
                JsonObject stream = (JsonObject) jsonObj.get("stream");
                return stream.get("game").toString();
            }
        }
        return "";
    }

    @Override
    public int compareTo(Object o) {
        if (o != null) {
            if (o instanceof Streamer) {
                Streamer str = (Streamer) o;
                return this.getChannel().compareToIgnoreCase(((Streamer) o).getChannel());
            }
        }
        return -1;
    }

}
