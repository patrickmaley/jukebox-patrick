package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
/**@author Brian Wehrle
 * @author Patrick Maley
 *
 *Class Description: AccountCollection class hardcodes the accounts available to the user.
 *It also can reset the plays for all accounts when the date changes.
 */
public class AccountCollection implements Serializable{
	
	private static final long serialVersionUID = -844659374983536693L;
	
	HashMap<Integer, JukeBoxAccount> accountCollection;
	private static AccountCollection uniqueAccountCollection;
	
	private AccountCollection(){
		accountCollection = new HashMap<>();
		createAccounts();
	}
	
	/**
	 * Singleton implementation. If uniqueAccountCollection is null,
	 * it constructs a new one. If not, it returns uniqueAccountCollection.
	 * 
	 * @return The unique instance of this object.
	 */
	public static AccountCollection makeAccountCollection() {
		if (uniqueAccountCollection == null)
			uniqueAccountCollection = new AccountCollection();
		return uniqueAccountCollection;
	}
	
	// hardcodes all the accounts given in the spec and puts them in the HashMap
	private void createAccounts() {
		JukeBoxAccount chrisAccount = new JukeBoxAccount("Chris");
		JukeBoxAccount devonAccount = new JukeBoxAccount("Devon");
		JukeBoxAccount riverAccount = new JukeBoxAccount("River");
		JukeBoxAccount ryanAccount = new JukeBoxAccount("Ryan");
		
		accountCollection.put(1, chrisAccount);
		accountCollection.put(22, devonAccount);
		accountCollection.put(333, riverAccount);
		accountCollection.put(4444, ryanAccount);
	}

	/**
	 * Resets the plays of all the accounts in the account collection. 
	 */
	public void resetPlays() {
		Iterator<Entry<Integer, JukeBoxAccount>> itr = this.accountCollection.entrySet().iterator();
		while(itr.hasNext()){
			itr.next().getValue().resetNumberOfSongsPlayed();
		}
	}
	
	/**
	 * Returns the HashMap of the accountCollection.
	 * 
	 * @return The HashMap of the accountCollection.
	 * 
	 */
	public HashMap<Integer, JukeBoxAccount> getAccountCollection(){
		return this.accountCollection;
	}
}
