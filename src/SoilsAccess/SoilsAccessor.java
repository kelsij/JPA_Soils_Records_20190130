package SoilsAccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Soils.Soil;

public class SoilsAccessor {

	static EntityManagerFactory emfactory = 
			Persistence.createEntityManagerFactory("JPA_Soils_Records");

	public void insertRecord(Soil soil) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(soil);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Soil> showAllSoils(){
		EntityManager em = emfactory.createEntityManager();
		List<Soil> allSoils = em.createQuery("SELECT s FROM Soil s").getResultList();
		return allSoils;
	}
	
	//Delete soil record
	public void deleteSoil(Soil toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Soil> typedQuery = em.createQuery(
				"select soil from Soil soil where soil.sms = :selectedSMS "
				+ "and soil.name = :selectedName and soil.csr = :selectedCSR", Soil.class);

		typedQuery.setParameter("selectedSMS", toDelete.getSMS());
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedCSR", toDelete.getCSR());
		
		//Set max results to 1
		typedQuery.setMaxResults(1);
		
		//get result and save to list
		Soil result = typedQuery.getSingleResult();
		
		//remove record
		em.remove(result);
		em.getTransaction().commit();
		em.close();		
	}
	
	//Search by ID
	public Soil searchSoilByID(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Soil soilRecord = em.find(Soil.class, idToEdit);
		em.close();
		
		return soilRecord;
	}
	//Update Soil record
	public void updateSoilRecord(Soil toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	//Search by SMS
	public List<Soil> searchSoilBySMS(String sms){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Soil> typedQuery = em.createQuery("select soil from Soil soil where"
				+"soil.sms = : selectedSMS", Soil.class); 
		 
		List<Soil> soilRecords = typedQuery.getResultList();
		em.close();
		
		return soilRecords;
	}
	
	//Search by Name
	public List<Soil> searchSoilByName(String name){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Soil> typedQuery = em.createQuery("select soil from Soil soil where"
				+"soil.name = : selectedName", Soil.class); 
		 
		List<Soil> soilRecords = typedQuery.getResultList();
		em.close();
		
		return soilRecords;
	}
	
	//Search by CSR
	public List<Soil> searchSoilByCSR(int csr){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Soil> typedQuery = em.createQuery("select soil from Soil soil where"
				+"soil.csr = : selectedCSR", Soil.class); 
		 
		List<Soil> soilRecords = typedQuery.getResultList();
		em.close();
		
		return soilRecords;
	}

	//cleanup
	public void cleanUp() {
		emfactory.close();
	}
}
