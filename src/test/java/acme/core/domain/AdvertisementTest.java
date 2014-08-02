package acme.core.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdvertisementTest
{

	private Advertisement sut;

	@Before
	public void setup()
	{
		sut = new Advertisement();
	}

	@Test
	public void testEquals()
	{
		long id = 1;
		String title = "ad";
		String text = "hi";
		long paperId = 4;

		sut.setId(id);
		sut.setTitle(title);
		sut.setAdText(text);
		sut.setPaperId(paperId);

		Advertisement ad = sut.clone();
		Assert.assertTrue(sut.equals(ad));

		ad.setId(2L);

		Assert.assertFalse(sut.equals(ad));

		ad.setId(id);
		ad.setTitle("something");

		Assert.assertFalse(sut.equals(ad));

		ad.setTitle(title);
		ad.setAdText("buybubybuy");

		Assert.assertFalse(sut.equals(ad));

		ad.setAdText(text);
		ad.setPaperId(2L);

		Assert.assertFalse(sut.equals(ad));

	}

	@Test
	public void testSetters()
	{
		Long id = 1L;
		String title = "anAd";
		String body = "buyStuff";
		sut.setId(id);
		sut.setTitle(title);
		sut.setAdText(body);

		Assert.assertEquals(id, sut.getId());
		Assert.assertEquals(title, sut.getTitle());
		Assert.assertEquals(body, sut.getAdText());
	}
}
