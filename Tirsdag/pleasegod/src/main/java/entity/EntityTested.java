/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityTested {
        private static final EntityManagerFactory enf=Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {
        EntityManager em = enf.createEntityManager();

         
        Customer cus = new Customer("matti","hansen");
        
        em.getTransaction().begin();
        em.persist(cus);
        em.getTransaction().commit();
        
       
    }
}
