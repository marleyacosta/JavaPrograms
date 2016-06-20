import java.util.ArrayList;
import java.util.Collections;

public class Album extends Catalog implements Comparable{
    
    String artistName;// The artist name.
    String albumName;// The album name.
    private ArrayList tracksList;// The track list.
    
    /**
     * This method creates an Album object.
     * @param artistName artist name
     * @param albumName album name
     * @param tracksList list of tracks
     */
    public Album(String artistName, String albumName){
        
        tracksList = new ArrayList<Track>();// initialize the tracklist.
        
        this.artistName = artistName;
        this.albumName = albumName;           
    }
    /**
     * This method adds song names to the track list.
     * @param songName song name.
     */
    public void createList(String songName){Track track = new Track(songName);tracksList.add(track);
    }
    /**
     * This method returns the album name.
     * @return album name
     */
    public String getAlbumName(){ return albumName;}
    
    /**
     * This method returns the artist name.
     * @return artist name.
     */
    public String getArtistName(){ return artistName;}
    
    /**
     * This method returns formatted list of all the Album objects.
     * @return formatted list of all the Album objects.
     */
    public String toStrings(){
        
        String allTracks="";
        Collections.sort(tracksList);
        for(int x = 0;x < tracksList.size();x++){// Traverse the list.
            allTracks = allTracks  + tracksList.get(x);// Concatenate the track objects.
            if(x != tracksList.size() - 1){allTracks = allTracks  + "~~~~";}  // if not the last track on the list.
        }
        return String.format("%-30s         %-40s           %-40s",artistName,albumName,allTracks);//Artist Name: " + artistName + "             Album Name: " + albumName + "         " + allTracks;
    }
    /**
     * This method returns an Album object as a String.
     * @return Album object as a String.
     */
    public String toString(){
        
        String allTracks="";
        
        Collections.sort(tracksList);
        
        for(int x = 0;x < tracksList.size();x++){// Traverse the list.
            allTracks = allTracks  + tracksList.get(x);// Concatenate the tracks.
            if(x != tracksList.size() - 1){allTracks = allTracks  + "~~~~";}  // if not the last track on the list.
        }
        return artistName + " " + albumName + " " + allTracks;
    }
    /**
     * This method sorts the Album objects by album name.
     */
    public int compareTo(Object otherObject){
        
        Album other = (Album)otherObject;// Convert Object to Album object.
        
        int compare = getAlbumName().compareTo(other.getAlbumName());
        
        if ( compare < 0) { return -1; }// return -1 if less than.
        if (compare > 0) { return 1; }// 1 if greater than.
        return 0;// 0 if they are equal.
    } 
}
