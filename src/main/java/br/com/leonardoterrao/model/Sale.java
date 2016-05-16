package br.com.leonardoterrao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sale {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime dateTime;

    @ManyToOne
    private Client client;

    @OneToMany
    private List<Item> items = new ArrayList<>();

}
