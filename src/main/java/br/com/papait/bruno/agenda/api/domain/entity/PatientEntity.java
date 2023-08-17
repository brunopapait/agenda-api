package br.com.papait.bruno.agenda.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patient")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50)
  private String name;

  @Column(length = 100)
  private String surname;

  @Column(length = 100)
  private String email;

  @Column(length = 15)
  private String cpf;
}
