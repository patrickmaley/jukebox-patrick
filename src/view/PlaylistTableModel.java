package view;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Playlist;
import model.Song;
import model.SongCollection;




/**
 * 
 * @author Brian Wehrle
 * @author Patrick Maley
 * 
 * Class Description: PlayListTableModel adapts our Playlist class to be displayed as a 
 * TableModel object. It provides all the necessary methods to implement TableModel.
 */
public class PlaylistTableModel implements TableModel, ListModel{

	
	private Playlist playCollection;
	private SongCollection songCollection;
	private static final List<String> COLUMN_NAMES = Collections.unmodifiableList(
			Arrays.asList(new String[] {"Artist", "Title", "Length"}));
	
	public PlaylistTableModel(){
		this.playCollection = Playlist.makePlayCollection(null);
		
	}
	/** Retrieves the number of rows in the Table.
	 * 
	 * @return The number of rows.
	 */
	@Override
	public int getRowCount() {
		return this.playCollection.getSize();
	}
	
	/** Retrieves the number of columns in the Table.
	 * 
	 * @return The number of columns.
	 */
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.size();
	}

	/**
	 * Gets the column name at the specified index.
	 * 
	 *  @param columnIndex
	 *  	The index of the column.
	 *  
	 *  @return The name of the column at the specified index.
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES.get(columnIndex);
	}

	/**
	 * Gets the class of the elements held in the specified column.
	 * 
	 * @param columnIndex: 
	 * 		The index of the column
	 * 
	 * @return The class of the elements held in the column
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return Integer.class;
		default:
			return null;
	}
	}

	/**
	 * Determines if the cell is editable at the specified cell.
	 * 
	 * @param rowIndex
	 * 		The index of the row of the cell.
	 * 
	 * @param columnIndex
	 * 		The index of the column of the cell.
	 * 
	 * @return Boolean stating if the cell is editable.
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * Retrieves the value at the specified row and column.
	 * 
	 * @param 
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		int start = 0;
		Song selectedSong = null;
		LinkedList<Song> list = (LinkedList<Song>) this.playCollection.getPlaylist();
		for (Song song : list) {
			if(start == rowIndex){
				selectedSong = song;
				break;
			}
			start++;
		}
		
		switch (columnIndex) {
			case 0:
				// The first column is name
				return selectedSong.getSongName();
			case 1:
				// The second column is artist
				return selectedSong.getArtist();
			case 2:
				// The third column is length
				return selectedSong.getSongLength();
		
			default:
				// This case is unreachable, as our JTable will never use a
				// columnIndex value greater than 3
				return null;

		}
	
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

}

