package acme.core.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import acme.core.domain.Newspaper;

@Component
public class JdbcNewspaperDAO implements NewspaperDAO
{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public Long create(Newspaper newspaper)
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

		return holder.getKey().longValue();

	}

	@Override
	@Transactional
	public void update(Newspaper newspaper)
	{
		String newspaperUpdate = "UPDATE newspaper set name = ? WHERE id = ?";
		jdbcTemplate.update(newspaperUpdate, newspaper.getName(),
				newspaper.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public Newspaper getNewspaper(long id)
	{
		String sqlStatement = "SELECT id, name FROM newspaper WHERE id = ?";
		List<Newspaper> result = jdbcTemplate.query(
				new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(
							Connection connection) throws SQLException
					{
						PreparedStatement ps = connection
								.prepareStatement(sqlStatement);
						ps.setLong(1, id);
						return ps;
					}

				}, new NewspaperRowMapper());

		// Id is unique, should never have a result set larger than 1
		return result.get(0);
	}

	@Override
	public List<Newspaper> getNewspapers()
	{
		String sqlStatement = "SELECT id, name FROM newspaper";
		List<Newspaper> result;
		result = jdbcTemplate.query(sqlStatement, new NewspaperRowMapper());
		return result;
	}

	private class NewspaperRowMapper implements RowMapper<Newspaper>
	{
		@Override
		public Newspaper mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Newspaper newspaper = new Newspaper();
			newspaper.setId(rs.getLong(1));
			newspaper.setName(rs.getString(2));
			return newspaper;
		}
	}

}
