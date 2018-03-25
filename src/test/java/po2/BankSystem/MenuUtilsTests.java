package po2.BankSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MenuUtilsTests {
	@Test
	void prettyHeaderTest() {
		assertEquals("+------+\n| ABCD |\n+------+\n", Menu.prettyHeader("Abcd"));
		assertEquals("+--+\n|  |\n+--+\n", Menu.prettyHeader(""));
	}
}
