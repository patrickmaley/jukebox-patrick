package model;

public class JukeBoxAccount {
	private int id;
	private int playtime;
	private int numberOfSongsPlayed;
	private String name;
	// add String password?
	
	public JukeBoxAccount(String name, int id){
		this.numberOfSongsPlayed = 0;
		this.playtime = 1500;
		this.id = id;
		this.name = name;
	}
	
	// what's the difference between this and the canPlay method in Jukebox?
	public boolean canPlaySong(Song song){
		return false;
	}
	
	public String getName() {
		return name;
	}
	
}
