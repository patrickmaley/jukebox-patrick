package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.CardReader;
import model.JukeBoxAccount;
import model.Playlist;
import model.PlaylistTableModel;
import model.Song;
import model.SongCollection;
import model.SongCollectionTableModel;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

/*Iteration 2:
 * TODO:
 * 1.GUI: -Patrick 
 * 		-Create a view package
 * 		-Change size of the window.
 * 		-Add JLists
 * 		-Allow multiple users to login
 * 		-select songs
 * 		-maintain a playlist of songs 
 * 		-maintain a songlist of songs to choose from
 * 		-JPasswordField into view
 * 2.Persistence:
 * 		-writing serialized objects to disk - DO THIS LAST?
 * 		-A lot 
 * 3. Implement Design patterns: Use the following design patterns - Brian
 * 		-Adapter: two collections are adapted to JTable and JList with the 
 * 				interfaces TableModel and ListModel
 * 		-Adapter: Have your WindowListener extend WindowAdapter
 * 		-SingleTon: Two collections must be singletons so we dont have two different versionsof songs or accounts
 * 		-Decorator: Decorate your Jtable and JList with a JScrollPane
 * 		-Decorator: Decorate to classes with others to persist data
 * 
 * 4. Playlist can be sorted by artists
 * 
 * Iteration 1:
 * TODO: 
 * 1. DONE- Complete authentication for usernames and password. And get testing code for it
 * 2. DONE- Create functionality for the 3 song limit
 * 3. DONE- Queue for playlist functionality
 * 4. DONE- Create local date variable, implement it and Reset playtime and songs played variables with local dates
 * 5. DONE- Tests for playlimit time and amount of times played for users and songs
 * 6. DONE- Finish song class with pathnames variable.
 * 7. In Progress- Work on the GUI
 * 		-Have the general view for iteration 1. 
 * 8. DONE: Implement listeners for the buttons
 */
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
 *Class Description: JukeBox class creates the controller for the Jukebox. It creates
 *the hashmap for the song collection and instantiates the objects for the CardReader
 *and Playlist classes. This sets up the userAccount for who is using the jukebox
 *and it also sets up the view. JukeBox logic is also created in some methods
 *
 *
 */
@SuppressWarnings("serial")
public class JukeBox extends JFrame{
	
	private HashMap<Integer, Song> songCollection;
	private CardReader cardReader;
	//private Playlist chosenSongs;
	private LocalDate theDate;
	private JukeBoxAccount userAccount;
	private JButton addSong = new JButton("Add Song");
	//private JButton selectSongTwo = new JButton("Select Song Two");
	private JButton signInButton = new JButton("Sign In");
	private JButton signOutButton = new JButton("Sign Out");
	
	private JPanel buttonsPanel = new JPanel();
	private JPanel signInPanel = new JPanel(new FlowLayout());
	private JPanel titlePanel = new JPanel();
	private JPanel songlistPanel = new JPanel(new FlowLayout());
	private JPanel playlistPanel = new JPanel(new FlowLayout());
	private JPanel textPanel = new JPanel(new FlowLayout());
	private JPanel statusPanel = new JPanel(new FlowLayout());
	
	private JTextField signInText = new JTextField();
	private JTextField passwordText = new JTextField();
	private JTextField titleText = new JTextField("MondoPlayer JukeBox");
	private JTextArea textField = new JTextArea("Hello, please sign in.");
	private JTextArea statusField = new JTextArea("Status: - songs played, - minutes remaining"); 
	
	private JTextArea playlistList2 = new JTextArea("THIS IS A PLACEHOLDER UNTIL WE TURN IN");
	private boolean playlistWatcher = true;
	
	private SongCollection songlistCollection = SongCollection.makeSongCollection();
	private TableModel songlistModel = null;
	private JTable songListTable = null;
	
	private Playlist playlistCollection = Playlist.makePlayCollection();
	private TableModel playlistModel = null;
	private JTable playlistTable = null;
	
	//Initiates the GUI and the player logic
	public static void main(String[] args) {
		new JukeBox().setVisible(true);
	}
	
	//Constructor to set up the view of the JukeBox and create the necessary objects
	public JukeBox(){
		songCollection = new HashMap<>();
		cardReader = new CardReader();
		//chosenSongs = new Playlist();
		theDate = LocalDate.now();
		
		addSongsToSongCollection();
		frameProperties();
	}
	
	//Sets up the layout of the GUI.
	private void frameProperties() {
		setTitle("MondoPlayer JukeBox");
		setLayout(new FlowLayout());
		setSize(1550, 1000);
		setLocation(200,200);
		setResizable(false);
		getContentPane().setBackground(new Color(192, 223, 217));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Adds all the components to the JFrame -PM
		addComponents();
	}	

	//Adds the buttons, textfields, and login screen for the JFrame
	private void addComponents() {
		//Set title of the Player
		Font titleFont = new Font("Bodoni MT Black", 1, 50);
		titleText.setSize(100, 100);
		titleText.setFont(titleFont);
		titleText.setBackground(new Color(192, 223, 217));
		titleText.setEditable(false);
		titlePanel.add(titleText);
		add(titlePanel);
		
		Font displayFont = new Font("AngsanaUPC", 1, 30);
		
		//Sign In Panel where the user inputs information
		JLabel userNameLabel = new JLabel("User Name");
		JLabel passwordLabel = new JLabel("Password  ");
		userNameLabel.setForeground(new Color(59, 58, 54));
		userNameLabel.setLabelFor(signInText);
		userNameLabel.setFont(displayFont);
		passwordLabel.setForeground(new Color(59, 58, 54));
		passwordLabel.setLabelFor(passwordText);
		passwordLabel.setFont(displayFont);
		
		signInPanel.setPreferredSize(new Dimension(275,275));
		signInPanel.add(userNameLabel);
		signInText.setPreferredSize(new Dimension(150, 50));
		signInPanel.add(signInText);
		signInPanel.add(passwordLabel);
		passwordText.setPreferredSize(new Dimension(150, 50));
        signInPanel.add(passwordText);
        signInPanel.add(this.signInButton);
        signInPanel.add(this.signOutButton);
        signInPanel.setBackground(new Color(179, 194, 191));
        add(signInPanel);
		
        //This will display the user status on how many songs and minutes the user has left
        statusPanel.setPreferredSize(new Dimension(275,200));
        statusPanel.setBackground(new Color(59, 58, 54));
  		statusField.setSize(50, 500);
		statusField.setBackground(new Color(59, 58, 54));
		statusField.setForeground(Color.WHITE);
		statusPanel.add(statusField);
		add(statusPanel);
		
		 //This will display messages to the user on whether they successfully logged in or not -PM
        textPanel.setPreferredSize(new Dimension(275,200));
        textPanel.setBackground(new Color(59, 58, 54));
  		textField.setSize(300, 200);
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
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(this.songlistModel);
		this.songListTable.setRowSorter(rowSorter);
		songListTable.setPreferredSize(new Dimension(400,490));
		songListTable.setBackground(new Color(233, 236, 229));
		songlistPanel.setPreferredSize(new Dimension(500, 520));
		songlistPanel.setBackground(new Color(59, 58, 54));
		songlistPanel.setBorder(songlistTitle);
		
		//playlistPanel.add(songListTable);
		songlistPanel.add(songlistScrollPane, BorderLayout.NORTH);
		add(songlistPanel);
		this.revalidate();
		
		
		//This is the button used to transfer songs to the playlist
		add(addSong);
		
		//This will display the play list to the user with scrollPane capabilities
		TitledBorder playlistTitle = BorderFactory.createTitledBorder("Play List");
		playlistTitle.setTitleColor(Color.white);
		this.playlistModel = new PlaylistTableModel();
		this.playlistTable = new JTable(this.playlistModel);
		JScrollPane playlistScrollPane = new JScrollPane(this.playlistTable);
		playlistTable.setPreferredSize(new Dimension(400, 490));
		playlistTable.setBackground(new Color(233, 236, 229));
		playlistPanel.setPreferredSize(new Dimension(500, 520));
		playlistPanel.setBackground(new Color(59, 58, 54));
		playlistPanel.setBorder(playlistTitle);
		playlistPanel.add(playlistScrollPane, BorderLayout.NORTH);
		add(playlistPanel);
		this.revalidate();
		
		//Song Panel where the user chooses information
//		buttonsPanel.add(selectSongOne);
//		buttonsPanel.add(selectSongTwo);
//		buttonsPanel.setBackground(new Color(221, 224, 205));
//		add(buttonsPanel,constraints);
		
		// grey out select song buttons
//		selectSongOne.setEnabled(false);
//		selectSongTwo.setEnabled(false);
		addSong.addActionListener(new addSongListener());
		signInButton.addActionListener(new SignInListener());
		signOutButton.addActionListener(new SignOutListener());
		//selectSongOne.addActionListener(new SongOneListener());
		//selectSongTwo.addActionListener(new SongTwoListener());
	}
	private class addSongListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int songRow = songListTable.getSelectedRow();
			Song songChosen = songlistCollection.getElementAt(songRow);
			
			if (canPlay(songChosen)) {
				userAccount.incrementNumberOfSongsPlayed();
				userAccount.subtractPlayTime(songChosen);
				statusField.setText("| Status: " + userAccount.getNumberOfSongsPlayed() + " songs played, " + userAccount.getPlayTime() / 60 + " minutes remaining");

				if(playlistWatcher){
					play();
					playlistWatcher = false;
				}
			}
			if(playlistWatcher){
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
				// selectSongOne.setEnabled(true);
				// selectSongTwo.setEnabled(true);
				 userAccount = cardReader.getCurrentAccount();
				 textField.setText("Welcome, "  + cardReader.getCurrentAccount().getName());
				 statusField.setText("| Status: " + userAccount.getNumberOfSongsPlayed() + " songs played, " + userAccount.getPlayTime() / 60 + " minutes remaining");
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
			
			//selectSongOne.setEnabled(false);
			//selectSongTwo.setEnabled(false);
			
			textField.setText("Successfully signed out");
			statusField.setText("| Status: -, ----");
		}
		
	}

	//This adds the first song to the playlist
	private class SongOneListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Song songOne = songCollection.get(7);
			if (canPlay(songOne)) {
				userAccount.incrementNumberOfSongsPlayed();
				userAccount.subtractPlayTime(songOne);
				statusField.setText("| Status: " + userAccount.getNumberOfSongsPlayed() + " songs played, " + userAccount.getPlayTime() / 60 + " minutes remaining");

				if(playlistWatcher){
					play();
					playlistWatcher = false;
				}
			}
		}
	}
	

	//Adds all the songs to the songCollection
	private void addSongsToSongCollection() {
		Song songOne = new Song("Kevin MacLeod", "Danse Macabre Violin Hook", "./songfiles/DanseMacabreViolinHook.mp3", 34);
		Song songTwo = new Song("FreePlay Music", "Determined Tumbao", "./songfiles/DeterminedTumbao.mp3", 20);
		Song songThree = new Song("Sun Microsystems", "Flute", "./songfiles/flute.aif", 5);
		Song songFour = new Song("Unknown", "Loping Flute", "./songfiles/LopingSting.mp3", 4);
		Song songFive = new Song("Unknown", "Space Music", "./songfiles/spacemusic.au", 6);
		Song songSix = new Song("FreePlay Music", "Swing Cheese", "./songfiles/SwingCheese.mp3", 15);
		Song songSeven = new Song("Microsoft", "TaDa", "./songfiles/tada.wav", 2);
		Song songEight = new Song("Kevin MacLeod", "TheCurtainRises", "./songfiles/TheCurtainRises.mp3", 28);
		Song songNine = new Song("Kevin MacLeod", "Untameable Fire", "./songfiles/UntameableFire.mp3", 282);
		
		this.songCollection.put(1, songOne);
		this.songCollection.put(2, songTwo);
		this.songCollection.put(3, songThree);
		this.songCollection.put(4, songFour);
		this.songCollection.put(5, songFive);
		this.songCollection.put(6, songSix);
		this.songCollection.put(7, songSeven);
		this.songCollection.put(8, songEight);
		this.songCollection.put(9, songNine);
	}

	//Determines whether or not the user can play the song and if the local dates have changed
	public boolean canPlay(Song song){
		LocalDate dateChecker = LocalDate.now();
		if(this.theDate.getDayOfYear() < dateChecker.getDayOfYear()){
			cardReader.getAccountCollection().resetPlays();
			theDate = dateChecker;//Change the programs date to the new date of today-PM
		}
		
		//Checks both the number of times the song has been chosen and the number of times
		//the user has chosen a song -PM
		if(song.getNumPlays() < 3 && this.userAccount.canPlaySong()){ 
			playlistCollection.addSong(song);
			this.revalidate();
			song.setNumPlays(song.getNumPlays() + 1);
			return true;
			
			//add song++;

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
	
	//Adds songs to the playlist
//	public void addToPlayList(Song song){
//		chosenSongs.addSong(song);
//	}
}

