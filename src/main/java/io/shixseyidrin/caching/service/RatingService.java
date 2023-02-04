package io.shixseyidrin.caching.service;

import io.shixseyidrin.caching.response.Result;

public interface RatingService {
    Result<?> like(Long postId);
    Result<?> dislike(Long postId);
    Result<?> getData(Long postId);
}
