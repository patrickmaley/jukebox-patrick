package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongCollectionTableModel implements TableModel, ListModel{
	
	private SongCollection songCollection;
	
	private static final List<String> COLUMN_NAMES = Collections.unmodifiableList(
			Arrays.asList(new String[] {"Artist", "Title", "Length(Sec)"}));
	
	public SongCollectionTableModel(){
		this.songCollection = SongCollection.makeSongCollection();
		
	}
	@Override
	public int getRowCount() {
		return this.songCollection.getSize();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES.get(columnIndex);
	}

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

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

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
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

}
