package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import model.AccountCollection;
import model.CardReader;
import model.JukeBoxAccount;
import model.Playlist;
import model.Song;
import model.SongCollection;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;
import view.PlaylistTableModel;
import view.SongCollectionTableModel;

/**@author Brian Wehrle (brianwehrle@email.arizona.edu)
 * @author Patrick Maley (pmaley@email.arizona.edu)
 * 
 *Class: CSC 335
 * 
 *Project: JukeBox Iteration 2
 * 
 *Date: March 8th, 2016
 *
 *Professor: Dr. Mercer
 *
 *Section Lead: Cindy Trieu
 *
 *Class Description: JukeBox class creates the controller for the Jukebox. It creates
 *the hashmap for the song collection and instantiates the objects for the CardReader
 *and Playlist classes. This sets up the userAccount for who is using the jukebox
 *and it also sets up the view. JukeBox logic is also created in some methods
 *
 *
 */
@SuppressWarnings("serial")
public class JukeBox extends JFrame{
	private boolean playlistWatcher = true; 
	
	private CardReader cardReader;
	private LocalDate theDate;
	private JukeBoxAccount userAccount;
	
	private JButton addSong = new JButton("Add Song");
	private JButton signInButton = new JButton("Sign In");
	private JButton signOutButton = new JButton("Sign Out");
	
	private JPanel signInPanel = new JPanel(new FlowLayout());
	private JPanel titlePanel = new JPanel();
	private JPanel songlistPanel = new JPanel(new FlowLayout());
	private JPanel playlistPanel = new JPanel(new FlowLayout());
	private JPanel textPanel = new JPanel(new FlowLayout());
	private JPanel statusPanel = new JPanel(new FlowLayout());
	
	private JTextField signInText = new JTextField();

	private JPasswordField passwordText = new JPasswordField();
	private final JTextField TITLE_TEXT = new JTextField("MondoPlayer JukeBox");

	
	private JTextArea textField = new JTextArea("Hello, please sign in.");
	private JTextArea statusField = new JTextArea("Status: - songs played\n - minutes remaining"); 
	
	private AccountCollection accountCollection;// = AccountCollection.makeAccountCollection();
	private SongCollection songlistCollection; //= SongCollection.makeSongCollection();
	private TableModel songlistModel = null;
	private JTable songListTable = null;
	
	private Playlist playlistCollection;// = Playlist.makePlayCollection();
	private TableModel playlistModel = null;
	private JTable playlistTable = null;
	private JScrollPane playlistScrollPane = null;
	
	private RowSorter<TableModel> rowSorter = null;
	private final Dimension STATUS_PANEL_DIMENSION = new Dimension(200, 150);
	

	//Constructor to set up the view of the JukeBox and create the necessary objects
	public JukeBox(){
		theDate = LocalDate.now();
		frameProperties();
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				int save = JOptionPane.showConfirmDialog(((Component) null), "Save data?", "Select an option", JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (save == JOptionPane.YES_OPTION) {
					try {
						FileOutputStream file = new FileOutputStream("my.save");
						ObjectOutputStream out = new ObjectOutputStream(file);
						out.writeObject(JukeBox.this.songlistCollection);
						out.writeObject(JukeBox.this.playlistCollection);
						out.writeObject(JukeBox.this.accountCollection);
						out.writeObject(JukeBox.this.cardReader);
						out.close();
						file.close();
						file.flush();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					
					System.exit(0);
				} else if (save == JOptionPane.NO_OPTION) {
					System.exit(0);
					
				}
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				int load = JOptionPane.showConfirmDialog(((Component) null), "Start with previous saved state?", "Select an option", JOptionPane.YES_NO_OPTION);
				if (load == JOptionPane.YES_OPTION) {
					try {
						FileInputStream input = new FileInputStream("my.save");
						ObjectInputStream in = new ObjectInputStream(input);
						
						JukeBox.this.songlistCollection = (SongCollection) in.readObject();
						JukeBox.this.playlistCollection = Playlist.makePlayCollection((Playlist) in.readObject());
						JukeBox.this.accountCollection = (AccountCollection) in.readObject();
						JukeBox.this.cardReader = (CardReader) in.readObject();
						
						in.close();
						input.close();
					} catch (ClassNotFoundException c) {
						System.out.println("Class not found.");
						c.printStackTrace();
					} catch (FileNotFoundException ex) {
						System.out.println("Could not open file.");
						ex.printStackTrace();
					} catch (IOException exc) {
						System.out.println("IOException");
						exc.printStackTrace();
					}
					
				} else if (load == JOptionPane.NO_OPTION) {
					JukeBox.this.accountCollection = AccountCollection.makeAccountCollection();
					JukeBox.this.songlistCollection = SongCollection.makeSongCollection();
					JukeBox.this.playlistCollection = Playlist.makePlayCollection(null);
					JukeBox.this.cardReader = CardReader.makeCardReader(accountCollection);
				}				
				addComponents();
				// sign in the previously loaded account
				if (cardReader.getCurrentAccount() != null) {
					userAccount = cardReader.getCurrentAccount();
					textField.setText("Welcome, "  + cardReader.getCurrentAccount().getName());
					statusField.setText("Status: " + userAccount.getNumberOfSongsPlayed() + " songs played \n");
				 	statusField.append(userAccount.getPlayTime() / 60 + " minutes remaining");
				 	addSong.setEnabled(true);
				 	playlistWatcher = false;
				}			
				
				playlistTable.repaint();
				
				if (playlistCollection.peek() != null){
					play();
				}				
			}
			
		});
	}
	
	//Sets up the layout of the GUI.
	private void frameProperties() {
		setTitle("MondoPlayer JukeBox");
		setLayout(new FlowLayout());
		setSize(1125, 725);
		setLocation(0, 0);
		//setResizable(false);
		getContentPane().setBackground(new Color(192, 223, 217));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	

	//Adds the buttons, textfields, and login screen for the JFrame
	private void addComponents() {
		
		//Set title of the Player
		Font titleFont = new Font("Bodoni MT Black", 1, 35);
		TITLE_TEXT.setSize(65, 65);
		TITLE_TEXT.setFont(titleFont);
		TITLE_TEXT.setBackground(new Color(192, 223, 217));
		TITLE_TEXT.setEditable(false);
		titlePanel.add(TITLE_TEXT);
		add(titlePanel);
		
		Font displayFont = new Font("AngsanaUPC", 1, 14);
		
		//Sets up the sign In Panel where the user inputs information
		JLabel userNameLabel = new JLabel("User Name");
		JLabel passwordLabel = new JLabel("Password  ");
		userNameLabel.setForeground(new Color(59, 58, 54));
		userNameLabel.setLabelFor(signInText);
		userNameLabel.setFont(displayFont);
		passwordLabel.setForeground(new Color(59, 58, 54));
		passwordLabel.setLabelFor(passwordText);
		passwordLabel.setFont(displayFont);		

		signInPanel.setPreferredSize(STATUS_PANEL_DIMENSION);
		signInPanel.add(userNameLabel);
		signInText.setPreferredSize(new Dimension(100, 25));
		signInPanel.add(signInText);
		signInPanel.add(passwordLabel);
		passwordText.setPreferredSize(new Dimension(100, 25));
        signInPanel.add(passwordText);
        signInPanel.add(this.signInButton);
        signInPanel.add(this.signOutButton);
        signInPanel.setBackground(new Color(179, 194, 191));
        add(signInPanel);
		
        //This will display the user status on how many songs and minutes the user has left
        statusPanel.setPreferredSize(STATUS_PANEL_DIMENSION);
        statusPanel.setBackground(new Color(59, 58, 54));
  		statusField.setPreferredSize(STATUS_PANEL_DIMENSION);
  		statusField.setLineWrap(true);
		statusField.setBackground(new Color(59, 58, 54));
		statusField.setForeground(Color.WHITE);
		statusField.setEditable(false);
		statusPanel.add(statusField);
		add(statusPanel);
		
		 //This will display messages to the user on whether they successfully logged in or not -PM
        textPanel.setPreferredSize(STATUS_PANEL_DIMENSION);
        textPanel.setBackground(new Color(59, 58, 54));
  		textField.setSize(STATUS_PANEL_DIMENSION);
  		textField.setEditable(false);
  		textField.setLineWrap(true);
  		textField.setBackground(new Color(59, 58, 54));
  		textField.setForeground(Color.WHITE);
  		textPanel.add(textField);
  		add(textPanel);
  	
  		//This will display the Song List to the user with ScrollPane capabilities
		Font playlistFont = new Font("AngsanaUPC", 1,20);
		TitledBorder songlistTitle = BorderFactory.createTitledBorder("Song List");
		songlistTitle.setTitleColor(Color.WHITE);
		this.songlistModel = new SongCollectionTableModel();
		this.songListTable = new JTable(this.songlistModel);
		JScrollPane songlistScrollPane = new JScrollPane(this.songListTable);
		rowSorter = new TableRowSorter<TableModel>(this.songlistModel);
		this.songListTable.setRowSorter(rowSorter);
		songListTable.setPreferredSize(new Dimension(400,490));
		songListTable.setBackground(new Color(233, 236, 229));
		songlistPanel.setPreferredSize(new Dimension(500, 520));
		songlistPanel.setBackground(new Color(59, 58, 54));
		songlistPanel.setBorder(songlistTitle);
		songlistPanel.add(songlistScrollPane, BorderLayout.NORTH);
		add(songlistPanel);
		
		//This is the button used to transfer songs to the playlist
		add(addSong);
		addSong.setEnabled(false);
		
		//This will display the play list to the user with scrollPane capabilities
		TitledBorder playlistTitle = BorderFactory.createTitledBorder("Play List");
		playlistTitle.setTitleColor(Color.white);
		this.playlistModel = new PlaylistTableModel();
		this.playlistTable = new JTable(this.playlistModel);
	    playlistScrollPane = new JScrollPane(this.playlistTable);
		playlistTable.setPreferredSize(new Dimension(400, 490));
		playlistTable.setBackground(new Color(233, 236, 229));
		playlistPanel.setPreferredSize(new Dimension(500, 520));
		playlistPanel.setBackground(new Color(59, 58, 54));
		playlistPanel.setBorder(playlistTitle);
		playlistPanel.add(playlistScrollPane, BorderLayout.NORTH);
		add(playlistPanel);
		this.revalidate();	
		
		addSong.addActionListener(new addSongListener());
		signInButton.addActionListener(new SignInListener());
		signOutButton.addActionListener(new SignOutListener());
				
	}
	
	private class addSongListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//int songRow = rowSorter.convertRowIndexToView(getX());
			int selectedRow = JukeBox.this.songListTable.convertRowIndexToModel(
					JukeBox.this.songListTable.getSelectedRow());
			
			
			Song songChosen = songlistCollection.getElementAt(selectedRow);
			
			if (canPlay(songChosen)) {
				String userTime = "";
				userAccount.incrementNumberOfSongsPlayed();
				userAccount.subtractPlayTime(songChosen);
				userTime = String.format("Free Time Left: %02d:%02d:%02d", userAccount.getPlayTime() / 60 / 60, //Hours
						 userAccount.getPlayTime() / 60 % 60, //Minutes
						 userAccount.getPlayTime() % 60 % 60);//Seconds
				statusField.setText("| Status: " + userAccount.getNumberOfSongsPlayed() + " songs played \n" 
				+ userTime);


				if (playlistWatcher){
					play();
					playlistWatcher = false;
				}
			}
			if (playlistWatcher){
				play();
				playlistWatcher = false;
			}
		}
		
	}
	
	//When a song is in the playlist this will play it
	private void play() {		
		EndOfSongListener waitForSongEnd = new WaitingForSongToEnd();
		SongPlayer.playFile(waitForSongEnd, playlistCollection.peek().getPathName());
	}
	
	//When a song completes, this event will kick off and either play the
	//next song in the queue or stop.
	private class WaitingForSongToEnd implements EndOfSongListener {
		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			playlistCollection.removeSong();
			playlistTable.repaint();
			
			if (playlistCollection.peek() != null) {
				play();
				//System.out.println("numPlays: " + userAccount.getNumberOfSongsPlayed() + "\n " + songCollection.get(4).getNumPlays());
			} else{
				playlistWatcher = true;
			}
		}
	}
	
	//This takes the information inputted into the sign in boxes
	//and authenticates them with the records of the users in 
	//the accountCollection
	private class SignInListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int password = Integer.parseInt(passwordText.getText());
			
			 if (cardReader.readAccount(signInText.getText(), password)) {
				 String userTime = "";
				 userAccount = cardReader.getCurrentAccount();
				 textField.setText("Welcome, "  + cardReader.getCurrentAccount().getName());
				 statusField.setText("Status: " + userAccount.getNumberOfSongsPlayed() + " songs played \n");
				 userTime = String.format("Free Time Left: %02d:%02d:%02d", userAccount.getPlayTime() / 60 / 60, //Hours
						 userAccount.getPlayTime() / 60 % 60, //Minutes
						 userAccount.getPlayTime() % 60 % 60);//Seconds
				 statusField.append(userTime);
				 addSong.setEnabled(true);
			 } else {
				 textField.setText("Account login failed");
				 JOptionPane.showMessageDialog(null, "Account login failed");
			 }
		}
	}
	
	//This sets the currentAccount to null and deactivates buttons in the GUI
	//to prevent any further songs from being added to the playlist
	private class SignOutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			userAccount = null;
			cardReader.signOut();
			textField.setText("Successfully signed out");
			statusField.setText("Status: - \n, ----");
			addSong.setEnabled(false);
		}
	}

	/**Determines whether or not the user can play the song and if the local dates have changed.
	 * If the song is able to be played, it adds it to the playlist and updates the JTable.
	 * 
	 * @param song
	 *		The song object that is being added to the playlist.
	 * @return
	 * 		A boolean indicating if the song is able to be played.
	 */

	public boolean canPlay(Song song){
		LocalDate dateChecker = LocalDate.now();
		if(this.theDate.getDayOfYear() < dateChecker.getDayOfYear()){
			cardReader.getAccountCollection().resetPlays();
			songlistCollection.resetSongs();
			theDate = dateChecker;//Change the programs date to the new date of today-PM
		}
		
		//Checks both the number of times the song has been chosen and the number of times
		//the user has chosen a song and if the user has any playtime left -PM
		if(song.getNumPlays() < 3 && this.userAccount.canPlaySong() && this.userAccount.getPlayTime() - song.getSongLength() > 0){ 
			playlistCollection.addSong(song);
			playlistTable.repaint();
			song.setNumPlays(song.getNumPlays() + 1);
			return true;
		}else{
			//Return a jpanel saying the limit has been reached-PM
			if(song.getNumPlays() >=3){
				textField.setText("The allotted song plays has been reached for this song. ");
			}
			if(!this.userAccount.canPlaySong()){
				textField.setText("You have reached the alloted limit for the amount of songs you can play");
			}
		}
		return false;
	}
	
}

