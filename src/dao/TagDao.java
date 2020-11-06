package dao;

import java.util.List;

import domain.Tag;

public interface TagDao {

	List<Tag> findAll() throws Exception;

	Tag findById(Integer id) throws Exception;

}
