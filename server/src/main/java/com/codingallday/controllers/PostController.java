package com.codingallday.controllers;

import com.codingallday.services.PostService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/posts")
public class PostController {

  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }
}
