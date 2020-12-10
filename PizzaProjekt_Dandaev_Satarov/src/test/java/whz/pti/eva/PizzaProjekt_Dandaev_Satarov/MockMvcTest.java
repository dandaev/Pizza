package whz.pti.eva.PizzaProjekt_Dandaev_Satarov;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public @interface MockMvcTest {
}
