
import java.util.Comparator;

public class ArtistNameComparator implements Comparator <Album> {
    
    /**
     * This method sorts by artist name.
     * @param a an Album object
     * @param b another Album object.
     * @return An integer representing if the Album is less than, greater than, or equal to.
     */
    public int compare(Album a, Album b){
        
        String A = a.getArtistName();// get Album a's artist name
        String B = b.getArtistName();// get Album b's artist name
        
        return A.compareTo(B);
    }  
}
