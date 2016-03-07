package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class SongCollection implements Serializable{

	public ArrayList<Song> list;
	private static SongCollection uniqueSongCollection;
	
	private SongCollection(){
		list = new ArrayList<Song>();
		setUpSongList();
	}
	
	public static SongCollection makeSongCollection() {
		if (uniqueSongCollection == null)
			return new SongCollection();
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
		add(songOne);
		add(songTwo);
		add(songThree);
		add(songFour);
		add(songFive);
		add(songSix);
		add(songSeven);
		add(songEight);
		add(songNine);
		add(songOne);
		add(songTwo);
		add(songThree);
		add(songFour);
		add(songFive);
		add(songSix);
		add(songSeven);
		add(songEight);
		add(songNine);
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
	
	public int getSize() {
		return list.size();
	}

	public Song getElementAt(int index) {
		return list.get(index);
	}
}
