package model;
/**@author Brian Wehrle
 * @author Patrick Maley
 *
 *Class Description: JukeBoxAccount holds the information of each individual account. It 
 *is mainly getters and setters that hold the required information for the controller.
 */
public class JukeBoxAccount implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -18876558248125584L;
	
	private int playtime;
	private int numberOfSongsPlayed;
	private String name;
	
	/**
	 * Constructor for JukeBoxAccount. Initializes playtime and number of songs played.
	 * 
	 * @param name
	 * 		The name of the account.
	 */
	public JukeBoxAccount(String name){
		this.numberOfSongsPlayed = 0;
		this.playtime = 90000; // seconds
		this.name = name;
	}
	
	/**
	 * Determines if the user has reacher their daily max limit of songs played.
	 * 
	 * @return True if they can play a song, false if not.
	 */
	public boolean canPlaySong(){
		return numberOfSongsPlayed < 3;
	}
	
	/**
	 * Returns the name of the account.
	 * 
	 * @return The name of the account.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Resets the number of songs played.
	 */
	public void resetNumberOfSongsPlayed(){
		this.numberOfSongsPlayed = 0;
	}
	
	/**
	 * Increments the number of songs played.
	 */
	public void incrementNumberOfSongsPlayed(){
		this.numberOfSongsPlayed++;
	}
	
	/**
	 * Gets the number of songs played by this account.
	 * 
	 * @return The number of songs played.
	 */
	public int getNumberOfSongsPlayed(){
		return this.numberOfSongsPlayed;
	}

	/**
	 * Subtracts the length of the song from the Account's playtime.
	 * 
	 * @param song
	 * 		The song that we use the length of to subtract.
	 */
	public void subtractPlayTime(Song song) {
		playtime -= song.getSongLength();
	}
	
/**
 * Returns the current playtime of the account.
 * 
 * @return The playtime of the account.
 */
	public int getPlayTime(){
		return this.playtime;
	}

}
