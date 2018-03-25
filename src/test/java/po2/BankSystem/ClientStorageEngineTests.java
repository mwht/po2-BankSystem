package po2.BankSystem;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientStorageEngineTests {
	ClientStorageEngine sut;
	
	@BeforeEach
	void setUp() {
		sut = new ClientStorageEngine("clients.csv");
	}
	
	@Test
	void commitTest() {
		assertTrue(sut.commit());
	}
	
	@Test
	void addClientTest() {
		Client c = new Client();
		try {
			c.setName("a"); c.setSurname("b"); c.setId(1337); c.setPesel(7301023456L); c.setAddress("ul. Szkolna 17, Białystok"); c.setBalance(6.66);
		} catch(Exception e) {
			fail("setting up test Client failed");
		}
		sut.addClient(c);
		assertTrue(sut.commit());
		
		c = new Client();
		try {
			c.setName("a"); c.setSurname("b"); c.setId(1337); c.setPesel(7301023456L); c.setAddress("ul. Szkolna 17\\, Białystok"); c.setBalance(6.66);
		} catch(Exception e) {
			fail("setting up test Client failed");
		}
		sut.addClient(c);
		assertTrue(sut.commit());
	}
	
	@AfterEach
	void tearDown() {
		sut = null;
	}
}
