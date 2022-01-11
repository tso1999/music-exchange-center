import java.util.ArrayList;
import javafx.util.Pair;
public class User {
  public Float b;
  private boolean online;
  private String userName;
  private ArrayList <Song> songList = new ArrayList <Song> ();
  private int totalSongTime;
  
  
  public User() {
    this.userName = ("");
  }
  public User(String u) {
    userName = u;
    online = false;
    
    
  }
  
  public User(String u, ArrayList sl) {
    userName = u;
    online = false;
    songList = sl;
    
  }
  
  public String getUserName() {
    return userName;
  }
  
  public boolean isOnline() {
    return online;
    
  }
  
  public ArrayList <Song> getSongList() {
    return this.songList;
    
  }
  public String toString() {
    String s = "" + userName + ":" + songList.size ( ) + " songs (";
    if (!online) s += "not ";
    return s + "online)";
  }
  
  public void addSong(Song s) {
    s.owner = this;
    this.songList.add(s);
  }
  public void register(MusicExchangeCenter m) {
    m.registerUser (this);
  }
  
  public void logon(MusicExchangeCenter m) {
    m.userWithName (this.getUserName ()).online = true;
  }
  
  public void logoff(MusicExchangeCenter m) {
    if (m.userWithName (this.getUserName ()) != null) {
      m.userWithName (this.getUserName ()).online = false;
    }
  }
  public void  downloadSong(MusicExchangeCenter m, String title, String ownerName) {
    m.getSong (title, ownerName);
    if (m.getSong (title, ownerName) != null) {
      this.songList.add (m.getSong (title, ownerName) );
      m.downloadedSongs.add (m.getSong (title, ownerName));
      
      if(m.timesDownladed2.get (title) != null) {
        int numb = m.timesDownladed2.get (title) + 1;
        m.timesDownladed2.put (title, numb);
      }
      else {
        m.timesDownladed2.put(title, 1);
      }
      
      for(int j = 0; j < m.downloadedSongs.size (); j++) {
        if (m.downloadedSongs.get (j) == m.getSong (title, ownerName )) {
          int downl = m.timesDownloaded.get (j) + 1;
          m.timesDownloaded.set (j , downl);
        }
        
      }
      if (m.getRoyalties ().containsKey (m.getSong (title, ownerName).getArtist ())) {
        float j = m.getRoyalties ().get (m.getSong (title, ownerName ).getArtist ()) + 0.25f;
        m.getRoyalties ().put (m.getSong (title, ownerName).getArtist (), j);
        
      }
    }
  }
  public int totalSongTime() {
    for (int i = 0; i < songList.size (); i++) {
      totalSongTime = songList.get (i).getDuration () + totalSongTime;
    }
    return totalSongTime;
  }
   public ArrayList<String> requestSonglistByArtist(MusicExchangeCenter m, String artist){
     ArrayList <String> requestedSonglList = new ArrayList <String> (); 
     int counter = 0;
      String heading2 = String.format("%-3s %-29s %-21s %-10s %-15s"," ","Title","ARTIST","TIME","OWNER");
      requestedSonglList.add (heading2);
      for (Song p : m.allAvailableSongs ()) {
        if(p != null) {
          if (p.getArtist ().equals (artist)) {
            counter++;
            String song1 = String.format ("%2d%-1s %-29s  %-20s %2d:%-8s %-15s",counter,".", p.getTitle (), p.getArtist (), p.getMinutes (), p.getSeconds (), p.owner.userName);
            requestedSonglList.add (song1);
          }
        }
        
        
      }
      return requestedSonglList;
    }
    public ArrayList<String> requestCompleteSonglist(MusicExchangeCenter m){
      int count = 0;
      String heading = String.format("%-3s %-29s %-21s %-10s %-15s"," ","Title","ARTIST","TIME","OWNER");
      ArrayList <String> completeSongList = new ArrayList <String> ();
      completeSongList.add (heading);
      for (Song w :m.allAvailableSongs ()){
        count++;
        String song = String.format ("%2d%-1s %-29s  %-20s %2d:%-8s %-15s",count,".", w.getTitle (),w.getArtist (),w.getMinutes (),w.getSeconds (),w.owner.userName);
        completeSongList.add (song);
        
        
      }
      return  completeSongList;
      
    }
   
    
  }
  
  
  
  
  
  
  
  
  
  


