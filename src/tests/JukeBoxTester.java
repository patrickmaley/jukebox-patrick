package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Test;

import model.AccountCollection;
import model.JukeBoxAccount;
import model.Playlist;
import model.Song;

import model.CardReader;

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
 *Class Description: JukeBoxTester tests the model classes of this program.
 */

public class JukeBoxTester {
	
	//Source code for iterating through an hashmap
	@Test

	public void accountCollectionTest() {
		AccountCollection allUsers = AccountCollection.makeAccountCollection();
		int users = 0;
		Iterator<Entry<Integer, JukeBoxAccount>> itr = allUsers.getAccountCollection().entrySet().iterator();
		while(itr.hasNext()){
		    itr.next();
			users++;
		}
		assertEquals(4, users);
	}
	
	@Test
	public void accountCollectionUserTest() {
		AccountCollection allUsers = AccountCollection.makeAccountCollection();
		HashMap<Integer, JukeBoxAccount> accounts = allUsers.getAccountCollection();
		
		assertEquals("Chris", accounts.get(1).getName());
		assertEquals("Devon", accounts.get(22).getName());
		assertEquals("River", accounts.get(333).getName());
		assertEquals("Ryan", accounts.get(4444).getName());
	}
	
	@Test
	public void accountCollectionUserPlaysTest() {
		AccountCollection allUsers = AccountCollection.makeAccountCollection();
		Song songOne = new Song("Unknown", "Danse Macabre Violin Hook", "./songfiles/DanseMacabreViolinHook.mp3", 34);
		HashMap<Integer, JukeBoxAccount> accounts = allUsers.getAccountCollection();
		
		assertEquals(0, accounts.get(1).getNumberOfSongsPlayed());
		assertEquals(0, accounts.get(22).getNumberOfSongsPlayed());
		assertEquals(0, accounts.get(333).getNumberOfSongsPlayed());
		assertEquals(0, accounts.get(4444).getNumberOfSongsPlayed());
		
		accounts.get(1).incrementNumberOfSongsPlayed();
		assertEquals(1, accounts.get(1).getNumberOfSongsPlayed());
		assertFalse(0 == accounts.get(1).getNumberOfSongsPlayed());
		
		accounts.get(1).incrementNumberOfSongsPlayed();
		accounts.get(1).incrementNumberOfSongsPlayed();
		accounts.get(1).incrementNumberOfSongsPlayed();
		assertEquals(4, accounts.get(1).getNumberOfSongsPlayed());
		
		assertFalse(accounts.get(1).canPlaySong());
		assertTrue(accounts.get(22).canPlaySong());
		assertTrue(accounts.get(333).canPlaySong());
		assertTrue(accounts.get(4444).canPlaySong());
		
		accounts.get(4444).subtractPlayTime(songOne);
		assertEquals(89966, accounts.get(4444).getPlayTime());
		allUsers.resetPlays();
		assertEquals(0, accounts.get(1).getNumberOfSongsPlayed());
		assertEquals(0, accounts.get(22).getNumberOfSongsPlayed());
		assertEquals(0, accounts.get(333).getNumberOfSongsPlayed());
		assertEquals(0, accounts.get(4444).getNumberOfSongsPlayed());
	}
	
	@Test
	public void songTest() {
		Song songOne = new Song("Unknown", "Danse Macabre Violin Hook", "./songfiles/DanseMacabreViolinHook.mp3", 34);
		Song songTwo = new Song("Unknown", "Determined Tumbao", "./songfiles/DeterminedTumbao.mp3", 20);
	
		songOne.setNumPlays(2);
		assertEquals(2, songOne.getNumPlays());
		assertEquals(0, songTwo.getNumPlays());
		
		assertEquals("./songfiles/DanseMacabreViolinHook.mp3", songOne.getPathName());
		assertEquals("Determined Tumbao", songTwo.getSongName());
		
		assertEquals(34, songOne.getSongLength());
		assertEquals(20, songTwo.getSongLength());
	}
	
	@Test
	public void playListTest() {
		Playlist userSongs = Playlist.makePlayCollection();
		
		Song songOne = new Song("Unknown", "Danse Macabre Violin Hook", "./songfiles/DanseMacabreViolinHook.mp3", 34);
		Song songTwo = new Song("Unknown", "Determined Tumbao", "./songfiles/DeterminedTumbao.mp3", 20);
		Song songThree = new Song("Unknown", "Flute", "./songfiles/flute.mp3", 5);
		Song songFour = new Song("Unknown", "Loping Flute", "./songfiles/LopingString.mp3", 4);
		Song songFive = new Song("Unknown", "Space Music", "./songfiles/spacemusic.mp3", 5);
		Song songSix = new Song("Unknown", "Swing Cheese", "./songfiles/SwingCheese.mp3", 15);
		Song songSeven = new Song("Unknown", "TaDa", "./songfiles/tada.mp3", 1);
		Song songEight = new Song("Unknown", "TheCurtainRises", "./songfiles/TheCurtainRises.mp3", 28);
		Song songNine = new Song("Unknown", "Untameable Fire", "./songfiles/UntameableFire.mp3", 281);
		
		userSongs.addSong(songOne);
		userSongs.addSong(songTwo);
		userSongs.addSong(songThree);
		userSongs.addSong(songFour);
		userSongs.addSong(songFive);
		userSongs.addSong(songSix);
		userSongs.addSong(songSeven);
		userSongs.addSong(songEight);
		userSongs.addSong(songNine);
		
		assertEquals(9, userSongs.getSongCount());
		assertEquals(songOne, userSongs.removeSong());
		
		assertEquals(songTwo, userSongs.peek());
	}
	
	@Test
	public void CardReaderTest() {
		CardReader cardReader = new CardReader();
		
		assertEquals(true, cardReader.readAccount("Chris", 1));
		assertEquals(false, cardReader.readAccount("Chris", 22));
		
		cardReader.readAccount("Chris", 1234); // not an existing password
		assertEquals(null, cardReader.getCurrentAccount());
	}
	
	@Test
	public void dateResetTest() {
		AccountCollection allUsers = AccountCollection.makeAccountCollection();
		HashMap<Integer, JukeBoxAccount> accounts = allUsers.getAccountCollection();
		LocalDate dateChecker = LocalDate.now();
		LocalDate theDate = LocalDate.now();
		
		accounts.get(1).incrementNumberOfSongsPlayed();
		assertEquals(1, accounts.get(1).getNumberOfSongsPlayed());
		assertFalse(0 == accounts.get(1).getNumberOfSongsPlayed());
		
		if(theDate.getDayOfYear() < dateChecker.getDayOfYear() +1){
			allUsers.resetPlays();
			theDate = dateChecker;//Change the programs date to the new date of today-PM
		}
		
		assertEquals(0, accounts.get(1).getNumberOfSongsPlayed());
		assertTrue(0 == accounts.get(1).getNumberOfSongsPlayed());
	}
}
