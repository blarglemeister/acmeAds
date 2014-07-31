package acme.core.domain;

import java.util.List;

public abstract class Advertisement
{

	private long id;
	private String title;
	private List<Newspaper> newspapers;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
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

	public List<Newspaper> getNewspapers()
	{
		return newspapers;
	}

	public void setNewspapers(List<Newspaper> newspapers)
	{
		this.newspapers = newspapers;
	}
}
