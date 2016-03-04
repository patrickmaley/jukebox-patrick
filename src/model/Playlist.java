package model;

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
