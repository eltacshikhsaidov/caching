package io.shixseyidrin.caching.util;

import io.shixseyidrin.caching.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class PostUtil {
    public static boolean isPresent(Long postId, PostRepository postRepository) {
        return postRepository.findById(postId).isPresent();
    }
}
