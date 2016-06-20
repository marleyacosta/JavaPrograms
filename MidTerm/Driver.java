///////////////////////////////////////////////////////////////////////////////                  

// Title:            Two-Thirds Term Project 

// Files:            Driver.java - Catalog.java - Album.java - Track.java - ArtistNameComparator.java 

// Semester:         COP3337 Fall 2015

//

// Author:           3914479

// Lecturer's Name:  Christy Charter

//

// Description of Program’s Functionality: Reads a file and creates a catalog of albums.
// The program can sort by artist name and album name. As well as search for a specific album or artist. 
//

//////////////////////////// 80 columns wide/////////////////////////////////
import java.io.IOException;

public class Driver {
    
    public static void main(String[] args) throws IOException {
        
        Catalog catalog = new Catalog();// Create a Catalog object.
        
        catalog.CreateAlbums();// Create Album objects by extracting the data from the file.
        
        System.out.println("Sort By Album Name:");
        catalog.sortByAlbumName(); // Sort the data by album name.
        
        System.out.println("***************************************************");
        
        System.out.println("Sort By Artist Name:");
        catalog.sortByArtistName();// Sort the data by artist name.
        
        catalog.displayMenu(); // Display the menu to the user.
    }
}
