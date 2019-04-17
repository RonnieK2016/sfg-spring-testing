package com.example.udemy.sfgtdd.springtesting.petclinic.service;

import com.example.udemy.sfgtdd.springtesting.petclinic.model.PetType;
import com.example.udemy.sfgtdd.springtesting.petclinic.repository.OwnerRepository;
import com.example.udemy.sfgtdd.springtesting.petclinic.repository.PetRepository;
import com.example.udemy.sfgtdd.springtesting.petclinic.repository.VetRepository;
import com.example.udemy.sfgtdd.springtesting.petclinic.repository.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {

    @Mock
    private PetRepository petRepository;
    @Mock
    private VetRepository vetRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private VisitRepository visitRepository;

    private ClinicServiceImpl clinicService;

    @BeforeEach
    void setUp() {
        clinicService = new ClinicServiceImpl(petRepository, vetRepository, ownerRepository, visitRepository);
    }

    @Test
    void findPetTypes() {
        List<PetType> petTypes = Arrays.asList(new PetType());
        given(petRepository.findPetTypes()).willReturn(petTypes);

        Collection<PetType> petTypesReturned = clinicService.findPetTypes();

        then(petRepository).should().findPetTypes();
        assertNotNull(petTypesReturned);
        assertThat(petTypesReturned.size()).isEqualTo(1);
    }
}