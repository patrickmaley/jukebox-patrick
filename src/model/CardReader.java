package model;
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
 *Class Description: CardReader creates and Accountcollection object to determine
 *whether or not the login information in the JukeBox controller is in the hashmap.
 *If so it returns true and sets the currentAccount to true.
 */

public class CardReader implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7557889148923487609L;
	
	private static CardReader uniqueCardReader;
	private JukeBoxAccount currentAccount;
	private AccountCollection accountCollection;
	
	private CardReader(AccountCollection accountCollection){
		this.accountCollection = accountCollection;
	}
	
	public static CardReader makeCardReader(AccountCollection accountCollection) {
		if (uniqueCardReader == null)
			uniqueCardReader = new CardReader(accountCollection);
		return uniqueCardReader;
	}
	
	//Finds the account in the accountCollection by matching the passwords
	public boolean readAccount(String accountName, int password){				
		currentAccount = accountCollection.accountCollection.get(password);
		
		if (currentAccount == null) {	
			return false;
		}
		
		if (currentAccount.getName().compareTo(accountName) == 0) {
			return true;
			
		} else
			return false;
	}
	
	//Returns the accountcollection to the JukeBox
	public AccountCollection getAccountCollection(){		
		return this.accountCollection;
	}
	
	// Returns the account that was most recently read.
	// Must call readAccount to intialize currentAccount
	public JukeBoxAccount getCurrentAccount(){	
		return this.currentAccount;
	}
	
	public void signOut() {
		this.currentAccount = null;
	}
}
