package model;
/**@author Brian Wehrle
 * @author Patrick Maley
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
	
	/**
	 * Singleton implementation. If uniqueCardReader is null,
	 * it constructs a new one. If not, it returns uniqueCardReader.
	 * 
	 * @param accountCollection
	 * 		the AccountCollection to read accounts from.
	 * @return The unique instance of this object.
	 */
	public static CardReader makeCardReader(AccountCollection accountCollection) {
		if (uniqueCardReader == null)
			uniqueCardReader = new CardReader(accountCollection);
		return uniqueCardReader;
	}
	
	/**
	 * Reads and finds the account with the specified name by matching the passwords.
	 * 
	 * @param accountName
	 * 		The name of the account to be found.
	 * 
	 * @param password
	 * 		The password of the account.
	 * @return
	 * 		True if the account was found, false if not.
	 */
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
	/**
	 * Returns the accountCollection to the JukeBox.
	 * 
	 * @return the accountCollection to the JukeBox.
	 */
	public AccountCollection getAccountCollection(){		
		return this.accountCollection;
	}
	
	/**
	 * Returns the account that was most recently read.
	 * Must call readAccount to intitialize currentAccount.
	 * 
	 * @return The account that was most recently read.
	 */
	// Returns the account that was most recently read.
	// Must call readAccount to intialize currentAccount
	public JukeBoxAccount getCurrentAccount(){	
		return this.currentAccount;
	}
	
	/**
	 * Signs out the current account by setting to null.
	 * 
	 */
	public void signOut() {
		this.currentAccount = null;
	}
}
