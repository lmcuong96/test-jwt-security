package com.example.demo.service.implement;

import com.example.demo.dtos.ContentDTO;
import com.example.demo.entities.Content;
import com.example.demo.repository.ContentRepository;
import com.example.demo.service.ContentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public List<ContentDTO> findAll() {
        var contentList = contentRepository.findAll();

        return contentList.stream().map(content -> {
            var contentDTO = new ContentDTO();

            contentDTO.setId(content.getId());
            contentDTO.setContent(content.getContent());

            return contentDTO;
        }).toList();
    }

    @Override
    public boolean create(ContentDTO contentDTO) {
       if (contentDTO == null) {
           return false;
       }

       var content = new Content();

       content.setId(contentDTO.getId());
       content.setContent(contentDTO.getContent());

       contentRepository.save(content);

       return true;
    }
}
