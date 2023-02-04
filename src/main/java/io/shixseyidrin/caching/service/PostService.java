package io.shixseyidrin.caching.service;

import io.shixseyidrin.caching.request.PostReq;
import io.shixseyidrin.caching.response.Result;

public interface PostService {
    Result<?> createPost(PostReq request);
    Result<?> getAllPosts();
    Result<?> removePostById(Long id);
}
