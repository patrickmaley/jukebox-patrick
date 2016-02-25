package controller;

import java.util.HashMap;

import model.CardReader;
import model.Song;

/*
 * TODO: 
 * 1.Complete authentication for usernames and password. And get testing code for it
 * 2.Create functionality for the 3 song limit
 * 3.Queue for playlist functionality
 * 4.Create local date variable, implement it and Reset playtime and songs played variables with local dates
 * 5.Tests for playlimit time and amount of times played for users and songs
 * 6.DONE- Finish song class with pathnames variable.
 * *Work on the GUI
 * 
 */
public class JukeBox {
	
	HashMap<Integer, Song> songCollection;
	CardReader cardReader = new CardReader();
	
	public JukeBox(){
		songCollection = new HashMap<>();
		addSongsToSongCollection();
	}
	
	private void addSongsToSongCollection() {
		Song songOne = new Song("Unknown", "Danse Macabre Violin Hook", "./songfiles/DanseMacabreViolinHook.mp3", 34);
		Song songTwo = new Song("Unknown", "Determined Tumbao", "./songfiles/DeterminedTumbao.mp3", 20);
		Song songThree = new Song("Unknown", "Flute", "./songfiles/flute.mp3", 5);
		Song songFour = new Song("Unknown", "Loping Flute", "./songfiles/LopingString.mp3", 4);
		Song songFive = new Song("Unknown", "Space Music", "./songfiles/spacemusic.mp3", 5);
		Song songSix = new Song("Unknown", "Swing Cheese", "./songfiles/SwingCheese.mp3", 15);
		Song songSeven = new Song("Unknown", "TaDa", "./songfiles/tada.mp3", 1);
		Song songEight = new Song("Unknown", "TheCurtainRises", "./songfiles/TheCurtainRises.mp3", 28);
		Song songNine = new Song("Unknown", "Untameable Fire", "./songfiles/UntameableFire.mp3", 281);
		
		this.songCollection.put(1, songOne);
		this.songCollection.put(2, songTwo);
		this.songCollection.put(3, songThree);
		this.songCollection.put(4, songFour);
		this.songCollection.put(5, songFive);
		this.songCollection.put(6, songSix);
		this.songCollection.put(7, songSeven);
		this.songCollection.put(8, songEight);
		this.songCollection.put(9, songNine);
	}

	public void setupView(){
		
	}
	
	public boolean canPlay(Song song){
		return false;
		
	}
	
	public void addToPlayList(Song song){
		
	}
}

