package com.perso.bok.web.controller;

import com.perso.bok.model.ContentBlock;
import com.perso.bok.model.Theme;
import com.perso.bok.web.dao.ContentBlockDao;
import com.perso.bok.web.dao.ThemeDao;
import com.perso.bok.web.exceptions.UnfoundContentBlockException;
import com.perso.bok.web.exceptions.UnfoundThemeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class ThemeController {

    private final ThemeDao themeDao;

    public ThemeController(ThemeDao themeDao) {
        this.themeDao = themeDao;
    }

    @GetMapping("/Themes")
    public List<Theme> showAllThemes() {
        List<Theme> themeList = themeDao.findAll();
        return themeList;
    }

    @GetMapping("/Themes/{id}")
    public Theme showTheme(@PathVariable int id) {
        Theme theme = themeDao.findById(id);
        if(theme==null) throw new UnfoundThemeException("The theme with the id " + id + " is unfound.");
        return theme;
    }

    @PostMapping(value = "/Themes")
    public ResponseEntity<Theme> addTheme(@RequestBody Theme theme) {
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

    @DeleteMapping (value = "/Themes/{id}")
    public void deleteTheme(@PathVariable int id) {
        themeDao.deleteById(id);
    }

    @PutMapping (value = "/Themes")
    public void updateTheme(@RequestBody Theme Theme)
    {
        themeDao.save(Theme);
    }


}
