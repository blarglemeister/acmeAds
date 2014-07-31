package acme.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Newspaper
{

	private String name;
	private List<Advertisement> ads;

	public Newspaper()
	{
		this("");
	}

	public Newspaper(String newspaperName)
	{
		name = newspaperName;
		ads = new ArrayList<>();
	}

	public void addAd(Advertisement ad)
	{
		if (getAds() == null)
		{
			setAds(new ArrayList<>());
		}
		getAds().add(ad);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Advertisement> getAds()
	{
		return ads;
	}

	public void setAds(List<Advertisement> ads)
	{
		this.ads = ads;
	}
}
