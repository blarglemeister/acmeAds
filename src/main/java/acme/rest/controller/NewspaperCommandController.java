package acme.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import acme.core.domain.Newspaper;
import acme.core.service.AdvertisementService;
import acme.core.service.NewspaperService;

@Controller
@RequestMapping("/api/newspapers")
public class NewspaperCommandController
{

	@Autowired
	private NewspaperService paperProvider;

	@Autowired
	private AdvertisementService adProvider;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Newspaper> createNewspaper(
			@RequestBody Newspaper newspaper, UriComponentsBuilder builder)
	{

		Long paperId = paperProvider.createNewspaper(newspaper);
		newspaper.setId(paperId);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/api/newspapers/{id}")
				.buildAndExpand(paperId.toString()).toUri());

		return new ResponseEntity<Newspaper>(newspaper, headers, HttpStatus.CREATED);
	}

}
