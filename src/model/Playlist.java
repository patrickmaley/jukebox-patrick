package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
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
 *Class Description: Playlist is a class that we want to refactor. And we probably will for
 *iteration 2. That is all.
 */
public class Playlist implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8812361899528992300L;
	
	Queue<Song> playlist;
	int songCount;
	private static Playlist uniquePlayCollection = null;
	
	private Playlist(){
		this.playlist = new LinkedList<>();
		songCount = 0;	
	}
	
	public static Playlist makePlayCollection() {
		if (uniquePlayCollection == null)
			uniquePlayCollection = new Playlist();
		return uniquePlayCollection;
	}
	
	
	// adds a song to the playlist, and increments songCount
	public void addSong(Song song){
		this.playlist.add(song);
		songCount++;
	}
	
	// removes a song from the playlist, and decrements songCount
	public Song removeSong(){
		songCount--;
	    return this.playlist.remove();
	}

	// returns songCount
	public int getSongCount(){
		return this.songCount;
	}
	
	// peeks at the top song on the queue
	public Song peek(){
		return this.playlist.peek();
	}
	
	public int getSize(){
		return this.playlist.size();
	}
	
}
