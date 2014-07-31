package acme.core.domain;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NewspaperTest
{

	private Newspaper sut;

	@Before
	public void setup()
	{
		sut = new Newspaper();
	}

	@Test
	public void testAddAd()
	{
		TextAd textAd = new TextAd();
		ImageAd imageAd = new ImageAd();
		
		sut.addAd(textAd);
		sut.addAd(imageAd);
		
		Assert.assertEquals(2, sut.getAds().size());
		Assert.assertTrue(sut.getAds().contains(textAd));
		Assert.assertTrue(sut.getAds().contains(imageAd));
	}
	
	@Test
	public void testSetters()
	{
		TextAd ad = new TextAd();
		String name = "Newspaper";

		sut.setAds(Arrays.asList(ad));
		sut.setName(name);

		Assert.assertEquals(1, sut.getAds().size());
		Assert.assertEquals(ad, sut.getAds().get(0));
		Assert.assertEquals(name, sut.getName());
	}
}
