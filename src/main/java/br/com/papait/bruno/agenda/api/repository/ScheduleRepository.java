package br.com.papait.bruno.agenda.api.repository;

import br.com.papait.bruno.agenda.api.domain.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
  Optional<ScheduleEntity> findByDhTime(LocalDateTime dhTime);
}
