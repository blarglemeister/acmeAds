package acme.core.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import acme.core.domain.Newspaper;
import acme.core.domain.TextAd;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-application.xml"})
public class NewspaperDAOTest
{
	
	@Autowired
	private NewspaperDAO sut;
	
	@Test
	public void testCRUD()
	{
		Newspaper newspaper = new Newspaper();
		newspaper.setName("adHawk");
		
		sut.store(newspaper);
		
		TextAd ad = new TextAd();
		newspaper.addAd(ad);
		
		sut.store(newspaper);
	}
}
