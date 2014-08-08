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

import acme.core.domain.Advertisement;
import acme.core.service.AdvertisementService;

public class AdvertisementCommandControllerTest
{

	private MockMvc mockMvc;

	@InjectMocks
	private AdvertisementCommandController sut;

	@Mock
	private AdvertisementService adService;

	private Long testId = 42L;

	private Long paperId = 12L;

	@Before
	public void setup()
	{
		sut = new AdvertisementCommandController();

		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders
				.standaloneSetup(sut)
				.setMessageConverters(new MappingJackson2HttpMessageConverter())
				.build();

		when(adService.createNewAd(any(Advertisement.class)))
				.thenReturn(testId);
	}

	@Test
	public void testCreateAdUsesHttpCreated() throws Exception
	{

		this.mockMvc
				.perform(
						post("/api/newspapers/" + paperId + "/ads")
								.content(
										ControllerTestData.standardAdJSON(
												testId, paperId))
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void testCreateAdRendersAsJson() throws Exception
	{

		this.mockMvc
				.perform(
						post("/api/newspapers/" + paperId + "/ads")
								.content(
										ControllerTestData
												.standardAdJSON(testId, paperId))
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
				.andExpect(
						jsonPath("$.title").value(ControllerTestData.AD_TITLE))
				.andExpect(jsonPath("$.id").value(testId.intValue()))
				.andExpect(jsonPath("$.paperId").value(paperId.intValue()))
				.andExpect(
						jsonPath("$.adText").value(ControllerTestData.AD_TEXT));

	}

	@Test
	public void testCreateAdPassesLocationHeader() throws Exception
	{

		this.mockMvc.perform(
				post("/api/newspapers/" + paperId + "/ads")
						.content(
								ControllerTestData
										.standardAdJSON(testId, paperId))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)).andExpect(
				header().string("Location",
						"http://localhost/api/newspapers/" + paperId));
	}
}
