package acme.core.service;

import java.util.List;

import acme.core.domain.Advertisement;

public interface AdvertisementService
{

	public List<Advertisement> requestNewspaperAds(long newspaperId);

	public long createNewAd(Advertisement ad);
}
