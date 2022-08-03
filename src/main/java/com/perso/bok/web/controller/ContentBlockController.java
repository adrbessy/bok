package com.perso.bok.web.controller;

import com.perso.bok.model.ContentBlock;
import com.perso.bok.model.Theme;
import com.perso.bok.web.dao.ContentBlockDao;
import com.perso.bok.web.dao.ThemeDao;
import com.perso.bok.web.exceptions.UnfoundContentBlockException;
import com.perso.bok.web.exceptions.UnfoundThemeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class ContentBlockController {

    @Autowired
    private ThemeDao themeDao;

    private static final Logger logger = LogManager.getLogger(ContentBlockController.class);

    private final ContentBlockDao contentBlockDao;

    public ContentBlockController(ContentBlockDao contentBlockDao) {
        this.contentBlockDao = contentBlockDao;
    }

    @CrossOrigin
    @GetMapping("/ContentBlocks")
    public List<ContentBlock> showAllContentBlocks() {
        return contentBlockDao.findAll();
    }

    @CrossOrigin
    @GetMapping("/ContentBlocks/{id}")
    public ContentBlock showContentBlock(@PathVariable int id) {
        ContentBlock contentBlock = contentBlockDao.findById(id);
        if(contentBlock==null) throw new UnfoundContentBlockException("The content block with the id " + id + " is unfound.");
        return contentBlock;
    }

    @CrossOrigin
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

    /**
     * Read - Get all the content blocks of one theme
     *
     * @param themeId The id of a theme
     * @return - A List of content blocks
     */
    @GetMapping("/ContentBlocks/theme/{themeId}")
    public List<ContentBlock> getContentBlocksOfOneTheme(@PathVariable int themeId) {
        logger.info("Get request with the endpoint 'notes'");
        Theme theme = themeDao.findById(themeId);
        if(theme==null) {
            throw new UnfoundThemeException("The theme with the id " + themeId + " is unfound.");
        }
        else{
            List<ContentBlock> contentBlockList = contentBlockDao.findByThemeId(themeId);
            logger.info("response following the GET on the endpoint 'notes'.");
            return contentBlockList;
        }
    }

}
