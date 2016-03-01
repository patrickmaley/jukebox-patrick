package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class AccountCollection {//implements Iterable<JukeBoxAccount>{
	
//	public static class MyIterator implements Iterator<JukeBoxAccount> {
//		
//		private final AccountCollection myAccountCollection;
//		private int current;
//		
//		public MyIterator(AccountCollection accountCollection) { // pass AccountCollection or HashMap?
//			this.myAccountCollection = accountCollection;
//			current = 1;
//		}
//
//		@Override
//		public boolean hasNext() {
//			return current < 4;
//		}
//
//		@Override
//		public JukeBoxAccount next() {
//			if (!hasNext()) throw new NoSuchElementException();
//			return myAccountCollection.accountCollection.get(current++); // variable name problems?
//		}
//		
//	}
//	
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

	public void resetPlays() {
		Iterator<Entry<Integer, JukeBoxAccount>> itr = this.accountCollection.entrySet().iterator();
		while(itr.hasNext()){
			itr.next().getValue().resetNumberOfSongsPlayer();
		}
	}

//	@Override
//	public Iterator<JukeBoxAccount> iterator() {
//		return new MyIterator(this);
//	}
	
	public HashMap<Integer, JukeBoxAccount> getAccountCollection(){
		return this.accountCollection;
	}
}
