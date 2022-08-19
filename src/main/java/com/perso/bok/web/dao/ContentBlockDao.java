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

    //@Modifying
    //@Query("update contentBlock u set u.title = ?1, u.content = ?2 where u.id = ?3")
    //void setContentBlockById(String title, String content, int id);

}
