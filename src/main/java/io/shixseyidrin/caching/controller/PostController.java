package io.shixseyidrin.caching.controller;

import io.shixseyidrin.caching.request.PostReq;
import io.shixseyidrin.caching.response.Result;
import io.shixseyidrin.caching.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/post")
public class PostController {

    private final PostService postService;


    @PostMapping(path = "/create")
    public Result<?> createPost(@RequestBody PostReq request) {
        return postService.createPost(request);
    }

    @GetMapping(path = "/all")
    public Result<?> allPosts() {
        return postService.getAllPosts();
    }
}
