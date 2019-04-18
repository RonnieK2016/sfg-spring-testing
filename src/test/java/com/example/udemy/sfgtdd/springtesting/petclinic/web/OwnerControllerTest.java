package com.example.udemy.sfgtdd.springtesting.petclinic.web;

import com.example.udemy.sfgtdd.springtesting.petclinic.model.Owner;
import com.example.udemy.sfgtdd.springtesting.petclinic.service.ClinicService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static com.example.udemy.sfgtdd.springtesting.petclinic.web.OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private OwnerController ownerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @AfterEach
    void tearDown() {
        reset(clinicService);
    }

    @Test
    void testInitForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
    }

    @Test
    void testByNameNotFound() throws Exception {
        mockMvc.perform(get("/owners").param("lastName","TestName"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));

        then(clinicService).should().findOwnerByLastName(anyString());
    }

    @Test
    void testFindFormMultipleResults() throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1);
        owner1.setFirstName("John");
        owner1.setLastName("Jackson");

        Owner owner2 = new Owner();
        owner2.setId(2);
        owner2.setFirstName("Jack");
        owner2.setLastName("Jonson");

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        given(clinicService.findOwnerByLastName(argumentCaptor.capture())).willReturn(Arrays.asList(owner1, owner2));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("selections"))
                .andExpect(view().name("owners/ownersList"));

        then(clinicService).should().findOwnerByLastName(anyString());
        assertEquals("", argumentCaptor.getValue());
    }

    @Test
    void testFindFormSingleResult() throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1);
        owner1.setFirstName("John");
        owner1.setLastName("Jackson");

        given(clinicService.findOwnerByLastName(anyString())).willReturn(Arrays.asList(owner1));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + owner1.getId()));

        then(clinicService).should().findOwnerByLastName(anyString());
    }

    @Test
    void testNewOwnerPostValid() throws Exception {
        mockMvc.perform(post("/owners/new")
                .param("firstName", "Jimmy")
                .param("lastName", "Buffett")
                .param("address", "123 Duval St ")
                .param("city", "Key West")
                .param("telephone", "3151231234"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testNewOwnerPostNotValid() throws Exception {
        mockMvc.perform(post("/owners/new")
                .param("firstName", "Jimmy")
                .param("lastName", "Buffett")
                .param("city", "Key West"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner", "address"))
                .andExpect(model().attributeHasFieldErrors("owner", "telephone"))
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
    }

    @Test
    void testUpdateOwnerPostValid() throws Exception {
        mockMvc.perform(post("/owners/{ownerid}/edit",1)
                .param("firstName", "Jimmy")
                .param("lastName", "Buffett")
                .param("address", "123 Duval St ")
                .param("city", "Key West")
                .param("telephone", "3151231234"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testEditOwnerPostNotValid() throws Exception {
        mockMvc.perform(post("/owners/{ownerid}/edit",1)
                .param("address", "123 Duval St ")
                .param("city", "Key West")
                .param("telephone", "3151231234"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner","firstName", "lastName"))
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
    }
    
    @Test
    void testInitUpdateForm() throws Exception {

        given(clinicService.findOwnerById(anyInt())).willReturn(new Owner());

        mockMvc.perform(get("/owners/{ownerId}/edit", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));

        then(clinicService).should().findOwnerById(anyInt());
    }

    @Test
    void testInitFindForm() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void testShowOwner() throws Exception {

        given(clinicService.findOwnerById(anyInt())).willReturn(new Owner());

        mockMvc.perform(get("/owners/{ownerId}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/ownerDetails"));
    }
}