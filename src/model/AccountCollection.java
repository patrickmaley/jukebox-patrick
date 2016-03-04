package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
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
 *Class Description: AccountCollection class hardcodes the accounts available to the user.
 *It also can reset the plays for all accounts when the date changes.
 */
public class AccountCollection {
	
	HashMap<Integer, JukeBoxAccount> accountCollection;
	
	public AccountCollection(){
		accountCollection = new HashMap<>();
		createAccounts();
	}
	
	// hardcodes all the accounts given in the spec and puts them in the HashMap
	private void createAccounts() {
		JukeBoxAccount chrisAccount = new JukeBoxAccount("Chris", 1);
		JukeBoxAccount devonAccount = new JukeBoxAccount("Devon", 2);
		JukeBoxAccount riverAccount = new JukeBoxAccount("River", 3);
		JukeBoxAccount ryanAccount = new JukeBoxAccount("Ryan", 4);
		
		//Not sure if the keys should just be arbitrary values and the
		//passwords should be a field in the accounts that we could encrypt
		accountCollection.put(1, chrisAccount);
		accountCollection.put(22, devonAccount);
		accountCollection.put(333, riverAccount);
		accountCollection.put(4444, ryanAccount);
	}

	// Resets the plays of all the accounts in the accountCollection. 
	public void resetPlays() {
		Iterator<Entry<Integer, JukeBoxAccount>> itr = this.accountCollection.entrySet().iterator();
		while(itr.hasNext()){
			itr.next().getValue().resetNumberOfSongsPlayed();
		}
	}
	
	// Returns the HashMap of accounts
	public HashMap<Integer, JukeBoxAccount> getAccountCollection(){
		return this.accountCollection;
	}
}
