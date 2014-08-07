package acme.rest.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import acme.core.domain.Newspaper;
import acme.core.service.AdvertisementService;
import acme.core.service.NewspaperService;

/*public class NewspaperCommandsControllerTest
{

	private MockMvc mockMvc;

	@InjectMocks
	private NewspaperCommandController sut;

	@Mock
	private NewspaperService paperService;

	@Mock
	private AdvertisementService adService;

	private Long testId = 12L;

	@Before
	public void setup()
	{
		sut = new NewspaperCommandController();

		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders
				.standaloneSetup(sut)
				.setMessageConverters(new MappingJackson2HttpMessageConverter())
				.build();

		when(paperService.createNewspaper(any(Newspaper.class))).thenReturn(
				testId);
	}

	@Test
	public void testCreateNewspaperUsesHttpCreated() throws Exception
	{

		this.mockMvc
				.perform(
						post("/api/newspapers")
								.content(
										ControllerTestData
												.standardNewspaperJSON(testId))
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void testCreateNewspaperRendersAsJson() throws Exception
	{

		this.mockMvc
				.perform(
						post("/api/newspapers")
								.content(
										ControllerTestData
												.standardNewspaperJSON(testId))
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
				.andExpect(
						jsonPath("$.name").value(ControllerTestData.PAPER_NAME))
				.andExpect(jsonPath("$.id").value(testId.intValue()));

	}

	@Test
	public void testCreateNewspaperPassesLocationHeader() throws Exception
	{

		this.mockMvc.perform(
				post("/api/newspapers")
						.content(
								ControllerTestData
										.standardNewspaperJSON(testId))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)).andExpect(
				header().string("Location",
						"http://localhost/api/newspapers/" + testId));
	}

}*/
