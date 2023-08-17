package br.com.papait.bruno.agenda.api.config;

import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTO;
import br.com.papait.bruno.agenda.api.domain.dto.schedule.ScheduleDTOResponse;
import br.com.papait.bruno.agenda.api.domain.entity.ScheduleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();

    // ScheduleEntity -> ScheduleDTOResponse
    modelMapper.createTypeMap(ScheduleEntity.class, ScheduleDTOResponse.class)
        .addMapping(ScheduleEntity::getPatientEntity, ScheduleDTOResponse::setPatientDTO);

    // ScheduleDTO -> ScheduleEntity
    modelMapper.createTypeMap(ScheduleDTO.class, ScheduleEntity.class)
        .addMapping(ScheduleDTO::getPatientDTO, ScheduleEntity::setPatientEntity);

    return modelMapper;
  }
}
