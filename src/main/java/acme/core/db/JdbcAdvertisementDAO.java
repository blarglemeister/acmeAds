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

import acme.core.domain.Advertisement;

@Component
public class JdbcAdvertisementDAO implements AdvertisementDAO
{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public long createAd(Advertisement ad)
	{
		String insertStatement = "INSERT INTO ad (title, body, newspaper_id) values (?, ?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException
			{
				PreparedStatement ps = connection.prepareStatement(
						insertStatement, new String[] { "id" });
				ps.setString(1, ad.getTitle());
				ps.setString(2, ad.getAdText());
				ps.setLong(3, ad.getPaperId());

				return ps;
			}

		}, holder);

		return holder.getKey().intValue();
	}

	@Override
	@Transactional
	public void updateAd(Advertisement ad)
	{
		String adUpdate = "UPDATE ad SET title = ?, body = ? WHERE id = ?";
		jdbcTemplate
				.update(adUpdate, ad.getTitle(), ad.getAdText(), ad.getId());

	}

	@Transactional
	public void linkAd(long adId, long paperId)
	{
		String linkAd = "UPDATE ad SET newspaper_id = ? WHERE id = ?";
		jdbcTemplate.update(linkAd, paperId, adId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Advertisement> getAdsForNewspaper(long newspaperId)
	{
		String getAds = "SELECT id, title, body, newspaper_id FROM ad WHERE newspaper_id = ?";
		return jdbcTemplate.query(getAds, new RowMapper<Advertisement>(){

			@Override
			public Advertisement mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				Advertisement ad = new Advertisement();
				ad.setId(rs.getLong(1));
				ad.setTitle(rs.getString(2));
				ad.setAdText(rs.getString(3));
				ad.setPaperId(rs.getLong(4));
				return ad;
			}
			
		}, newspaperId);
	}
}
