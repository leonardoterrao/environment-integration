package br.com.leonardoterrao.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Sale {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne
    private Client client;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

}
