package acme.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Newspaper
{

	private Long id;
	private String name;
	private List<Advertisement> ads;

	@Override
	public boolean equals(Object o)
	{
		if (o == null || !(o instanceof Newspaper))
		{
			return false;
		}
		Newspaper n = (Newspaper) o;
		return Objects.equals(id, n.id) && Objects.equals(name, n.name)
				&& Objects.equals(ads, n.ads);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name, ads);
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
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
