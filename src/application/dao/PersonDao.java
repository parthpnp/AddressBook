package application.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import application.model.Person;
import application.util.HibernateUtil;

public class PersonDao {
	public List<Person> getAllPerson(){
		Session session = HibernateUtil.getFactory().openSession();
		session.beginTransaction();
		List<Person> persons = session.createQuery("from Person").list();
		session.getTransaction().commit();
		session.close();
		
		return persons;
	}
	
	public  void insertPerson(Person person) {
		Session session = HibernateUtil.getFactory().openSession();
		session.beginTransaction();
		session.save(person);
		session.getTransaction().commit();
		session.close();
	}
}
