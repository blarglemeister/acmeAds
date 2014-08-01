package acme.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Newspaper
{

	private Long id;
	private String name;
	private List<TextAd> ads;

	public Newspaper()
	{
		this("");
	}

	public Newspaper(String newspaperName)
	{
		name = newspaperName;
		ads = new ArrayList<>();
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void addAd(TextAd ad)
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

	public List<TextAd> getAds()
	{
		return ads;
	}

	public void setAds(List<TextAd> ads)
	{
		this.ads = ads;
	}
}
