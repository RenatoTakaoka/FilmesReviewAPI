package com.github.renatotakaoka.filmes_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "tb_filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    private Integer ano;
    @OneToMany(mappedBy = "filme")
    private List<Review> filmeReviews;
    @ManyToOne
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;

}
