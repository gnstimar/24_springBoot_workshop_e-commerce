package se.lexicon.__springBoot_workshop_e_commerce.example1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTests {

	//"Keress egy megfelelő Spring Bean-t az alkalmazásban, és automatikusan add át (fecskendezd be) ennek az osztálynak!"
	@Autowired
	PaymentServiceImpl paymentService;

	@Test
	void testDependencyInjection() {
		assertNotNull(paymentService);
		assertNotNull(paymentService.getPaymentRepository());

	}

}
