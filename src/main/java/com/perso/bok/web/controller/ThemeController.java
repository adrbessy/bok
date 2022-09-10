package com.perso.bok.web.controller;

import com.perso.bok.model.Theme;
import com.perso.bok.web.dao.ThemeDao;
import com.perso.bok.web.exceptions.UnfoundThemeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeDao themeDao;

    @CrossOrigin
    @GetMapping("/Themes")
    public List<Theme> getAllThemes() {
        log.info("Get request with the endpoint '/Themes'");
        return themeDao.findAll();
    }

    @CrossOrigin
    @GetMapping("/Themes/{id}")
    public Theme showTheme(@PathVariable int id) {
        log.info("Get request with the endpoint '/Themes/{id}'");
        Theme theme = themeDao.findById(id);
        if(theme==null) throw new UnfoundThemeException("The theme with the id " + id + " is unfound.");
        return theme;
    }

    @CrossOrigin
    @PostMapping(value = "/Themes")
    public ResponseEntity<Theme> addTheme(@RequestBody @Valid Theme theme) {
        log.info("Post request with the endpoint '/Themes'");
        Theme themeAdded = themeDao.save(theme);
        if (Objects.isNull(themeAdded)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(themeAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @CrossOrigin
    @DeleteMapping (value = "/Themes")
    public void deleteAllThemes() {
        log.info("Delete request with the endpoint '/Themes'");
        themeDao.deleteAll();
        log.info("request response with the endpoint '/Themes'");
    }

    @CrossOrigin
    @DeleteMapping (value = "/Themes/{id}")
    public void deleteTheme(@PathVariable int id) {
        themeDao.deleteById(id);
    }

    /*@PutMapping (value = "/Themes")
    public void updateTheme(@RequestBody Theme Theme)
    {
        themeDao.save(Theme);
    }*/

    @CrossOrigin
    @PutMapping ("/Themes")
    public Theme updateTheme(@RequestBody Theme theme) {
        log.info("UpdateThemeName request with the endpoint '/Themes'");
        Theme themeFound = themeDao.findById(theme.getId());
        themeFound.setName(theme.getName());
        themeFound.setShowSubthemes(theme.isShowSubthemes());
        themeDao.save(themeFound);
        return theme;
    }

    @CrossOrigin
    @PutMapping (value = "/ThemeList")
    public List<Theme> updateThemeList(@RequestBody List<Theme> themeList) {
        log.info("PUT request with the endpoint '/ThemeList'");
        themeDao.saveAll(themeList);
        log.info("request response with the endpoint '/ThemeList'");
        return themeList;
    }

    /**
     * Delete - Delete the themes by id
     *
     * @param id The theme id
     */
    @CrossOrigin
    @DeleteMapping("/Themes/deleteById/{id}")
    public void deleteById(@PathVariable int id) {
        log.info("Delete request with the endpoint '/Themes/deleteById'");
        themeDao.deleteById(id);
        log.info("request response with the endpoint '/Themes/deleteId'");
    }

    /**
     * Save - Save all the themes
     *
     * @param themeList The list to save
     * @return - A List of themes
     */
    @CrossOrigin
    @PostMapping("/Themes/saveThemes")
    public List<Theme> saveThemes(@RequestBody List<Theme> themeList) {
        log.info("Post request with the endpoint '/Themes/saveThemes'");
        themeDao.saveAll(themeList);
        log.info("request response with the endpoint '/Themes/saveThemes'");
        return themeList;
    }

}
