package br.com.papait.bruno.agenda.api.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "paciente")
@Getter @Setter
public class PacienteEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50)
  private String nome;

  @Column(length = 100)
  private String sobrenome;

  @Column(length = 100)
  private String email;

  @Column(length = 15)
  private String cpf;
}
