package model;

public class CardReader {
	
	private JukeBoxAccount currentAccount;
	private AccountCollection accountCollection;
	
	public CardReader(){
		
	}
	
	public boolean readAccount(String accountName, int password){
		accountCollection = new AccountCollection();
		
		if (accountCollection.accountCollection.get(password).getName().compareTo(accountName) == 0)
			return true; // Set currentAccount to the matching account in accountCollection
		else
			return false;
	}
	
	public AccountCollection getAccountCollection(){
		return this.accountCollection;
	}
	
	public JukeBoxAccount getCurrentAccount(){
		return this.currentAccount;
	}
}
