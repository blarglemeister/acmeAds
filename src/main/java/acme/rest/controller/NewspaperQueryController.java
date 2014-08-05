package acme.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import acme.core.domain.Advertisement;
import acme.core.domain.Newspaper;
import acme.core.service.AdvertisementService;
import acme.core.service.NewspaperService;

@Controller
@RequestMapping("/api/newspapers")
public class NewspaperQueryController
{

	@Autowired
	NewspaperService newspaperService;

	@Autowired
	AdvertisementService adService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Newspaper>> requestAllNewspapers()
	{
		List<Newspaper> newspapers = newspaperService.requestAllNewspapers();

		return new ResponseEntity<List<Newspaper>>(newspapers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Newspaper> viewNewspaper(@PathVariable String id)
	{

		long numId = Long.parseLong(id);

		Newspaper paper = newspaperService.requestNewspaper(numId);

		if (paper == null)
		{
			return new ResponseEntity<Newspaper>(HttpStatus.NOT_FOUND);
		}

		List<Advertisement> ads = adService.requestNewspaperAds(numId);

		paper.setAds(ads);

		return new ResponseEntity<Newspaper>(paper, HttpStatus.OK);
	}
}
