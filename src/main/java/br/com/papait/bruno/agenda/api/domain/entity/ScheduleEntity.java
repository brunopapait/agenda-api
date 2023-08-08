package br.com.papait.bruno.agenda.api.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
@Getter
@Setter
public class ScheduleEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @Column(name = "dh_time")
  private LocalDateTime dhTime;

  @Column(name = "dh_creation")
  private LocalDateTime dhCreation;

  @ManyToOne
  @JoinColumn(name = "patient_id", referencedColumnName = "id")
  private PatientEntity patientEntity;

  @PrePersist
  public void setCreationTime() {
    this.dhCreation = LocalDateTime.now();
  }
}
