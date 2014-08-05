package acme.rest.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import acme.core.service.AdvertisementService;
import acme.core.service.NewspaperService;

public class NewspaperQueryControllerTest
{

	private MockMvc mockMvc;

	@InjectMocks
	private NewspaperQueryController sut;

	@Mock
	private NewspaperService paperService;

	@Mock
	private AdvertisementService adService;

	private Long testId = 12L;

	@Before
	public void setup()
	{
		sut = new NewspaperQueryController();

		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders
				.standaloneSetup(sut)
				.setMessageConverters(new MappingJackson2HttpMessageConverter())
				.build();

	}

	@Test
	public void thatRequestNewspaperUsesHttpNotFound() throws Exception
	{

		Mockito.when(paperService.requestNewspaper(testId)).thenReturn(null);

		this.mockMvc
				.perform(
						get("/api/newspapers/{id}", testId.toString()).accept(
								MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	public void thatRequestNewspaperUsesHttpOK() throws Exception
	{

		Mockito.when(paperService.requestNewspaper(testId)).thenReturn(
				ControllerTestData.createNewspaper(testId));
		Mockito.when(adService.requestNewspaperAds(testId)).thenReturn(null);

		this.mockMvc.perform(
				get("/api/newspapers/{id}", testId.toString()).accept(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void thatRequestNewspaperRendersCorrectly() throws Exception
	{

		Mockito.when(paperService.requestNewspaper(testId)).thenReturn(
				ControllerTestData.createNewspaper(testId));

		this.mockMvc
				.perform(
						get("/api/newspapers/{id}", testId.toString()).accept(
								MediaType.APPLICATION_JSON))
				.andExpect(
						jsonPath("$.name").value(ControllerTestData.PAPER_NAME))
				.andExpect(jsonPath("$.id").value(testId.intValue()));
	}

	@Test
	public void thatRequestAllNewspapersUsesHttpOK() throws Exception
	{

		Mockito.when(paperService.requestNewspaper(testId)).thenReturn(
				ControllerTestData.createNewspaper(testId));

		this.mockMvc.perform(
				get("/api/newspapers", testId.toString()).accept(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void thatRequestAllNewspapersRendersCorrectly() throws Exception
	{

		Mockito.when(paperService.requestAllNewspapers()).thenReturn(
				Arrays.asList(ControllerTestData.createNewspaper(testId)));

		this.mockMvc
				.perform(
						get("/api/newspapers").accept(
								MediaType.APPLICATION_JSON))
				.andExpect(
						jsonPath("$[0].name").value(
								ControllerTestData.PAPER_NAME))
				.andExpect(jsonPath("$[0].id").value(testId.intValue()))
				.andExpect(jsonPath("$", hasSize(1)));
	}

}
