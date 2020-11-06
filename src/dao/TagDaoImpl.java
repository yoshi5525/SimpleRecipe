package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Tag;

public class TagDaoImpl implements TagDao {

	private DataSource ds;

	public TagDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	private Tag mapToTag(ResultSet rs) throws SQLException {
		Tag tags = new Tag();
		tags.setId((Integer)rs.getObject("id"));
		tags.setName(rs.getString("name"));
		return tags;
	}

	@Override
	public List<Tag> findAll() throws Exception {
		List<Tag> tags = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM tags";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				tags.add(mapToTag(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return tags;
	}

	@Override
	public Tag findById(Integer id) throws Exception {
		Tag tag = null;

		try (Connection con = ds.getConnection()) {
		    String sql = "SELECT * FROM tags WHERE id = ?";
		    PreparedStatement stmt = con.prepareStatement(sql);
		    stmt.setObject(1, id, Types.INTEGER);
		    ResultSet rs = stmt.executeQuery();

		    if (rs.next()) {
		        tag = mapToTag(rs);
		    }
	    } catch (Exception e) {
	        throw e;
	    }
		return tag;
	}

}
