import java.util.ArrayList;
import java.util.Comparator;
import javafx.util.Pair;

public class Song implements Comparable<Song> {
    private String title;
    private String artist;
    private int duration;
    
    public User owner;
  
    public Song() {
        this("", "", 0, 0);
    }
    public Song(String t, String a, int n, int s) {
        owner= null;
        title = t;
        artist = a;
        duration = n * 60 + s;
       
    }
    public String getTitle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }
    public int getDuration() {
        return duration;
    }
    public int getMinutes() {
        return duration / 60;
    }
    public int getSeconds() {
        return duration % 60;
    }
    public String toString() {
        return("\"" + title + "\" by " + artist + " " + (duration / 60) + ":" + (duration%60));
    }


    @Override
    public int compareTo(Song c) {
        return (this.getTitle ().compareTo ( c.getTitle ()));

    }

        }
