/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author matti
 */
public class Farcade {
     private static FacadeExample instance;
    private static EntityManagerFactory emf=Persistence.createEntityManagerFactory("pu");
    
    
    public static Employee getEmployeebyID(long id){
         EntityManager em = emf.createEntityManager();
        try{
            Employee e = em.find(Employee.class,id);
            
            return e;
        }finally {
            em.close();
        }
    }
    public static Employee getEmployeebyName(String name){
         EntityManager em = emf.createEntityManager();
        try{
            Employee e = em.find(Employee.class,name);
            
            return e;
        }finally {
            em.close();
        }
    }
    
     public static Employee addE(String name,String adress,int salary){
        Employee e = new Employee(name,adress,salary);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return e;
        }finally {
            em.close();
        }
    }
    
    public static List<Employee> getAllCustomers(){
         EntityManager em = emf.createEntityManager();
         
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("Select e from Employee e",Employee.class);
     return query.getResultList();
        }finally {
            em.close();
        }
        
    }
    public static Employee getEmpWithHighestSalary(){
            EntityManager em = emf.createEntityManager();

    TypedQuery<Employee>query6=em.createQuery("SELECT e FROM Employee e where e.salary=(select MAX(e.salary) from Employee e)",Employee.class);
           Employee maxemp=query6.getSingleResult();
           System.out.println(maxemp.toString());
         return maxemp;
           
    }
    
    public static Employee getEmpByName(String name){
                 EntityManager em = emf.createEntityManager();

    TypedQuery<Employee>query6=em.createQuery(
        "SELECT e FROM Employee e WHERE e.name = '" + name + "'",
        Employee.class);
           Employee maxemp=query6.getSingleResult();
         return query6.getSingleResult();  
    }
}
