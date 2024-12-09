package org.example.instructions;


// https://medium.com/@Lakshitha_Fernando/spring-boot-unit-testing-for-repositories-controllers-and-services-using-junit-5-and-mockito-def3ff5891be

import org.assertj.core.api.Assertions;
import org.example.exceptions.EntryAlreadyExistsException;
import org.example.models.RecycleInstructionsEntry;
import org.example.repositories.InstructionRepository;
import org.example.services.InstructionService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstructionsServiceTests {

    @Mock
    private InstructionRepository instructionRepository;

    @InjectMocks
    private InstructionService instructionService;

    private String _material;
    private RecycleInstructionsEntry _entry;

    @BeforeEach
    public void setup() {
        _material = "air";
        _entry = new RecycleInstructionsEntry(_material);
    }

    @Test
    @Order(1)
    public void createEntryTest() {
        given(instructionRepository.save(any(RecycleInstructionsEntry.class))).willReturn(_entry);

        RecycleInstructionsEntry entry = instructionService.createInstructionsEntry(_material);

        assertThat(entry).isNotNull();
        assertThat(entry.getMaterialId()).isEqualTo(_material);
    }

    @Test
    @Order(2)
    public void createDuplicateEntryTest() {
        given(instructionRepository.existsByMaterial(_material)).willReturn(true);

        Assertions.assertThatExceptionOfType(EntryAlreadyExistsException.class).isThrownBy(() -> {
           RecycleInstructionsEntry entry = instructionService.createInstructionsEntry(_material);
        });
    }

    @Test
    @Order(3)
    public void getEntryTest() {
        given(instructionRepository.existsByMaterial(_material)).willReturn(true);
        given(instructionRepository.selectByMaterial(_material)).willReturn(_entry);

        RecycleInstructionsEntry entry = instructionService.getInstructionsEntry(_material);

        assertThat(entry).isNotNull();
        assertThat(entry.getMaterialId()).isEqualTo(_material);
    }

    @Test
    @Order(4)
    public void updateEntryTest() {
        given(instructionRepository.existsById(_entry.getId())).willReturn(true);
//        given(instructionRepository.selectByMaterial(_material)).willReturn(_entry);
        _entry.addInstructions("Inhale");
        given(instructionRepository.save(any(RecycleInstructionsEntry.class))).willReturn(_entry);

        RecycleInstructionsEntry updatedEntry = instructionService.updateInstructionsEntry(_entry);

        assertThat(updatedEntry.getInstructions().size()).isEqualTo(1);
        assertThat(updatedEntry.getInstructions().get(0)).isEqualTo("Inhale");
    }

    @Test
    @Order(5)
    public void deleteEntryTest() {
        given(instructionRepository.existsById(any(Integer.class))).willReturn(true);
        willDoNothing().given(instructionRepository).deleteById(_entry.getId() != null ? _entry.getId() : 1);

        instructionService.deleteInstructionsEntry(_entry.getId() != null ? _entry.getId() : 1);

        verify(instructionRepository, times(1)).deleteById(_entry.getId() != null ? _entry.getId() : 1);
    }
}
