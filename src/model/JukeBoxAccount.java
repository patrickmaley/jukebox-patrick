package model;

public class JukeBoxAccount {
	private int id;
	private int playtime;
	private int numberOfSongsPlayed;
	
	public JukeBoxAccount(int id){
		this.numberOfSongsPlayed = 0;
		this.playtime = 1500;
		this.id = id;
	}
	
	public boolean canPlaySong(Song song){
		return false;
	}
	
}
