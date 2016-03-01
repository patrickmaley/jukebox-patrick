package model;

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
	
	public int getLength(){
		return this.length;
	}
}
