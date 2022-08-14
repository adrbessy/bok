package com.perso.bok.web.dao;

import com.perso.bok.model.ContentBlock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentBlockDao extends CrudRepository<ContentBlock, Integer> {

    List<ContentBlock> findAll();

    ContentBlock findById(int id);

    List<ContentBlock> findByThemeId(final Integer themeId);

    void deleteByThemeId(int themeId);

}
