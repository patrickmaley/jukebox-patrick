package model;

import java.io.Serializable;
import java.util.ArrayList;
/**@author Brian Wehrle
 * @author Patrick Maley
 *
 *Class Description: SongCollection contains the list that holds our collection of songs.
 * It hardcodes the songs into the list upon construction. 
 */
public class SongCollection implements Serializable{

	private static final long serialVersionUID = -7242006385882812861L;
	
	public ArrayList<Song> list;
	private static SongCollection uniqueSongCollection;
	
	private SongCollection(){
		list = new ArrayList<Song>();
		setUpSongList();
	}
	
	/**
	 * Singleton implementation. If uniqueSongCollection is null,
	 * it constructs a new one. If not, it returns uniqueSongCollection.
	 * 
	 * @return The unique instance of this object.
	 */
	public static SongCollection makeSongCollection() {
		if (uniqueSongCollection == null)
			uniqueSongCollection = new SongCollection();
		return uniqueSongCollection;
	}
	
	private void setUpSongList() {
		Song songOne = new Song("Kevin MacLeod", "Danse Macabre Violin Hook", "./songfiles/DanseMacabreViolinHook.mp3", 34);
		Song songTwo = new Song("FreePlay Music", "Determined Tumbao", "./songfiles/DeterminedTumbao.mp3", 20);
		Song songThree = new Song("Sun Microsystems", "Flute", "./songfiles/flute.aif", 5);
		Song songFour = new Song("Unknown", "Loping Flute", "./songfiles/LopingSting.mp3", 4);
		Song songFive = new Song("Unknown", "Space Music", "./songfiles/spacemusic.au", 6);
		Song songSix = new Song("FreePlay Music", "Swing Cheese", "./songfiles/SwingCheese.mp3", 15);
		Song songSeven = new Song("Microsoft", "TaDa", "./songfiles/tada.wav", 2);
		Song songEight = new Song("Kevin MacLeod", "TheCurtainRises", "./songfiles/TheCurtainRises.mp3", 28);
		Song songNine = new Song("Kevin MacLeod", "Untameable Fire", "./songfiles/UntameableFire.mp3", 282);
		
		add(songOne);
		add(songTwo);
		add(songThree);
		add(songFour);
		add(songFive);
		add(songSix);
		add(songSeven);
		add(songEight);
		add(songNine);
		
		
	}
	
	private void add(Song song) {
		list.add(song);		
	}
	
	/**
	 * Returns the size of the song list.
	 * 
	 * @return the size of the song list.
	 */
	public int getSize() {
		return list.size();
	}

	/**
	 * Returns the song at the specified index.
	 * 
	 * @param index
	 * 		The index at which to retrieve the song from the list.
	 * 
	 * @return
	 * 		The song at the specified index.
	 */
	public Song getElementAt(int index) {
		return list.get(index);
	}
	
	/**
	 * Resets the number of plays of all songs in the list to 0.
	 */
	public void resetSongs(){
		for (Song song : list) {
			song.setNumPlays(0);
		}
	}
}
