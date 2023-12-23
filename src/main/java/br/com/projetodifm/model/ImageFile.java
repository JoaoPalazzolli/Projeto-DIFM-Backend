package br.com.projetodifm.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "image_files")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ImageFile implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(name = "image_uri", nullable = false)
    private String imageUri;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(nullable = false)
    private Long size;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Produto produto;
}
