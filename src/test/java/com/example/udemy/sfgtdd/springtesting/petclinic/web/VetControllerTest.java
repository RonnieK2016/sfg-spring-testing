package com.example.udemy.sfgtdd.springtesting.petclinic.web;

import com.example.udemy.sfgtdd.springtesting.petclinic.model.Vet;
import com.example.udemy.sfgtdd.springtesting.petclinic.model.Vets;
import com.example.udemy.sfgtdd.springtesting.petclinic.service.ClinicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    private ClinicService clinicService;
    @Mock
    Map<String, Object> model;

    private VetController vetController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vetController = new VetController(clinicService);
        given(clinicService.findVets()).willReturn(Arrays.asList(new Vet()));
        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    @Test
    void showVetListMvc() throws Exception {
        mockMvc.perform(get("/vets.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vets"))
                .andExpect(view().name("vets/vetList"));
        then(clinicService).should().findVets();
    }

    @Test
    void showVetList() {
        //when
        String viewName = vetController.showVetList(model);

        then(clinicService).should().findVets();
        assertThat(viewName).isEqualTo("vets/vetList");
        then(model).should().put(anyString(), any());
    }

    @Test
    void showResourcesVetList() {

        Vets vets = vetController.showResourcesVetList();

        then(clinicService).should().findVets();
        assertThat(vets.getVetList()).isNotNull();
        assertThat(vets.getVetList().size()).isEqualTo(1);
    }
}