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
	public void resetPlays(){
		
	}
}
