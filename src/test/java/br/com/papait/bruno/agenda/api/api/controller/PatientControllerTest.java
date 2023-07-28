//package br.com.papait.bruno.agenda.api.api.controller;
//
//import br.com.papait.bruno.agenda.api.domain.dto.patient.CreatePatientDTORequest;
//import br.com.papait.bruno.agenda.api.domain.dto.patient.PatientDTOResponse;
//import br.com.papait.bruno.agenda.api.domain.exception.CpfAlreadyInUseException;
//import br.com.papait.bruno.agenda.api.domain.exception.EmailAlreadyInUseException;
//import br.com.papait.bruno.agenda.api.usecase.patient.CreatePatientUseCase;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.util.NestedServletException;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//@WebMvcTest(PatientController.class)
//public class PatientControllerTest {
//
//  private CreatePatientDTORequest createPatientDTORequest;
//  @InjectMocks
//  private PatientController patientController;
//
//  @Autowired
//  @Mock
//  private CreatePatientUseCase createPatientUseCase;
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @Autowired
//  private ObjectMapper objectMapper;
//
//  @BeforeEach
//  public void setup() {
//    MockitoAnnotations.initMocks(this);
//
//    // Mock do CreatePatientDTORequest
//    this.createPatientDTORequest =
//        new CreatePatientDTORequest("José", "da Silva", "jose@email.com", "10071044534");
//  }
//
//  @Test
//  public void shouldSuccessfullyCreateThePatientTest() {
//    PatientDTOResponse patientDTOResponse = new PatientDTOResponse();
//    patientDTOResponse.setId(1L);
//    patientDTOResponse.setName("José");
//    patientDTOResponse.setSurname("da Silva");
//    patientDTOResponse.setEmail("jose@email.com");
//    patientDTOResponse.setCpf("10071044534");
//
//    when(this.createPatientUseCase.execute(this.createPatientDTORequest)).thenReturn(patientDTOResponse);
//    ResponseEntity<PatientDTOResponse> patientDTO = this.patientController.create(this.createPatientDTORequest);
//
//    verify(this.createPatientUseCase).execute(this.createPatientDTORequest);
//    Assertions.assertEquals(patientDTO.getBody(), patientDTOResponse);
//    Assertions.assertEquals(patientDTO.getStatusCodeValue(), 201);
//  }
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
//}
