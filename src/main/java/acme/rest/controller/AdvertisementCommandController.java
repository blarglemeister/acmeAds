package acme.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import acme.core.domain.Advertisement;
import acme.core.service.AdvertisementService;

@Controller
@RequestMapping("/api/newspapers/{id}")
public class AdvertisementCommandController
{

	@Autowired
	private AdvertisementService adProvider;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Advertisement> createAdvertisement(
			@RequestBody Advertisement ad, @PathVariable("id") String paperId,
			UriComponentsBuilder builder)
	{

		ad.setPaperId(Long.parseLong(paperId));
		long adId = adProvider.createNewAd(ad);

		ad.setId(adId);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/api/newspapers/{id}")
				.buildAndExpand(paperId.toString()).toUri());

		return new ResponseEntity<Advertisement>(ad, headers,
				HttpStatus.CREATED);
	}
}
