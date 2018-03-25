package po2.BankSystem;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuExceptionsTests {
	Menu sut;
	@BeforeEach
	void setUp() {
		sut = new Menu();
	}
	
	@Test
	void getMenuTitleExceptionTest() {
		assertThrows(UnsupportedOperationException.class, () -> sut.getMenuTitle());
	}
	
	@Test
	void getOptionsCountExceptionTest() {
		assertThrows(UnsupportedOperationException.class, () -> sut.getOptionsCount());
	}
	
	@Test
	void getOptionStringExceptionTest() {
		assertThrows(UnsupportedOperationException.class, () -> sut.getOptionString(666));
	}
	
	
	@Test
	void getOnOptionExceptionTest() {
		assertThrows(UnsupportedOperationException.class, () -> sut.onOption(666));
	}
	
	
	@AfterEach
	void tearDown() {
		sut = null;
	}
}
