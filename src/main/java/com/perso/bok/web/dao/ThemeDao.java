package com.perso.bok.web.dao;

import com.perso.bok.model.ContentBlock;
import com.perso.bok.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeDao extends JpaRepository<Theme, Integer> {

    List<Theme> findAll();

    Theme findById(int id);

    boolean existsById(Integer id);

}
