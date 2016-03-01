package model;

public class CardReader {
	
	private JukeBoxAccount currentAccount;
	private AccountCollection accountCollection;
	
	public CardReader(){
		
	}
	
	public boolean readAccount(String accountName, int password){
		accountCollection = new AccountCollection();		
		
		currentAccount = accountCollection.accountCollection.get(password);
		if (currentAccount == null) {	
			return false;
		}
		
		if (currentAccount.getName().compareTo(accountName) == 0) {
			return true;
			
		} else
			return false;
	}
	
	public AccountCollection getAccountCollection(){
		
		return this.accountCollection;
	}
	
	// must call readAccount to intialize currentAccount
	public JukeBoxAccount getCurrentAccount(){
		if (currentAccount == null) 
			System.out.println("Account does not exist.");
		
		return this.currentAccount;
	}
}
