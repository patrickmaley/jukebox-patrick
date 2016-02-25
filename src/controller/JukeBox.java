package controller;

import java.util.HashMap;

import model.Song;

public class JukeBox {
	
	HashMap<Integer, Song> songCollection;
	
	public JukeBox(){
		songCollection = new HashMap<>();
		addSongsToSongCollection();
	}
	
	private void addSongsToSongCollection() {
		
		
	}

	public void setupView(){
		
	}
	
	public boolean canPlay(Song song){
		return false;
		
	}
	
	public void addToPlayList(Song song){
		
	}
}

