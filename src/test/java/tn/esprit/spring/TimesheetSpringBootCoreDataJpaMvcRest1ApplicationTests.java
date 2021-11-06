

package tn.esprit.spring;


import org.junit.Test;

import org.junit.Assert;


import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests {
    @Autowired
    private IEntrepriseService servEntreprise;

    
    @Autowired
    private IEmployeService servEmploye;
    


    
    
  
   
    
    @Transactional
    @Test
    public void getSalaireMoyenByDepartementId(){

    	
        Assert.assertNotNull(servEmploye.getSalaireMoyenByDepartementId(1));
    }

    @Transactional
    @Test
    public void getTimesheetsByMissionAndDate(){
    	
    	Assert.assertNotNull(servEmploye.getSalaireMoyenByDepartementId(1));
    }
   
    @Transactional
    @Test
    public void getAllEmployes(){


        Assert.assertNotNull(servEmploye.getAllEmployes());
    }
    @Test
	  public void getNombreEmployeJPQL() {
			
		  Assert.assertNotNull(servEmploye.getNombreEmployeJPQL());
		}
	  @Test
	  public void getAllEmployeNamesJPQL() {
			Assert.assertNotNull( servEmploye.getAllEmployeNamesJPQL());
		}
	 
	
    

}