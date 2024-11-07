package com.relief.application.controllers;

import com.relief.domain.services.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{id}/like")
    public ResponseEntity<?> like(@PathVariable("id") String id){
        this.postService.like(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
