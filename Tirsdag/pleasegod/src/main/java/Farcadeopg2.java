/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import javax.persistence.*;
import entity.Customer;
/**
 *
 * @author matti
 */
public class Farcadeopg2 {
    public static void main(String[] args) {
       findCustomer(1);
       addC("Matti","Hansen");
        System.out.println(getAllCustomers());
       
    }
    
    private static EntityManagerFactory emf=Persistence.createEntityManagerFactory("pu");
;
    private static Farcadeopg2 instance;

    Farcadeopg2() {}

    public static Farcadeopg2 getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Farcadeopg2();
        }
        return instance;
    }
    
    public static Customer addC(String firstname,String lastname){
        Customer c = new Customer(firstname,lastname);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        }finally {
            em.close();
        }
    }
    
    public static Customer findCustomer(long id){
         EntityManager em = emf.createEntityManager();
        try{
            Customer c = em.find(Customer.class,id);
            
            return c;
        }finally {
            em.close();
        }
    }
    public static List<Customer> getAllCustomers(){
         EntityManager em = emf.createEntityManager();
         
        try{
            TypedQuery<Customer> query = 
                       em.createQuery("Select c from Customer c",Customer.class);
     return query.getResultList();
        }finally {
            em.close();
        }
        
    }

}
