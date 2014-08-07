package acme.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import acme.core.db.AdvertisementDAO;
import acme.core.domain.Advertisement;

@Component
public class DefaultAdvertisementService implements AdvertisementService
{

	@Autowired
	AdvertisementDAO adProvider;

	@Override
	public List<Advertisement> requestNewspaperAds(long newspaperId)
	{
		return adProvider.getAdsForNewspaper(newspaperId);
	}

	@Override
	public long createNewAd(Advertisement ad)
	{
		return adProvider.createAd(ad);
	}

}
