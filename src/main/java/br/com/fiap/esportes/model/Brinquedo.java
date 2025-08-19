package br.com.fiap.esportes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "TDS_TB_BRINQUEDO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brinquedo extends RepresentationModel<Brinquedo> {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @Column(name = "CLASSIFICACAO", nullable = false, length = 50)
    private String classificacao;

    @Column(name = "TAMANHO", nullable = false, length = 50)
    private String tamanho;

    @Column(name = "PRECO", nullable = false)
    private Float preco;
}
