package model;

public class CardReader {
	
	private JukeBoxAccount currentAccount;
	private AccountCollection accountCollection;
	
	public CardReader(){
		
	}
	
	public boolean readAccount(String accountName, int password){
		accountCollection = new AccountCollection();
		
		if (accountCollection.accountCollection.get(password).getName().compareTo(accountName) == 0)
			return true; // do something else?
		else
			return false;
	}
}
