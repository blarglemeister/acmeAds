package acme.core.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import acme.core.domain.Newspaper;
import acme.core.domain.TextAd;

@Component
public class JdbcNewspaperDAO implements NewspaperDAO
{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void store(Newspaper newspaper)
	{
		String newspaperInsert = "INSERT INTO newspaper(name) VALUES(?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException
			{
				PreparedStatement ps = connection.prepareStatement(
						newspaperInsert, new String[] { "id" });
				ps.setString(1, newspaper.getName());

				return ps;
			}

		}, holder);

		newspaper.setId(holder.getKey().longValue());

		if (newspaper.getAds() != null)
		{
			String adInsert = "INSERT INTO ad(title, body) VALUES(?, ?)";
			String linkInsert = "INSERT INTO paper_ad_links(paper_id, ad_id) VALUES(?, ?)";

			for (TextAd ad : newspaper.getAds())
			{
				jdbcTemplate.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(
							Connection connection) throws SQLException
					{
						PreparedStatement ps = connection.prepareStatement(
								adInsert, new String[] { "id" });
						ps.setString(1, ad.getTitle());
						ps.setString(2, ad.getAdText());

						return ps;
					}

				});

				ad.setId(holder.getKey().longValue());

				jdbcTemplate.update(linkInsert, newspaper.getId(), ad.getId());
			}
		}
	}

	@Override
	public Newspaper getNewspaper(int id)
	{
		String sqlStatement = "SELECT n.id, n.name, a.id, a.title, a.body "
				+ "FROM ad as a, newspaper as n, paper_ad_links as l "
				+ "WHERE ? = l.paper_id and l.ad_id = a.id";
		jdbcTemplate.query(sqlStatement, new NewspaperExtractor(), id);
		return null;
	}

	private class NewspaperExtractor implements
			ResultSetExtractor<List<Newspaper>>
	{

		@Override
		public List<Newspaper> extractData(ResultSet rs) throws SQLException,
				DataAccessException
		{
			Newspaper newspaper = null;
			Map<Long, Newspaper> paperMap = new HashMap<>();

			while (rs.next())
			{
				Long id = rs.getLong("n.id");
				newspaper = paperMap.get(id);

				if (newspaper == null)
				{
					newspaper = new Newspaper();
					newspaper.setId(id);
					newspaper.setName(rs.getString("n.name"));
					paperMap.put(id, newspaper);
				}

				TextAd ad = new TextAd();
				ad.setId(rs.getLong("a.id"));
				ad.setTitle(rs.getString("a.title"));
				ad.setAdText("a.body");
				newspaper.addAd(ad);
			}
			return new ArrayList<Newspaper>(paperMap.values());
		}

	}

}
