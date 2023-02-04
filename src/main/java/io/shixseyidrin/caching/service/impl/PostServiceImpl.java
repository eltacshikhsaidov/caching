package io.shixseyidrin.caching.service.impl;

import io.shixseyidrin.caching.model.Post;
import io.shixseyidrin.caching.repository.PostRepository;
import io.shixseyidrin.caching.request.PostReq;
import io.shixseyidrin.caching.response.Result;
import io.shixseyidrin.caching.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.shixseyidrin.caching.constants.ExceptionCode.INVALID_REQUEST_DATA;
import static io.shixseyidrin.caching.constants.ExceptionCode.NO_DATA_FOUND;
import static java.time.LocalDateTime.now;
import static java.util.Objects.isNull;
import static io.shixseyidrin.caching.response.Result.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Result<?> createPost(PostReq request) {
        if (isNull(request.title()) || isNull(request.content())) {
            log.info("invalid request data");
            return failed(
                    INVALID_REQUEST_DATA,
                    "invalid request data"
            );
        }

        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .image(request.image())
                .createdAt(now())
                .build();

        postRepository.save(post);

        return success(post);
    }

    @Override
    public Result<?> getAllPosts() {

        List<Post> allPosts = postRepository.findAll();

        if (allPosts.size() == 0) {
            log.warn("No post found");
            return failed(
                    NO_DATA_FOUND,
                    "post data is empty"
            );
        }

        return success(allPosts);
    }

    @Override
    public Result<?> removePostById(Long id) {
        return null;
    }
}
