package acme.core.service;

import java.util.List;

import acme.core.domain.Newspaper;

public interface NewspaperService
{

	public Long createNewspaper(Newspaper paper);

	public void updateNewspaper(Newspaper paper);

	public Newspaper requestNewspaper(long id);

	public List<Newspaper> requestAllNewspapers();

}
