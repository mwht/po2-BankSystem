package po2.BankSystem;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
	
	@AfterEach
	void tearDown() {
		sut = null;
	}
}
