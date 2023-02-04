package io.shixseyidrin.caching.service.impl;

import io.shixseyidrin.caching.model.RatingData;
import io.shixseyidrin.caching.repository.PostRepository;
import io.shixseyidrin.caching.repository.RatingRepository;
import io.shixseyidrin.caching.response.Result;
import io.shixseyidrin.caching.service.RatingService;
import io.shixseyidrin.caching.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.shixseyidrin.caching.constants.ExceptionCode.NO_DATA_FOUND;
import static io.shixseyidrin.caching.response.Result.*;
import static io.shixseyidrin.caching.util.PostUtil.isPresent;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RedisService redisService;
    private final RatingRepository ratingRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    @Cacheable(value = "postLikes", key = "#postId")
    public Result<?> like(Long postId) {

        if (isPresent(postId, postRepository)) {
            log.warn("Post with postId = {} not exist", postId);
            return failed(
                  NO_DATA_FOUND,
                  "post with id=" + postId + " not found"
            );
        }

        log.info(
                "Getting data from cache for postId: {}",
                postId
        );
        RatingData ratingData = (RatingData) redisService.get(postId);


        if (isNull(ratingData)) {
            log.info(
                    "Data not available on cache so " +
                            "getting from database for postId: {}",
                    postId
            );
            ratingData = ratingRepository.findByPostId(postId);
            if (isNull(ratingData)) {
                log.info(
                        "Data not available on database," +
                                " so creating one for postId: {}",
                        postId
                );
                ratingData = RatingData
                        .builder()
                        .postId(postId)
                        .likes(0)
                        .dislikes(0)
                        .build();
            }
        }

        log.info(
                "Increasing like value by one for postId: {}",
                postId
        );
        ratingData.setLikes(ratingData.getLikes() + 1);

        log.info(
                "Saving like data in cache for postId: {}",
                postId
        );
        redisService.set(postId, ratingData);

        log.info(
                "Saving like data to database for postId: {}",
                postId
        );
        ratingRepository.save(ratingData);

        return success(ratingData);
    }

    @Override
    @Transactional
    @Cacheable(value = "postDisLikes", key = "#postId")
    public Result<?> dislike(Long postId) {

        if (!isPresent(postId, postRepository)) {
            log.warn("Post with postId = {} not exist", postId);
            return failed(
                    NO_DATA_FOUND,
                    "post with id=" + postId + " not found"
            );
        }

        log.info(
                "Getting data from cache for postId: {}",
                postId
        );
        RatingData ratingData = (RatingData) redisService.get(postId);
        if (isNull(ratingData)) {
            log.info(
                    "Data not available on cache so " +
                            "getting from database for postId: {}",
                    postId
            );
            ratingData = ratingRepository.findByPostId(postId);
            if (isNull(ratingData)) {
                log.info(
                        "Data not available on database," +
                                " so creating one for postId: {}",
                        postId
                );
                ratingData = RatingData
                        .builder()
                        .postId(postId)
                        .likes(0)
                        .dislikes(0)
                        .build();
            }
        }

        log.info(
                "Increasing dislike value by one for postId: {}",
                postId
        );
        ratingData.setDislikes(ratingData.getDislikes() + 1);

        log.info(
                "Saving dislike data in cache for postId: {}",
                postId
        );
        redisService.set(postId, ratingData);

        log.info(
                "Saving dislike data to database for postId: {}",
                postId
        );
        ratingRepository.save(ratingData);

        return success(ratingData);
    }

    @Override
    public Result<?> getData(Long postId) {

        if (!isPresent(postId, postRepository)) {
            log.warn("Post with postId = {} not exist", postId);
            return failed(
                    NO_DATA_FOUND,
                    "post with id=" + postId + " not found"
            );
        }

        log.info(
                "Getting data from cache for postId: {}",
                postId
        );
        RatingData ratingData = (RatingData) redisService.get(postId);
        if (isNull(ratingData)) {
            log.info(
                    "Data not available on cache so, " +
                            "getting from database for postId: {}",
                    postId
            );
            ratingData = ratingRepository.findByPostId(postId);
            if (isNull(ratingData)) {
                log.info(
                        "Data not available on database," +
                                " so creating one for postId: {}",
                        postId
                );
                ratingData = RatingData
                        .builder()
                        .postId(postId)
                        .likes(0)
                        .dislikes(0)
                        .build();
            }

            redisService.set(postId, ratingData);
        }
        return success(ratingData);
    }
}
