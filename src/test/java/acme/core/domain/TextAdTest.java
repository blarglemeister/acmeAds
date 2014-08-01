package acme.core.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextAdTest
{

	private TextAd sut;

	@Before
	public void setup()
	{
		sut = new TextAd();
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
