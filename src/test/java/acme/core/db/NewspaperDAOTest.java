package acme.core.db;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import acme.core.domain.Newspaper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-application.xml" })
@Transactional
public class NewspaperDAOTest
{

	@Autowired
	private NewspaperDAO sut;

	@Test
	public void testCRUD()
	{
		Newspaper newspaper = new Newspaper();
		newspaper.setName("adHawk");

		long id = sut.create(newspaper);

		Newspaper dbPaper = sut.getNewspaper(id);
		newspaper.setId(id);

		Assert.assertTrue(newspaper.equals(dbPaper));

		newspaper.setName("Daily Bugle");

		sut.update(newspaper);

		dbPaper = sut.getNewspaper(id);

		Assert.assertTrue(newspaper.equals(dbPaper));

		dbPaper.setName("Slashdot");

		id = sut.create(dbPaper);
		dbPaper.setId(id);

		List<Newspaper> papers = sut.getNewspapers();

		Assert.assertTrue(papers.contains(newspaper));
		Assert.assertTrue(papers.contains(dbPaper));
	}
}
