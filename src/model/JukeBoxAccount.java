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
 *Class Description: JukeBoxAccount holds the information of each individual account. It 
 *is mainly getters and setters that hold the required information for the controller.
 */
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
	public boolean canPlaySong(){
		return numberOfSongsPlayed < 3;
	}
	
	public String getName() {
		return name;
	}
	
	public void resetNumberOfSongsPlayed(){
		this.numberOfSongsPlayed = 0;
	}
	
	public void incrementNumberOfSongsPlayed(){
		this.numberOfSongsPlayed++;
	}
	

	public int getNumberOfSongsPlayed(){
		return this.numberOfSongsPlayed;
	}

	public void subtractPlayTime(Song song) {
		playtime -= song.getSongLength();
	}
	
	public int getPlayTime(){
		return this.playtime;
	}

}
