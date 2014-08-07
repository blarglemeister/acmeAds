package acme.core.db;

import java.util.List;

import acme.core.domain.Newspaper;

/**
 * Database access object for querying or updating the newspapers table.
 * 
 * @author Steve
 *
 */
public interface NewspaperDAO
{
	/**
	 * Create a new newspaper entry in the database.
	 * 
	 * @param newspaper
	 *            Newspaper entry to add.
	 * @return ID of the created newspaper entry.
	 */
	public Long create(Newspaper newspaper);

	/**
	 * Make a modification to a newspaper entry in the database.
	 * 
	 * @param newspaper
	 *            Newspaper entry to update.
	 */
	public void update(Newspaper newspaper);

	/**
	 * Retrieve a newspaper from the database by ID.
	 * 
	 * @param id
	 *            Unique identifier for the newspaper to retrieve.
	 * @return Newspaper identified by the given ID.
	 */
	public Newspaper getNewspaper(Long id);

	/**
	 * Retrieve all newspaper entries stored in the database, without ads populated.
	 * 
	 * @return List of all newspaper entries.
	 */
	public List<Newspaper> getNewspapers();

}
