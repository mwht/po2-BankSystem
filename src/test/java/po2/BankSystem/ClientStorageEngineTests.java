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
		sut.addClient(new Client(1337,"Jan","Kowalski",73010234567L,"ul. Szkolna 17, Białystok",6.66));
		sut.addClient(new Client(1338,"Piotr","Jaworski",73010234567L,"ul. Młynarska 14/18 m. 16, 91-050 Łódź",6.66));
		sut.addClient(new Client(1339,"Tomasz","Duch",73010234567L,"ul. Szkolna 17, Białystok",6.66));
		sut.addClient(new Client(1340,"gagad,gapdgk","fasfasf",73010234567L,"ul. Szkolna 17, Białystok",6.66));
		sut.addClient(new Client(1341,"gadgdag","gadgki409i4903509w43tiopiopste[,yts0irjijrt0wopjntpoeropr",73010234567L,"ul. Szkolna 17, Białystok",6.66));
		assertTrue(sut.commit());
	}
	
	@Test
	void findClientTest() {
		
	}
	
	@AfterEach
	void tearDown() {
		sut = null;
	}
}
