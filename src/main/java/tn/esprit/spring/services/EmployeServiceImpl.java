package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	public static final Logger logger = Logger.getLogger(EmployeServiceImpl.class);

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	
	public int ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Employe employe = employeRepository.findById(employeId).get();
		employe.setEmail(email);
		employeRepository.save(employe);

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Departement depManagedEntity = deptRepoistory.findById(depId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		if(depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);

		}

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Departement dep = deptRepoistory.findById(depId).get();

		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;
			}
		}
	}

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);
		
	}

	public String getEmployePrenomById(int employeId) {
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		return employeManagedEntity.getPrenom();
	}
	public void deleteEmployeById(int employeId)
	{
		Employe employe = employeRepository.findById(employeId).get();

	
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
	}

	public void deleteContratById(int contratId) {
		logger.info("In deleteContratById():");
		logger.debug("debut de deleteContratById: " );
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		contratRepoistory.delete(contratManagedEntity);
		   
        logger.info("out of deleteContratById()");
			logger.debug("contrat: "  + " supprime avec succé");

	}
    
	public int getNombreEmployeJPQL() {
		logger.info("In getNombreEmploye():");
		logger.debug("debut de getNombreEmploye: " );
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		logger.info("In getAllEmployeNames():");
		logger.debug("debut de getAllEmployeNames: " );
		
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		logger.info("In getAllEmployeByEntreprise():");
		logger.debug("debut de getAllEmployeByEntreprise: " );
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}
   
	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		logger.info("In mettreAjourEmailByEmployeIdJPQL()");
		logger.debug("debut de mettreAjourEmailByEmployeIdJPQL()");
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);
        logger.info("out of mettreAjourEmailByEmployeIdJPQL");
			logger.debug("Modifie avec succé");

	}

	
	public void deleteAllContratJPQL() {
		logger.info("In deleteAllContratJPQL():");
		logger.debug("debut de deleteAllContratJPQL: " );
		
         employeRepository.deleteAllContratJPQL();
         
         logger.info("out of deleteAllContratJPQL()");
			logger.debug("contrat: "  + " supprime avec succé");

	}

   
	public float getSalaireByEmployeIdJPQL(int employeId) {
		logger.info("In getSalaireByEmployeIdJPQL():");
		logger.debug("debut de getSalaireByEmployeIdJPQL: " );
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
		
		
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		logger.info("In getSalaireMoyenByDepartementId():");
		logger.debug("debut de getSalaireMoyenByDepartementId: " );
		
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
		
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		
		logger.info("In getTimesheetsByMissionAndDate():");
		logger.debug("debut de getTimesheetsByMissionAndDate: " );
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
		
	
	}
   
	public List<Employe> getAllEmployes() {
		logger.info("In getAllEmployes():");
		logger.debug("debut de getAllEmployes: " );
				return (List<Employe>) employeRepository.findAll();
				
				
	}
}
