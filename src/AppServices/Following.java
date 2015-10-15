/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServices;


import AppDAOs.FileAccess;
import AppDAOs.FileAccess.FileMode;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
import java.net.*;
import com.eclipsesource.json.*;

/**
 *
 * @author lukasgreiner
 */
public class Following {

    private String user;
    private ArrayList<Streamer> streamer;

    public Following() {
        user = this.getUsername();
        this.writeToStreamerList();
    }

    private ArrayList<String> getFollowingList() {
        ArrayList<String> following = new ArrayList<>();
        try {
            URL url = new URL("https://api.twitch.tv/kraken/users/" + user + "/follows/channels");
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine = br.readLine();
            br.close();
            JsonObject obj = JsonObject.readFrom(inputLine);
            JsonArray follows = obj.get("follows").asArray();
            for (JsonValue item : follows) {
                JsonValue links = item.asObject().get("_links");
                following.add(links.toString().substring(links.toString().indexOf("channels/") + 9, links.toString().length() - 2));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return following;
    }

    private void writeToStreamerList() {
        ArrayList<String> following = this.getFollowingList();
        FileAccess access = new FileAccess(FileMode.APPEND,"TwitchHLS.config");
        ArrayList<Streamer> streamer = access.readStreamer();
        this.streamer = streamer;
        for (String follow : following) {
            if (this.contains(streamer, follow) == false) {
                access.write(follow);
            }
        }

    }

    public ArrayList<Streamer> getStreamer() {
        return streamer;
    }

    private String getUsername() {
        String eingabe = null;
        FileAccess user = new FileAccess(FileMode.UNAPPEND,"User.txt");
        eingabe = user.readString();
        if (eingabe == null || eingabe.contains(" ") || eingabe.isEmpty()) {
            do {
                eingabe = JOptionPane.showInputDialog(null, "Enter your Twitch Name", "Twitch Username", JOptionPane.PLAIN_MESSAGE);
                if (eingabe.isEmpty() == false && eingabe != null && eingabe.contains(" ") == false) {
                    user.write(eingabe);
                }
            } while (eingabe.isEmpty());

        }
        return eingabe;
    }
    
    private boolean contains(ArrayList<Streamer> list,Object o) {
        String paramStr = (String)o;
        for (Streamer s : list) {
            if (paramStr.equalsIgnoreCase(s.getChannel())) return true;
        }
        return false;
    }

}
