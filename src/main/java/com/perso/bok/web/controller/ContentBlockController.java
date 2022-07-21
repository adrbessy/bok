package com.perso.bok.web.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.perso.bok.model.ContentBlock;
import com.perso.bok.web.dao.ContentBlockDao;
import com.perso.bok.web.exceptions.UnfoundContentBlockException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class ContentBlockController {

    private final ContentBlockDao contentBlockDao;

    public ContentBlockController(ContentBlockDao contentBlockDao) {
        this.contentBlockDao = contentBlockDao;
    }

    @GetMapping("/ContentBlocks")
    public List<ContentBlock> showAllContentBlocks() {
        List<ContentBlock> contentBlockList = contentBlockDao.findAll();
        return contentBlockList;
    }

    @GetMapping("/ContentBlocks/{id}")
    public ContentBlock showContentBlock(@PathVariable int id) {
        ContentBlock produit = contentBlockDao.findById(id);
        if(produit==null) throw new UnfoundContentBlockException("The content block with the id " + id + " is unfound.");
        return produit;
    }

    @PostMapping(value = "/ContentBlocks")
    public ResponseEntity<ContentBlock> addContentBlock(@RequestBody ContentBlock contentBlock) {
        ContentBlock contentBlockAdded = contentBlockDao.save(contentBlock);
        if (Objects.isNull(contentBlockAdded)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contentBlockAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping (value = "/ContentBlocks/{id}")
    public void deleteContentBlock(@PathVariable int id) {
        contentBlockDao.deleteById(id);
    }

    @PutMapping (value = "/ContentBlocks")
    public void updateContentBlock(@RequestBody ContentBlock contentBlock)
    {
        contentBlockDao.save(contentBlock);
    }

}
