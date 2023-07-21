package br.com.papait.bruno.agenda.api.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "agenda")
@Getter @Setter
public class AgendaEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String descricao;

  @Column(name = "dh_horario")
  private LocalDateTime dhHorario;

  @Column(name = "dh_criacao")
  private LocalDateTime dhCriacao;

  @ManyToOne
  private PacienteEntity pacienteEntity;
}
