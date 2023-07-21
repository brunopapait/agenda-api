package br.com.papait.bruno.agenda.api.repository;

import br.com.papait.bruno.agenda.api.domain.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {
}
