package acme.core.db;

import acme.core.domain.Newspaper;

public interface NewspaperDAO
{
	public void store(Newspaper newspaper);
	public Newspaper getNewspaper(int id);
}
