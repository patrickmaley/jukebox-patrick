package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.CardReader;

public class JukeBoxTester {

	@Test
	public void CardReaderTest() {
		CardReader cardReader = new CardReader();
		
		assertEquals(true, cardReader.readAccount("Chris", 1));
		assertEquals(false, cardReader.readAccount("Chris", 22));
		
		cardReader.readAccount("Chris", 1234); // not an existing password
		assertEquals(null, cardReader.getCurrentAccount());
	}

}
