package br.com.papait.bruno.agenda.api.api.controller;

import br.com.papait.bruno.agenda.api.domain.dto.patient.CreatePatientDTORequest;
import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTOResponse;
import br.com.papait.bruno.agenda.api.usecase.patient.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.objectweb.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
@ExtendWith(SpringExtension.class)
public class PatientControllerTest {

  @MockBean
  private SavePatientUseCase savePatientUseCase;

  @MockBean
  private FindAllPatientUseCase findAllPatientUseCase;

  @MockBean
  private FindPatientByIdUseCase findPatientByIdUseCase;

  @MockBean
  private DeletePatientByIdUseCase deletePatientByIdUseCase;

  @MockBean
  private UpdatePatientUseCase updatePatientUseCase;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private CreatePatientDTORequest createPatientDTORequest;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);

    // Mock do CreatePatientDTORequest
    this.createPatientDTORequest =
        new CreatePatientDTORequest(null, "José", "da Silva", "jose@email.com", "10071044534");
  }


  @Test
  public void shouldSuccessfully_CreatePatientTest() throws Exception {
// Crie um objeto PatientDTOResponse simulado para ser retornado pelo savePatientUseCase
//    PatientDTOResponse patientDTOResponse = new PatientDTOResponse();
//    patientDTOResponse.setId(1L);
//    patientDTOResponse.setName("José");
//    patientDTOResponse.setSurname("da Silva");
//    patientDTOResponse.setEmail("jose@email.com");
//    patientDTOResponse.setCpf("10071044534");
//
//    // Simule o comportamento do savePatientUseCase retornando o objeto simulado acima
//    when(this.savePatientUseCase.execute(this.createPatientDTORequest))
//        .thenReturn(patientDTOResponse);

    // Realize a chamada POST para o endpoint /patient
    this.mockMvc
        .perform(MockMvcRequestBuilders.post("/patient")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(this.createPatientDTORequest)))
        .andExpect(status().isCreated());
//    ResponseEntity<PatientDTOResponse> patientDTO = this.patientController.create(this.createPatientDTORequest);
//
//    verify(this.createPatientUseCase).execute(this.createPatientDTORequest);
//    Assertions.assertEquals(patientDTO.getBody(), patientDTOResponse);
//    Assertions.assertEquals(patientDTO.getStatusCodeValue(), 201);
  }
//
//  @Test
//  public void testCreate_CpfAlreadyInUse() {
//    when(this.createPatientUseCase.execute(createPatientDTORequest))
//        .thenThrow(new CpfAlreadyInUseException("Já existe um paciente com o cpf informado"));
//
//    assertThrows(CpfAlreadyInUseException.class, () -> this.patientController.create(createPatientDTORequest));
//    verify(this.createPatientUseCase, times(1)).execute(createPatientDTORequest);
//  }
//
//  @Test
//  public void testCreate_EmailAlreadyInUse() {
//    when(this.createPatientUseCase.execute(createPatientDTORequest))
//        .thenThrow(new EmailAlreadyInUseException("Já existe um paciente com o e-mail informado"));
//
//    assertThrows(EmailAlreadyInUseException.class, () -> this.patientController.create(createPatientDTORequest));
//    verify(this.createPatientUseCase, times(1)).execute(createPatientDTORequest);
//  }
//
//  @Test
//  public void testInvalidRequestBody_Validation() throws Exception {
//    // Cria um objeto CreatePatientDTORequest com valores inválidos
//    CreatePatientDTORequest createPatientDTORequest = new CreatePatientDTORequest(null, null, "emailinvalido", "cpfinvalido");
//
//    try {
//      // Envia uma solicitação POST para o endpoint do Controller com o objeto inválido
//      mockMvc.perform(MockMvcRequestBuilders
//              .post("/patients")
//              .contentType(MediaType.APPLICATION_JSON)
//              .content(objectMapper.writeValueAsString(createPatientDTORequest)))
//          .andExpect(MockMvcResultMatchers.status().isBadRequest());
//    } catch (NestedServletException ex) {
//      // Verifica se a exceção esperada foi lançada
//      assertTrue(ex.getCause() instanceof MethodArgumentNotValidException);
//    }
//  }
}
