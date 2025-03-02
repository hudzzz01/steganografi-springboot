package com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StegaImage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "key")
    private String key;

    @Column(name = "secret_message")
    private String secretMessage;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "actual_stega_image")
    private ActualImage actualImage;
}
