package model;

import java.util.HashMap;

public class AccountCollection {
	HashMap<Integer, JukeBoxAccount> accountCollection;
	
	public AccountCollection(){
		accountCollection = new HashMap<>();
		createAccounts();
	}
	
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

	public void resetPlays(){
		
	}
}
