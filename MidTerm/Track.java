
public class Track implements Comparable {
    
    String songName;//song name.
    /**
     *This method creates a Track object. 
     * @param songName
     */
    public Track(String songName){this.songName = songName;}
    
    /**
     * This method returns the song name.
     * @return song name
     */
    public String getSongName(){return songName;}
    
    /**
     * This method returns the Track object into a String.
     * @return song name
     */
    public String toString(){return songName;}
    
    /**
     * This method sorts by song name.
     * @param otherObject an Object.
     * @return an integer if less than, greater than, or equal.
     */
    public int compareTo(Object otherObject){
        
        Track other = (Track) otherObject;
        
        int compare = getSongName().compareTo(other.getSongName());
        
        if(compare < 0){return -1;}
        if(compare > 0){return 1;}
        return 0;
    }
}
