package model;

import java.util.LinkedList;
import java.util.Queue;

public class Playlist {
	Queue<Song> playlist;
	
	public Playlist(){
		this.playlist = new LinkedList<>();
	}
	
	public void addSong(Song song){
		this.playlist.add(song);
	}
	
	public void removeSong(){
		this.playlist.remove();
	}
}
