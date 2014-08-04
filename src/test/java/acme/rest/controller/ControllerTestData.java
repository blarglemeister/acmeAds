package acme.rest.controller;

import acme.core.domain.Newspaper;

public class ControllerTestData
{

	public static final String PAPER_NAME = "Ad Hawk";
	
	public static final String AD_TITLE = "StuffMart ad";
	public static final String AD_TEXT = "Buy everyting!";

	public static Newspaper createNewspaper(Long id)
	{
		Newspaper paper = new Newspaper();
		paper.setId(id);
		paper.setName(PAPER_NAME);
		return paper;
	}

	public static String standardNewspaperJSON(Long id)
	{
		return "{\"id\":" + id + ",\"name\":\"" + PAPER_NAME + "\",\"ads\":[]}}";
	}
	
	public static String standardAdJSON(Long id, Long paperId)
	{
		return  "{\"id\":" + id + ",\"title\":\"" + AD_TITLE + "\",\"adText\":\"" + AD_TEXT + "\",\"paperId\":" + paperId + "}";
	}
	
}
