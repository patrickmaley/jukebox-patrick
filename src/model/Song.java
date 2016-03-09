package model;
/**@author Brian Wehrle
 * @author Patrick Maley
 * 
 *Class Description: Song holds the information of each song object. It is mainly getters and 
 *setters which have all the hardcoded information.
 */
public class Song implements java.io.Serializable{
	
	private static final long serialVersionUID = 6866369057112030883L;
	
	String songName;
	String artist;
	int length;
	int numberOfPlays;
	String pathName;
	
	/**
	 * Constructor for the song. 
	 * 
	 * @param artist
	 * 		The artist of the song.
	 * 
	 * @param song
	 * 		The name of the song.
	 * 
	 * @param path
	 * 		The file path to the song.
	 * 
	 * @param length
	 * 		The length of the song, in seconds.
	 */
	public Song(String artist, String song, String path, int length){
		this.artist = artist;
		this.songName = song;
		this.length = length;
		this.numberOfPlays = 0;
		this.pathName = path;
	}
	
	/**
	 * Sets the number of plays of the song.
	 * 
	 * @param num
	 * 		The number of plays.
	 */
	public void setNumPlays(int num){
		this.numberOfPlays = num;
	}
	
	/**
	 * Returns the number of plays of the song.
	 * 
	 * @return the number of plays.
	 */
	public int getNumPlays(){
		return this.numberOfPlays;
	}
	
	/**
	 * Returns the name of the song.
	 * 
	 * @return The name of the song.
	 */
	public String getSongName(){
		return this.songName;
	}
	
	/**
	 * Returns the path variable of the song.
	 * 
	 * @return The path variable of the song.
	 */
	public String getPathName(){
		return this.pathName;
	}
	
	/**
	 * Returns the length of the song in seconds.
	 * 
	 * @return The length of the song.
	 */
	public int getSongLength() {
		return length;
	}
	
	/**
	 * Returns the name of the artist of the song.
	 * 
	 * @return The name of the artist of the song.
	 */
	public String getArtist(){
		return this.artist;
	}
	
	/**
	 * Returns a String that has the name of the song, the artist, and the number of plays of the song.
	 * 
	 * @return The toString representation of the song.
	 */
	@Override
	public String toString(){
		return "Song name: " + songName + "\nArtist: " + artist + "\nnumPlays: " + numberOfPlays;
	}
	
	
}
