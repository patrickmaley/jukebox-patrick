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
	
	public void setNumPlays(int num){
		this.numberOfPlays = num;
	}
	
	public int getNumPlays(){
		return this.numberOfPlays;
	}
	

	public String getSongName(){
		return this.songName;
	}
	
	public String getPathName(){
		return this.pathName;
	}
	


	public int getSongLength() {
		return length;

	}
}
