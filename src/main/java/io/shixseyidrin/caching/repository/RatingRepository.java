package io.shixseyidrin.caching.repository;

import io.shixseyidrin.caching.model.RatingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingData, Long> {
    RatingData findByPostId(Long postId);
}
