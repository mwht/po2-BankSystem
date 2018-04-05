package po2.BankSystem;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientTests {
	Client sut;
	@BeforeEach
	void setUp() {
		sut = new Client();
	}
	
	@Test
	void idTest() {
		sut.setId(1337);
		assertEquals(1337, sut.getId());
	}
	
	@Test
	void nameTest() {
		sut.setName("Adam");
		assertEquals("Adam", sut.getName());
	}
	
	@Test 
	void surnameTest() {
		sut.setSurname("Kowalski");
		assertEquals("Kowalski", sut.getSurname());
	}
	
	@Test
	void peselTest() {
		sut.setPesel("73010431337");
		assertEquals("73010431337", sut.getPesel());
	}
	
	@Test
	void addressTest() {
		sut.setAddress("ul. Stefanowskiego 18/22");
		assertEquals("ul. Stefanowskiego 18/22", sut.getAddress());
	}
	
	@Test
	void balanceTest() {
		try {
			sut.setBalance(13.37);
		} catch (InvalidBalanceException e) {
			fail();
		}
		assertEquals(13.37, sut.getBalance());
		
		assertThrows(InvalidBalanceException.class, () -> sut.setBalance(-666));
		
		try {
			sut.addBalance(2);
		} catch (InvalidBalanceException e) {
			fail();
		}
		assertEquals(15.37, sut.getBalance());
		
		assertThrows(InvalidBalanceException.class, () -> sut.addBalance(-10));
		try {
			sut.subBalance(10);
		} catch (InvalidBalanceException e) {
			fail();
		}
		
		assertThrows(InvalidBalanceException.class, () -> sut.subBalance(-10));
		assertThrows(InvalidBalanceException.class, () -> sut.subBalance(7));
	}
	
	@AfterEach
	void tearDown() {
		sut = null;
	}
}
