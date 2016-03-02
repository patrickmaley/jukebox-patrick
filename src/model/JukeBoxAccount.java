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
	
	public JukeBoxAccount(String name, int id){
		this.numberOfSongsPlayed = 0;
		this.playtime = 90000; // seconds
		this.id = id;
		this.name = name;
	}
	
	// Determines if the user has reached their daily max limit of songs played
	public boolean canPlaySong(){
		return numberOfSongsPlayed < 3;
	}
	
	// returns name
	public String getName() {
		return name;
	}
	
	// resets the number of songs played
	public void resetNumberOfSongsPlayed(){
		this.numberOfSongsPlayed = 0;
	}
	
	// increments number of songs played
	public void incrementNumberOfSongsPlayed(){
		this.numberOfSongsPlayed++;
	}
	
	// returns number of songs played
	public int getNumberOfSongsPlayed(){
		return this.numberOfSongsPlayed;
	}

	// Subracts the length of the song from the user's current playTime.
	public void subtractPlayTime(Song song) {
		playtime -= song.getSongLength();
	}
	
	// returns users current play time
	public int getPlayTime(){
		return this.playtime;
	}

}
