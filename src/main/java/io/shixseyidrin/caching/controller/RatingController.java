package io.shixseyidrin.caching.controller;

import io.shixseyidrin.caching.response.Result;
import io.shixseyidrin.caching.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rating")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping(path = "/like")
    public Result<?> like(@RequestParam(name = "postId") Long postId) {
        return ratingService.like(postId);
    }

    @PostMapping(path = "/dislike")
    public Result<?> dislike(@RequestParam(name = "postId") Long postId) {
        return ratingService.dislike(postId);
    }

    @GetMapping(path = "/getData")
    public Result<?> getData(@RequestParam(name = "postId") Long postId) {
        return ratingService.getData(postId);
    }
}
