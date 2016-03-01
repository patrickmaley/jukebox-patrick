package model;

import java.util.LinkedList;
import java.util.Queue;

public class Playlist {
	Queue<Song> playlist;
	int songCount;
	
	public Playlist(){
		this.playlist = new LinkedList<>();
		songCount = 0;
	}
	
	public void addSong(Song song){
		this.playlist.add(song);
		songCount++;
	}
	
	public Song removeSong(){
		songCount--;
	    return this.playlist.remove();
	}
	
	public int getSongCount(){
		return this.songCount;
	}
	
	public Song peek(){
		return this.playlist.peek();
	}
}
