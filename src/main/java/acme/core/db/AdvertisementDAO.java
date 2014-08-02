package acme.core.db;

import java.util.List;

import acme.core.domain.Advertisement;

/**
 * Database access object for querying or updating the ad table.
 * 
 * @author Steve
 *
 */
public interface AdvertisementDAO
{

	/**
	 * Puts a new advertisement into the database.
	 * 
	 * @param ad
	 *            The ad to create in the database.
	 * @return The id of the newly created ad.
	 */
	public long createAd(Advertisement ad);

	/**
	 * Updates an advertisement in the database with the given values.
	 * 
	 * @param ad
	 *            The new ad data to update the database with.
	 */
	public void updateAd(Advertisement ad);

	/**
	 * Get all ads associated with a given newspaper id.
	 * 
	 * @param newspaperId
	 *            The newspaper to find ads for.
	 * @return The list of ads associated with the newspaper.
	 */
	public List<Advertisement> getAdsForNewspaper(long newspaperId);

}
