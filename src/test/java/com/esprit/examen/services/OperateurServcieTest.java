package com.esprit.examen.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.OneToMany;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class OperateurServcieTest {
	
	@MockBean
	private OperateurRepository or;
	
	private Operateur o1 = new Operateur("fatma","daâs");
	private Operateur o2 = new Operateur("Fatma","daâs");
	  
	@Autowired
	    IOperateurService os;
	
    
    @Test
	public void addOperateurTest() {
    	when(or.save(o1)).thenReturn(o1);
    	assertNotNull(o1);
		assertEquals(o1, os.addOperateur(o1)); 
		System.out.println("add works !");
	}
	
   @Test 
    public void retrieveAllOperateursTest() {
    	when(or.findAll()).thenReturn(Stream
    			.of(o1,o2)
    			.collect(Collectors.toList()));
    	assertEquals(2,os.retrieveAllOperateurs().size());
    	System.out.println("Retrieve operators works !");
    }
    
   
    
    @Test
    public void DeleteOperateurTest() {
    	or.save(o1);
    	os.deleteOperateur(o1.getIdOperateur());
    	verify(or, times(1)).deleteById(o1.getIdOperateur());
    	System.out.println("Delete works !");
    	
    }

    
    @Test 
    public void UpdateOperateurTest() {
    	when(or.save(o1)).thenReturn(o1);
    	assertNotNull(o1);
    	assertEquals(o1, os.updateOperateur(o1));
    	System.out.println("Update works !");
    }
    
    @Test
    public void retrieveOperateurTest() {
    	when(or.findById(o1.getIdOperateur())).thenReturn(Optional.of(o1));
    	assertEquals(o1, os.retrieveOperateur(o1.getIdOperateur()));
    	System.out.println("Retrieve operator works !");
    }
    
    
}
