package com.perso.bok.web.dao;

import com.perso.bok.model.ContentBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentBlockDao extends JpaRepository<ContentBlock, Integer> {

    List<ContentBlock> findAll();

    ContentBlock findById(int id);

}
