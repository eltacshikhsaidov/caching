package io.shixseyidrin.caching.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = 123456789L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    @Lob
    private byte[] image;
    private LocalDateTime createdAt;
    @OneToOne
    @PrimaryKeyJoinColumn
    private RatingData ratingData;
}
