package fr.basileparent.unitTestWorkshop.controller;

import fr.basileparent.unitTestWorkshop.model.Pony;
import fr.basileparent.unitTestWorkshop.repository.PonyRepository;
import fr.basileparent.unitTestWorkshop.service.PonyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PonyController.class)
public class PonyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PonyService ponyService;

    @MockBean
    private PonyRepository ponyRepository;

    @Test
    public void registerMale_shouldReturnOk_ifPonyIsMale() throws Exception {
        // Given
        when(ponyService.getGender(any(Pony.class))).thenReturn(1);

        // When & then
        this.mockMvc.perform(post("/pony/register/male")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"sex\" : \"MALE\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }

    @Test
    public void registerMale_shouldReturnBadRequest_ifPonyIsNotMale() throws Exception {
        // Given
        when(ponyService.getGender(any(Pony.class))).thenReturn(2);

        // When & then
        this.mockMvc.perform(post("/pony/register/male")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"sex\" : \"NOT_MALE\" }"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Pony in parameter should be male"));
    }

}