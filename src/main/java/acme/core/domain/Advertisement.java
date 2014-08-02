package acme.core.domain;

import java.util.Objects;

public class Advertisement
{

	private Long id;
	private String title;
	private String adText;
	private Long paperId;

	@Override
	public boolean equals(Object o)
	{
		if (o == null || !(o instanceof Advertisement))
		{
			return false;
		}
		Advertisement ad = (Advertisement) o;
		return Objects.equals(id, ad.id) && Objects.equals(title, ad.title)
				&& Objects.equals(adText, ad.adText)
				&& Objects.equals(paperId, ad.paperId);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, title, adText, paperId);
	}

	/**
	 * Clone implemented for testing purposes.
	 */
	@Override
	public Advertisement clone()
	{
		Advertisement ad = new Advertisement();
		ad.setId(id);
		ad.setTitle(title);
		ad.setAdText(adText);
		ad.setPaperId(paperId);
		return ad;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getAdText()
	{
		return adText;
	}

	public void setAdText(String adText)
	{
		this.adText = adText;
	}

	public Long getPaperId()
	{
		return paperId;
	}

	public void setPaperId(Long paperId)
	{
		this.paperId = paperId;
	}

}
