package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
/**@author Brian Wehrle
 * @author Patrick Maley
 * 
 *Class Description: The object that stores the songs being played.
 * Songs are played in a FIFO order, in typical Queue fashion. Supports
 * standard Queue operations like add, remove, and peek. 
 */
public class Playlist implements Serializable{
	
	private static final long serialVersionUID = -8812361899528992300L;
	
	Queue<Song> playlist;
	int songCount;
	private static Playlist uniquePlayCollection = null;
	
	private Playlist(){
		this.playlist = new LinkedList<>();
		songCount = 0;	
	}
	
	/**
	 * Singleton implementation. If uniquePlayCollection is null,
	 * it constructs a new one. If not, it returns uniquePlayCollection.
	 * 
	 * @return The unique instance of this object.
	 */
	public static Playlist makePlayCollection() {
		if (uniquePlayCollection == null)
			uniquePlayCollection = new Playlist();
		return uniquePlayCollection;
	}
	
	/**
	 * Adds a song to the playlist, and increments songCount.
	 * 
	 * @param song
	 * 		The song to be added.
	 */
	public void addSong(Song song){
		this.playlist.add(song);
		songCount++;
	}
	
	/**
	 * Remove sa song from the playlist, and decrements songCount.
	 * 
	 * @return The song that was removed.
	 */
	public Song removeSong(){
		songCount--;
	    return this.playlist.remove();
	}

	/**
	 * Returns the number of songs in the playlist.
	 * 
	 * @return The number of songs in the playlist.
	 */
	public int getSongCount(){
		return this.songCount;
	}
	
	/**
	 * Looks at the song at the top of the queue.
	 * 
	 * @return The song at the top of the queue.
	 */
	public Song peek(){
		return this.playlist.peek();
	}
	
	/**
	 * Gets the size of the playlist.
	 * 
	 * @return The size of the playlist.
	 */
	public int getSize(){
		return this.playlist.size();
	}
	
}
