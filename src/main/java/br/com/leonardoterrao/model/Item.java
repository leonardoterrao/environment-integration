package br.com.leonardoterrao.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private Integer quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

}
