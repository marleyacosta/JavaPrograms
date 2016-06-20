
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Catalog {
    
    ArrayList<Album> catalog = new ArrayList<>();// list of album objects.
    
    ArrayList<Album> artistNameList = new ArrayList<>();// list of album objects sorted by artist name.
    
    Scanner prompt = new Scanner(System.in); // construct Scanner object
    
    String response = "";//gets the value of the user's responses.
    String albumName = "";//album name
    String artistName = "";//artist name
    String fileName;// name of file to be read
    
    /**
     * This method reads a file and creates Album objects to be store in a list call Catalog.
     * @throws FileNotFoundException if the file is not found
     * @throws IOException If an error occurs.
     */
    public void CreateAlbums() throws FileNotFoundException, IOException{
        // Set boolean done to false.
        boolean done = false;
        // while the file is not done.
        while (!done){
        try{
        System.out.println("Enter 'catalog2.txt' for the file input.");
        fileName = prompt.next();
        
        File file = new File(fileName);// create File object.
        
	FileReader fileReader = new FileReader(file);// read file.
        
	BufferedReader bufferedReader = new BufferedReader(fileReader);// extract.
        
	StringBuffer stringBuffer = new StringBuffer();
	String line;
        
    while ((line = bufferedReader.readLine()) != null){// while there is a line to read.
                            
        Scanner in = new Scanner(line); // scan the line.
        
            String artistName = in.next();// extract artist name.
            
            String albumName = in.next();// extract album name.
            
            Album album = new Album(artistName,albumName);// Create Album object.
            
                while(in.hasNext()){// while there is more songs

                    String songName = in.next();// extract song name

                    album.createList(songName);// add to the Album object track list.   
                }
                catalog.add(album);// add album object to the catalog list.
                done = true;
            }
        }
        
            //Test with a file name that doesn't exist.
            catch (FileNotFoundException exception){System.out.println("File not found.");}
            catch (IOException exception){exception.printStackTrace();}
        }
    }
    
    /**
     * This method sorts the Album objects by album name.
     */
    public void sortByAlbumName(){
        
        Collections.sort(catalog);//sorts the catalog list by album name.
        
        for(int i = 0; i < catalog.size();i++){System.out.println(catalog.get(i).toStrings());}//print the list of albums by album name.
    }
    /**
     * This method sorts the Album objects by artist name.
     */
    public void sortByArtistName(){
        
        for(int i = 0; i < catalog.size();i++){artistNameList.add(catalog.get(i));}//make a copy of the catalog list.
        
        ArtistNameComparator artistName = new ArtistNameComparator();//create comparator object.
        
        Collections.sort(artistNameList,artistName);//sort the new list by artist name.
        
        for(int i = 0; i < artistNameList.size();i++){System.out.println(artistNameList.get(i).toStrings());}// print the new list sorted by artist name.
    }
    /**
     * This method displays options to the user: sort or add new album.
     * @throws IOException If there is an error.
     */
    public void displayMenu() throws IOException{
        
        System.out.println("\nEnter an option(Enter a, b, c, or d):\n\n a) Search by album title\n b) Search by artist\n c) Add album to Catalog\n d) Exit the menu");
        response = prompt.next();
        
        while(!response.equalsIgnoreCase("d")){// if the user doesn't want to quit.
            
            if(response.equalsIgnoreCase("a")){// if the use wants to search by album title.
                
                System.out.println("What is the name of the album you want to search?");
                albumName = prompt.next();
                albumSearch(albumName);
            }
            if(response.equalsIgnoreCase("b")){// if the user wants to search by artist name.
                
                System.out.println("What is the name of the artist you want to search?");
                artistName = prompt.next();
                artistSearch(artistName);
            }
            if(response.equalsIgnoreCase("c")){createAlbum();}// if the user wants to add a new album to the file
             
            if(response.equalsIgnoreCase("d")){break;}// if the user wants to quit.
        }
    }
    /**
     * This method searches for an album name in the catalog list
     * @param albumName The name of the album.
     */
    public void albumSearch(String albumName){
        
        Collections.sort(catalog);// sort by album name.
        
        int index = Collections.binarySearch(catalog,new Album(null,albumName),null);// searches for album name and returns the location.
        
        System.out.println(catalog.get(index).toStrings());//print the album object.
        
        System.out.println("\nSelect an option(Enter a, b, c, or d):\n\n a) Search by album title\n b) Search by artist\n c) Add album to Catalog\n d) Exit the menu");
        response = prompt.next();
    }
    /**
     * This method searches for all the albums created by the artist the user searched.
     * @return all instances of the artist name.
     */
    public void artistSearch(String artistName){
        
        ArtistNameComparator artistName1 = new ArtistNameComparator();// Create comparator object.
        
        Collections.sort(artistNameList,artistName1);// sort by artist name
       
        int index = Collections.binarySearch(artistNameList,new Album(artistName,null),artistName1);//searches for  the artist name and returns the location.
       
        for(int x = index; x >=0;x--){//traverse backwards
            
            if(!(artistNameList.get(x).getArtistName().equals(artistName))){// if the artist name is not the same at the top.
                
                for(int i = x + 1; i < artistNameList.size();i++){// start printing all instances of the artist name starting from the first.
                    
                    if(!(artistNameList.get(i).getArtistName().equals(artistName))){break;}//if towards the artist name at the bottom.
                    
                    System.out.println(artistNameList.get(i).toStrings());//print the album object with the artist name we are searching for.
                    
                }
                break;// all artist name have been printed, so break the loop
            }
        }

        System.out.println("\nSelect an option(Enter a, b, c, or d):\n\n a) Search by album title\n b) Search by artist\n c) Add album to Catalog\n d) Exit the menu");
        response = prompt.next();
    }
    /**
     * This method creates an album through user input.
     */
    public void createAlbum() throws IOException{
        
        System.out.println("Enter the artist name:");
        String artistName = prompt.next();
        
        System.out.println("Enter the album name:");
        String albumName = prompt.next();
        
        Album newAlbum = new Album(artistName, albumName);// create album object.
        
        System.out.println("Add a song? (Enter 1 for 'yes', 0 for 'no')");
        String moreSongName = prompt.next();
        
        while(moreSongName.equalsIgnoreCase("1")){
            System.out.println("Enter the song name:");
            String songName = prompt.next();
            
            newAlbum.createList(songName);// create track list.
            
            System.out.println("Add another song? (Enter 1 for yes, 0 for no.");
            moreSongName = prompt.next();    
        }
        catalog.add(newAlbum);// add to catalog list.
        
        artistNameList.add(newAlbum);//add to list sorted by artist name.
        
        Collections.sort(catalog);//sort by album name
        
        // Permanently add the album to the external file.
        FileWriter file = new FileWriter(fileName, true);
        
        PrintWriter print = new PrintWriter(file);
        
        print.println(newAlbum);
        
        print.close() ;// close the file
        
        System.out.println("\nSelect an option(Enter a, b, c, or d):\n\n a) Search by album title\n b) Search by artist\n c) Add album to Catalog\n d) Exit the menu");
        response = prompt.next();
    }
}
