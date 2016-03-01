package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.CardReader;
import model.JukeBoxAccount;
import model.Playlist;
import model.Song;

/*
 * TODO: 
 * 1.Complete authentication for usernames and password. And get testing code for it
 * 2.Create functionality for the 3 song limit
 * 3.Queue for playlist functionality
 * 4.IN PROGRESS: NEED TESTING-Create local date variable, implement it and Reset playtime and songs played variables with local dates
 * 5.IN PROGRESS: NEED TESTING-Tests for playlimit time and amount of times played for users and songs
 * 6.DONE- Finish song class with pathnames variable.
 * 7.In Progress- Work on the GUI
 * 	-Have the general view for iteration 1. 
 * 	-TO DOS: Implement listeners for the buttons
 */
@SuppressWarnings("serial")
public class JukeBox extends JFrame{
	
	private HashMap<Integer, Song> songCollection;
	private CardReader cardReader;
	private Playlist chosenSongs;
	private LocalDate theDate;
	private JukeBoxAccount userAccount;
	private JButton selectSongOne = new JButton("Select Song One");
	private JButton selectSongTwo = new JButton("Select Song Two");
	private JButton signInButton = new JButton("Sign In");
	private JButton signOutButton = new JButton("Sign Out");
	
	private JPanel buttonsPanel = new JPanel();
	private JPanel signInPanel = new JPanel(new GridLayout(0,2));
	private JTextField signInText = new JTextField();
	private JPasswordField passwordText = new JPasswordField(10);
	private JTextArea textField = new JTextArea("Hello adasdfadfasddddd");
	
	//Initiates the GUI and the player logic
	public static void main(String[] args) {
		new JukeBox().setVisible(true);
	}
	
	
	public JukeBox(){
		songCollection = new HashMap<>();
		cardReader = new CardReader();
		chosenSongs = new Playlist();
		theDate = LocalDate.now();
		userAccount = cardReader.getCurrentAccount();
		addSongsToSongCollection();
		frameProperties();
	}
	
	private void frameProperties() {
		setTitle("CSC335 Music Player");
		setLayout(new FlowLayout());
		setSize(400, 400);
		setLocation(200,200);
		//this.setResizable(false);
		getContentPane().setBackground(new Color(100,100,100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Adds all the components to the JFrame -PM
		addComponents();
		addButtonListeners();
	}

	private void addButtonListeners() {
		selectSongOne.addActionListener(new ActionListener() {     
			@Override
			public void actionPerformed(ActionEvent arg0) {
				canPlay(songCollection.get(1));
			}
        });
		
		selectSongTwo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				canPlay(songCollection.get(2));
			}
		});
	}


	private void addComponents() {
		//Song Panel where the user chooses information
		buttonsPanel.add(selectSongOne);
		buttonsPanel.add(selectSongTwo);
		buttonsPanel.setBackground(new Color(100,100,100));
		add(buttonsPanel);
		
		//Sign In Panel where the user inputs information
		JLabel userNameLabel = new JLabel("User Name");
		JLabel passwordLabel = new JLabel("Password");
		userNameLabel.setForeground(Color.WHITE);
		userNameLabel.setLabelFor(signInText);
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setLabelFor(passwordText);
	
		signInPanel.add(userNameLabel);
		signInPanel.add(signInText);
		signInPanel.add(passwordLabel);
        signInPanel.add(passwordText);
        signInPanel.add(this.signInButton);
        signInPanel.add(this.signOutButton);
        signInPanel.setBackground(new Color(100,100,100));
		
		//This will display messages to the user on whether they successfully logged in or not -PM
		textField.setSize(50, 75);
		textField.setBackground(new Color(100,100,100));
		textField.setForeground(Color.WHITE);
		signInPanel.add(textField);
		
		add(signInPanel);
	}


	private void addSongsToSongCollection() {
		Song songOne = new Song("Unknown", "Danse Macabre Violin Hook", "./songfiles/DanseMacabreViolinHook.mp3", 34);
		Song songTwo = new Song("Unknown", "Determined Tumbao", "./songfiles/DeterminedTumbao.mp3", 20);
		Song songThree = new Song("Unknown", "Flute", "./songfiles/flute.mp3", 5);
		Song songFour = new Song("Unknown", "Loping Flute", "./songfiles/LopingString.mp3", 4);
		Song songFive = new Song("Unknown", "Space Music", "./songfiles/spacemusic.mp3", 5);
		Song songSix = new Song("Unknown", "Swing Cheese", "./songfiles/SwingCheese.mp3", 15);
		Song songSeven = new Song("Unknown", "TaDa", "./songfiles/tada.mp3", 1);
		Song songEight = new Song("Unknown", "TheCurtainRises", "./songfiles/TheCurtainRises.mp3", 28);
		Song songNine = new Song("Unknown", "Untameable Fire", "./songfiles/UntameableFire.mp3", 281);
		
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

	public boolean canPlay(Song song){
		LocalDate dateChecker = LocalDate.now();
		if(this.theDate.getDayOfYear() < dateChecker.getDayOfYear()){
			cardReader.getAccountCollection().resetPlays();
			theDate = dateChecker;//Change the programs date to the new date of today-PM
		}
		
		//Checks both the number of times the song has been chosen and the number of times
		//the user has chosen a song -PM
		if(song.getNumPlays() < 4 && this.userAccount.canPlaySong()){ 
			addToPlayList(song);
			this.userAccount.setNumberOfSongsPlayed();
			song.setNumPlays(song.getNumPlays() + 1);
			return true;
		}else{
			//Return a jpanel saying the limit has been reached-PM
			if(song.getNumPlays() >=4){
				textField.setText("The allotted song plays has been reached for this song. ");
			}
			if(!this.userAccount.canPlaySong()){
				textField.append("You have reached the allot limit for the amount of songs you can play");
			}
			
		}
		return false;
		
	}
	
	//Might refactor this Method because it is kind redundant -PM
	public void addToPlayList(Song song){
		chosenSongs.addSong(song);
	}
}

