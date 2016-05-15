package br.com.leonardoterrao.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Gender gender = Gender.UNINFORMED;
    private String email;

}
