package br.com.papait.bruno.agenda.api.repository;

import br.com.papait.bruno.agenda.api.domain.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
  Optional<PatientEntity> findByCpf(String cpf);
  Optional<PatientEntity> findByEmail(String email);
}
