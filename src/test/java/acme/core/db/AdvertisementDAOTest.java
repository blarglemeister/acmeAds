package acme.core.db;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import acme.core.domain.Advertisement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-application.xml" })
@Transactional
public class AdvertisementDAOTest
{
	@Autowired
	private AdvertisementDAO sut;

	@Test
	public void testCRUD()
	{
		long newsId1 = 1;
		long newsId2 = 2;

		Advertisement ad1 = createAd("ad1", "Please buy something", newsId1);
		Advertisement ad2 = createAd("ad2", "Pretty please", newsId1);
		Advertisement ad3 = createAd("ad3", "I could use the business", newsId2);

		ad1.setId(sut.createAd(ad1));
		ad2.setId(sut.createAd(ad2));
		ad3.setId(sut.createAd(ad3));

		List<Advertisement> results = sut.getAdsForNewspaper(newsId1);
		Assert.assertEquals(2, results.size());
		Assert.assertTrue(results.contains(ad1));
		Assert.assertTrue(results.contains(ad2));
		
		results = sut.getAdsForNewspaper(newsId2);
		Assert.assertEquals(1, results.size());
		Assert.assertTrue(results.contains(ad3));
		
		ad3.setAdText("Nevermind, I give up");
		sut.updateAd(ad3);
		
		results = sut.getAdsForNewspaper(newsId2);
		Assert.assertEquals(1, results.size());
		Assert.assertTrue(results.contains(ad3));

	}

	private Advertisement createAd(String title, String text, long paperId)
	{
		Advertisement ad = new Advertisement();
		ad.setTitle(title);
		ad.setAdText(text);
		ad.setPaperId(paperId);

		return ad;
	}
}
