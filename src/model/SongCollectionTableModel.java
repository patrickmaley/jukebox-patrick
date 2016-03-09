package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * 
 * @author Brian Wehrle
 * @author Patrick Maley
 * 
 * Class Description: SongCollectionTableModel adapts our SongCollection class to be displayed as a 
 * TableModel object. It provides all the necessary methods to implement TableModel.
 */
public class SongCollectionTableModel implements TableModel{
	
	private SongCollection songCollection;
	
	private static final List<String> COLUMN_NAMES = Collections.unmodifiableList(
			Arrays.asList(new String[] {"Artist", "Title", "Length"}));
	
	public SongCollectionTableModel(){
		this.songCollection = SongCollection.makeSongCollection();
		
	}
	/**
	 * Returns the number of rows in the songCollection.
	 * 
	 * @return The number of rows in the songCollection.
	 */
	@Override
	public int getRowCount() {
		return this.songCollection.getSize();
	}

	/**
	 * Returns the number of columns in the songCollection.
	 * 
	 * @return The number of columns in the songCollection.
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
	 * @param rowIndex 
	 * 		Index of the row.
	 * 
	 * @param columnIndex
	 * 		Index of the column
	 * 
	 * @return The value of the object at the specified cell.
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Song selectedSong = this.songCollection.getElementAt(rowIndex);
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

	/**
	 * Sets the value at specified cell.
	 * 
	 * @param aValue
	 * 		The value to be set.
	 * 
	 * @param rowIndex
	 * 		The index of the row.
	 * 
	 * @param columnIndex
	 * 		The index of the column.
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Adds a Listener to the table
	 * 
	 * @param l
	 * 		The listener to be added.
	 */
	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Removes a listener from the table.
	 * 
	 * @param The listener to be removed.
	 */
	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

}
