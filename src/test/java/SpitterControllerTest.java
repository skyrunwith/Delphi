import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fzd.web.controller.CategoryController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class SpitterControllerTest {
	/*@Test
	public void shouldProcessRegistration() throws Exception{
		*//*SpitterRepository spitterRepository = mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer", "skyrun@qq.com");
		Spitter saved = new Spitter(24L, "jbauer", "24hours", "jack", "Bauer", "skyrun@qq.com");
		when(spitterRepository.save(unsaved)).thenReturn(saved);*//*
		CategoryController controller = new CategoryController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(post("/category/add")
				.param("name", "jbauer")
				.param("comment", "24hours"));
//		verify(spitterRepository, atLeastOnce()).save(unsaved);
	}*/
}
