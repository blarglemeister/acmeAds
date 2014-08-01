package acme.core.domain;


public class TextAd
{

	private Long id;
	private String title;
	private String adText;

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

}
