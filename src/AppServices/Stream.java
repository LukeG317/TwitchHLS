/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServices;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author lukasgreiner
 */
public class Stream {

    private SelectionPOJO streamDetails;
    private String TOKEN_STRING, USHER_STRING, USHER_URL;
    private ArrayList<String> tokenAndSignature;

    public Stream(SelectionPOJO streamInfos) {
        this.streamDetails = streamInfos;
        this.TOKEN_STRING = "http://api.twitch.tv/api/channels/" + this.streamDetails.getChannel() + "/access_token";
        this.tokenAndSignature = this.getAccessToken(TOKEN_STRING);
        this.USHER_URL = this.getStreamUrl(this.getStreamPlaylist(tokenAndSignature));
    }

    public String getStreamURL() {
        return this.USHER_URL;
    }

    private ArrayList getAccessToken(String tokenURL) {
        ArrayList<String> list = new ArrayList<>();

        try {
            URL twitchURL = new URL(tokenURL);
            URLConnection twitchAPI = twitchURL.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(twitchAPI.getInputStream()));

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                list.add(inputLine.substring(10, inputLine.length() - 77));
                list.add(inputLine.substring(inputLine.length() - 68, inputLine.length() - 28));
            }
            reader.close();

        } catch (Exception ex) {

        }
        return list;
    }

    private String getStreamPlaylist(ArrayList<String> ls) {
        try {
            this.USHER_STRING = "http://usher.twitch.tv/api/channel/hls/"
                    + this.streamDetails.getChannel() + ".m3u8?player=twitchweb"
                    + "&token=" + URLEncoder.encode(ls.get(0), "UTF-8").replaceAll("%5C", "")
                    + "&sig=" + ls.get(1)
                    + "&allow_audio_only=true&allow_source=true"
                    + "&type=any&p=" + this.randomInt();
        } catch (Exception ex) {
            return null;
        }
        return this.USHER_STRING;
    }

    private int randomInt() {
        Random generator = new Random();
        return 100000 + generator.nextInt(900000);
    }

    private String getStreamUrl(String playlistURL) {
        try {
            URL playlist = new URL(playlistURL);
            URLConnection usherAPI = playlist.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(usherAPI.getInputStream()));

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                if (inputLine.contains("http://") && inputLine.contains(this.streamDetails.getQuality())) {
                    return inputLine;
                }
            }
            reader.close();

        } catch (Exception ex) {
            return null;
        }
        return null;
    }

}
