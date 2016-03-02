package model;
/*Author: Patrick Maley && Brian Wehrle
 * 
 *Class: CSC 335
 * 
 *Project: JukeBox Iteration 1
 * 
 *Date: February 29, 2016
 *
 *Professor: Dr. Mercer
 *
 *Section Lead: Cindy Trieu
 *
 *Class Description: Song holds the information of each song object. It is mainly getters and 
 *setters which have all the hardcoded information.
 */
public class Song {
	String songName;
	String artist;
	int length;
	int numberOfPlays;
	String pathName;
	
	public Song(String artist, String song, String path, int length){
		this.artist = artist;
		this.songName = song;
		this.length = length;
		this.numberOfPlays = 0;
		this.pathName = path;
	}
	
	// sets of the number of plays
	public void setNumPlays(int num){
		this.numberOfPlays = num;
	}
	
	// returns the number of plays
	public int getNumPlays(){
		return this.numberOfPlays;
	}
	
	//returns the name of the song
	public String getSongName(){
		return this.songName;
	}
	
	// returns the path variable to the song
	public String getPathName(){
		return this.pathName;
	}
	
	// returns the length of the song
	public int getSongLength() {
		return length;

	}
}
