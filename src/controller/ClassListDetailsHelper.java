package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ClassDetails;

public class ClassListDetailsHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StudentClassList");

	public void insertNewClassListDetails(ClassDetails s) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}

	public List<ClassDetails> getLists() {
		EntityManager em = emfactory.createEntityManager();
		List<ClassDetails> allDetails = em.createQuery("SELECT d FROM ClassDetails d").getResultList();
		return allDetails;
	}

	public void deleteList(ClassDetails toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ClassDetails> typedQuery = em
				.createQuery("select detail from ClassDetails detail where detail.id = :selectedId", ClassDetails.class);
		// Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedId", toDelete.getSemesterId());

		// we only want one result
		typedQuery.setMaxResults(1);

		// get the result and save it into a new list item
		ClassDetails result = typedQuery.getSingleResult();

		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();

	}

	public ClassDetails searchForListDetailsById(Integer tempId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ClassDetails found = em.find(ClassDetails.class, tempId);
		em.close();
		return found;
	}

	public void updateList(ClassDetails toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
}