package com.perso.bok.web.service.implementation;

import com.perso.bok.model.ContentBlock;
import com.perso.bok.web.dao.ContentBlockDao;
import com.perso.bok.web.service.ContentBlockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentBlockServiceImpl implements ContentBlockService {

    private final ContentBlockDao contentBlockDao;

    @Override
    public ContentBlock create(ContentBlock contentBlock) {
        log.info("Saving new content block: {}", contentBlock.getTitle());
        return  contentBlockDao.save(contentBlock);
    }

}
