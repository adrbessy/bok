package com.perso.bok;

import com.perso.bok.model.ContentBlock;
import com.perso.bok.model.Theme;
import com.perso.bok.web.dao.ContentBlockDao;
import com.perso.bok.web.dao.ThemeDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class BokApplication {

	public static void main(String[] args) {
		SpringApplication.run(BokApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ContentBlockDao contentBlockDao, ThemeDao themeDao) {
		return args -> {
			themeDao.save(new Theme(1,"outils"));
			themeDao.save(new Theme(2,"genre homo"));
			contentBlockDao.save(new ContentBlock(1, 1, "Premiers outils en bois" , "Les plus anciens outils en bois, de conservation fragile à l'instar des brindilles des chimpanzés, sont des sortes de javelots attribuées à Erectus et retrouvées à Schoningen, en Allemagne.", LocalDate.now().atStartOfDay()));
			contentBlockDao.save(new ContentBlock(2, 2, "Homo luzonensis" , "Dans la grotte de Callao, sur l''île Luçon dans les Philippines, quelques rares fragments d''une nouvelle espèce, \"naine\" également, ont été découverts à partir de 2007 par une équipe franco-philippine.", LocalDate.now().atStartOfDay()));
			contentBlockDao.save(new ContentBlock(3, 2, "Premiers peuplements de l''Amérique" , "Il y a environ 20.000 ans, on a traversé le détroit de Béring pour peupler pour la première fois les Amériques.", LocalDate.now().atStartOfDay()));
		};
	}
}
