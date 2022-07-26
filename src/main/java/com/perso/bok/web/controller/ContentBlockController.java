package com.perso.bok.web.controller;

import com.perso.bok.model.ContentBlock;
import com.perso.bok.model.Theme;
import com.perso.bok.web.dao.ContentBlockDao;
import com.perso.bok.web.dao.ThemeDao;
import com.perso.bok.web.exceptions.UnfoundContentBlockException;
import com.perso.bok.web.exceptions.UnfoundThemeException;
import com.perso.bok.web.service.ContentBlockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ContentBlockController {

    private final ThemeDao themeDao;

    private final ContentBlockDao contentBlockDao;

    private final ContentBlockService contentBlockService;

    @CrossOrigin
    @DeleteMapping (value = "/ContentBlocks")
    public void deleteAllContentBlocks() {
        log.info("Delete request with the endpoint '/ContentBlocks'");
        contentBlockDao.deleteAll();
        log.info("request response with the endpoint '/ContentBlocks'");
    }

    @CrossOrigin
    @GetMapping("/ContentBlocks")
    public List<ContentBlock> showAllContentBlocks() {
        log.info("Get request with the endpoint '/ContentBlocks'");
        return contentBlockDao.findAll();
    }

    @CrossOrigin
    @GetMapping("/ContentBlocks/{id}")
    public ContentBlock showContentBlock(@PathVariable int id) {
        log.info("Get request with the endpoint '/ContentBlocks/{id}'");
        ContentBlock contentBlock = contentBlockDao.findById(id);
        if(contentBlock==null) throw new UnfoundContentBlockException("The content block with the id " + id + " is unfound.");
        return contentBlock;
    }

    @CrossOrigin
    @PostMapping("/ContentBlocks")
    public ResponseEntity<ContentBlock> addContentBlock(@RequestBody ContentBlock contentBlock) {
        log.info("Post request with the endpoint '/ContentBlocks'");
        ContentBlock addedContentBlock = contentBlockService.create(contentBlock);
        if (Objects.isNull(addedContentBlock)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedContentBlock.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @CrossOrigin
    @DeleteMapping ("/ContentBlocks/{id}")
    public ContentBlock deleteContentBlock(@PathVariable int id) {
        log.info("Delete request with the endpoint '/ContentBlocks/{id}'");
        ContentBlock deletedContentBlock = contentBlockDao.findById(id);
        contentBlockDao.deleteById(id);
        return deletedContentBlock;
    }

    @CrossOrigin
    @PutMapping ("/ContentBlocks")
    public void updateContentBlock(@RequestBody ContentBlock contentBlock)
    {
        log.info("Update request with the endpoint '/ContentBlocks'");
        //contentBlockDao.setContentBlockById(contentBlock.getTitle(),contentBlock.getContent(),contentBlock.getId());
        ContentBlock contentBlockFound = contentBlockDao.findById(contentBlock.getId());
        // crush the variables of the object found
        contentBlockFound.setTitle(contentBlock.getTitle());
        contentBlockFound.setContent(contentBlock.getContent());
        contentBlockDao.save(contentBlockFound);
    }

    /**
     * Read - Get all the content blocks of one theme
     *
     * @param themeId The id of a theme
     * @return - A List of content blocks
     */
    @CrossOrigin
    @GetMapping("/ContentBlocks/theme/{themeId}")
    public List<ContentBlock> getContentBlocksOfOneTheme(@PathVariable int themeId) {
        log.info("Get request with the endpoint '/ContentBlocks/theme/{themeId}'");
        Theme theme = themeDao.findById(themeId);
        if(theme==null) {
            throw new UnfoundThemeException("The theme with the id " + themeId + " is unfound.");
        }
        else{
            List<ContentBlock> contentBlockList = contentBlockDao.findByThemeId(themeId);
            log.info("response following the GET on the endpoint '/ContentBlocks/theme/{themeId}'.");
            return contentBlockList;
        }
    }

    /**
     * Delete - Delete the content blocks by theme id
     *
     * @param themeId The theme id
     */
    @CrossOrigin
    @DeleteMapping("/ContentBlocks/deleteByThemeId")
    public void deleteByThemeId(@PathVariable int themeId) {
        log.info("Delete request with the endpoint '/ContentBlocks/deleteByThemeId'");
        contentBlockDao.deleteByThemeId(themeId);
        log.info("request response with the endpoint '/ContentBlocks/deleteByThemeId'");
    }

    /**
     * Save - Save all the content blocks of one theme
     *
     * @param contentBlockList The list to save
     * @return - A List of content blocks
     */
    @CrossOrigin
    @PostMapping("/ContentBlocks/saveContentBlocks")
    public List<ContentBlock> saveContentBlocks(@RequestBody List<ContentBlock> contentBlockList) {
        log.info("Post request with the endpoint '/ContentBlocks/saveContentBlocks'");
        List<ContentBlock> contentBlocks = contentBlockDao.findByThemeId(contentBlockList.get(0).getThemeId());
        System.out.println("contentBlocks : " + contentBlocks);
        contentBlockDao.saveAll(contentBlockList);
        log.info("request response with the endpoint '/ContentBlocks/saveContentBlocks'");
        return contentBlockList;
    }

}
