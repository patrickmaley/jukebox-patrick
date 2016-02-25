package model;

public class JukeBoxAccount {
	private int id;
	private int playtime;
	private int numberOfSongsPlayed;
	private String name;
	
	public JukeBoxAccount(String name, int id){
		this.numberOfSongsPlayed = 0;
		this.playtime = 1500;
		this.id = id;
		this.name = name;
	}
	
	public boolean canPlaySong(Song song){
		return false;
	}
	
}
