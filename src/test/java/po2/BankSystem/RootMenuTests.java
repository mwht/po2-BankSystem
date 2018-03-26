package po2.BankSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RootMenuTests {
	RootMenu sut;
	
	@BeforeEach
	void setUp() {
		sut = new RootMenu();
	}
	
	@Test
	void getOptionStringTest() {
		assertEquals("Exit", sut.getOptionString(0));
	}
	
	@Test
	void onOptionTest() {
		assertThrows(UnsupportedOperationException.class, () -> sut.onOption(1));
	}
	
	@AfterEach
	void tearDown() {
		sut = null;
	}
}
