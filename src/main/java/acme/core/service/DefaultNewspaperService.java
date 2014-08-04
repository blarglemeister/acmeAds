package acme.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import acme.core.db.NewspaperDAO;
import acme.core.domain.Newspaper;

@Component
public class DefaultNewspaperService implements NewspaperService
{

	@Autowired
	private NewspaperDAO paperProvider;

	@Override
	public Long createNewspaper(Newspaper paper)
	{
		return paperProvider.create(paper);
	}

	@Override
	public void updateNewspaper(Newspaper paper)
	{
		paperProvider.update(paper);

	}

	@Override
	public Newspaper requestNewspaper(long id)
	{
		return paperProvider.getNewspaper(id);
	}

	@Override
	public List<Newspaper> requestAllNewspapers()
	{
		return paperProvider.getNewspapers();
	}

}
