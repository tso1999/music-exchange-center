import javafx.util.Pair;

import java.util.*;

public class MusicExchangeCenter {
  private ArrayList<User> users;
  
  HashMap <String,Float> royalties  = new HashMap<String,Float>();
  public  HashMap <String,Integer>  timesDownladed2 = new HashMap <String, Integer> ();
  
  public ArrayList <Song> downloadedSongs;
  public  ArrayList <Integer> timesDownloaded = new ArrayList <Integer> ();
  
  
  public MusicExchangeCenter() {
    users = new ArrayList <User> ();
    downloadedSongs = new ArrayList <Song> ();
    
    royalties.put ("Sleepfest" ,0.00F);
    royalties.put ("Clip" ,0.00F);
    royalties.put ("Jaw" ,0.00F);
    royalties.put ("Long Road" ,0.00F);
    royalties.put ("UFO" ,0.00F);
    
  }
  
  public ArrayList <Song> allAvailableSongs() {
    ArrayList <Song> songsAvailable = new ArrayList <Song> ();
    for (int i = 0; i < users.size (); i++) {
      if (users.get (i).isOnline ()) {
        for (int z = 0; z < users.get (i).getSongList ().size (); z++) {
          songsAvailable.add (users.get (i).getSongList ().get (z) );
          timesDownloaded.add(0);
        }
      }
    }
    return songsAvailable;
  }
  public ArrayList <User> onlineUsers() {
    ArrayList <User> usersOnline2 = new ArrayList <User> ( );
    
    for (int i = 0; i < users.size (); i++) {
      if (users.get (i).isOnline ()) {
        usersOnline2.add (users.get (i));
      }
    }
    return usersOnline2;
  }
  
  public String toString() {
    return ("Music Exchange Center " + "(" + onlineUsers ().size () + " clients on line," + allAvailableSongs ().size () + " songs available)");
  }
  
  
  
  public User userWithName(String s) {
    for (int i = 0; i < users.size (); i++) {
      if (users.get (i).getUserName ().equals (s)) {
        return users.get (i);
      }
    }
    return null;
  }
  
  public void registerUser(User t) {
    if ((userWithName (t.getUserName ())) == null) {
      users.add (t);
    }
  }
  
  public ArrayList <Song> availableSongsByArtist(String artist) {
    for (int i = 0; i < users.size (); i++) {
      if (users.get(i).isOnline()) {
        if (users.get (i).getSongList ().get (i).getArtist ().equals (artist)) {
          allAvailableSongs ().add (users.get (i).getSongList ().get (i));
        }
      }
    }
    return allAvailableSongs ();
  }
  
  
  
  public ArrayList <Song> getDownloadedSongs() {
    return downloadedSongs;
  }
  
   public ArrayList<Pair<Integer, Song>>  songsByPopularity(){
    ArrayList<Pair<Integer, Song>> pairL = new ArrayList <Pair<Integer, Song>> ();
    Set<String> songs = timesDownladed2.keySet ();
    for(String song : songs){
      for (Song s : uniqueDownloads ()) {
        if (s.getTitle ().equals (song)) {
          Pair <Integer, Song> x = new Pair <Integer, Song> (timesDownladed2.get (song), s );
          pairL.add (x);
        }
      }
    }
    
    Collections.sort(pairL, new Comparator<Pair<Integer, Song>> () {
      public int compare(Pair<Integer, Song> p1, Pair<Integer, Song> p2) {
        return p2.getKey () - p1.getKey ();
      }
    });
    return pairL;
    
  }
  
  public Song getSong(String title, String ownerName) {
    for (int i = 0; i < onlineUsers ().size (); i++) {
      if (onlineUsers ().get (i).getUserName ().equals (ownerName)) {
        for (int r = 0; r < onlineUsers ().get (i).getSongList ().size (); r++) {
          if (onlineUsers ().get (i).getSongList ().get (r).getTitle ().equals (title)) {
            return (onlineUsers ().get (i).getSongList ().get (r));
            
          }
        }
        
      }
    }
    return null;
    
    
  }
 
  public TreeSet<Song>  uniqueDownloads() {
    ArrayList<Song> popularitySongs = new ArrayList <Song> ();
    TreeSet<Song> songTree = new TreeSet <Song> ();
    for (Song s : downloadedSongs) {
      songTree.add (s);
    }
    for(Song e : songTree){
      popularitySongs.add (e);
    }
    
    return songTree;
  }
  
  public void displayRoyalties() {
    
    System.out.println ("Amount     Artist");
    System.out.println ("-------------------");
    for (String song : royalties.keySet ()) {
      System.out.println (String.format ("$" + royalties.get (song) + "     " + song));
    }
  }

  public HashMap<String, Float> getRoyalties() {
    return royalties;
  }
  
  
  public static void main(String args []){
    
  }
  
  
}


