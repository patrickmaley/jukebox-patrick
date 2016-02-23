package model;

public class Song {
	String songName;
	String artist;
	int length;
	int numberOfPlays;
	int ID;
	
	public Song(String artist, String song, int length, int id){
		this.artist = artist;
		this.songName = song;
		this.length = length;
		this.ID = id;
		this.numberOfPlays = 0;
	}
	public void resetPlays(){
		
	}
}
