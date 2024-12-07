package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controllers.InstructionController;
import org.example.models.RecycleInstructionsEntry;
import org.example.services.InstructionService;
import org.hibernate.sql.results.jdbc.internal.ResultSetAccess;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(InstructionController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstructionsControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstructionService instructionService;

    @Autowired
    private ObjectMapper objectMapper;

    RecycleInstructionsEntry entry;

    @BeforeEach
    public void setup() {
        entry = new RecycleInstructionsEntry("air");
    }

    @Test
    @Order(1)
    @DisplayName("POST entry test")
    public void postEntryTest() throws Exception {
        given(instructionService.createInstructionsEntry(any(String.class))).willReturn(entry);

        ResultActions response = mockMvc.perform(post("/instructions/create-entry")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entry.getMaterialId())));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.materialId", is(entry.getMaterialId())));
    }

    @Test
    @Order(2)
    @DisplayName("GET all entries test")
    public void getAllEntriesTest() throws Exception {
        List<RecycleInstructionsEntry> entries = instructionService.getAllEntries();

        ResultActions response = mockMvc.perform(get("/instructions/get-entries"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(entries.size())));
    }

    @Test
    @Order(3)
    @DisplayName("GET entry test")
    public void getEntryTest() throws Exception {
        given(instructionService.getInstructionsEntry(entry.getMaterialId())).willReturn(entry);

        ResultActions response = mockMvc.perform(get("/instructions/get/{materialId}", entry.getMaterialId()).accept(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.materialId", is(entry.getMaterialId())));
    }

    @Test
    @Order(4)
    @DisplayName("PUT update entry test")
    public void putUpdateEntryTest() throws Exception {
        given(instructionService.getInstructionsEntry(entry.getMaterialId())).willReturn(entry);
        entry.addInstructions("inhale");
        entry.addInstructions("exhale");
        given(instructionService.updateInstructionsEntry(any(RecycleInstructionsEntry.class))).willReturn(entry);


        ResultActions res = mockMvc.perform(put("/instructions/update-entry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entry))
                .accept(MediaType.APPLICATION_JSON));

        res.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.instructions", hasSize(entry.getInstructions().size())));

    }

    @Test
    @Order(5)
    @DisplayName("DELETE entry test")
    public void deleteEntryTest() throws Exception {
        willDoNothing().given(instructionService).deleteInstructionsEntry(entry.getId());

        ResultActions res = mockMvc.perform(delete("/instructions/delete-entry/{id}", entry.getId() != null ? entry.getId() : 1));

        res.andExpect(status().isNoContent())
                .andDo(print());
    }
}
