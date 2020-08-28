/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import static entity.Animal_.id;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author matti
 */
@Path("animals_db")
public class Animals_dbResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Animals_dbResource
     */
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    public Animals_dbResource() {
    }

    
    
@Path("animals2")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimals() {
  EntityManager em = emf.createEntityManager();
  try{
      TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
      List<Animal> animals = query.getResultList();
      return new Gson().toJson(animals);
   } finally {
          em.close();
   }
}

@Path("/animalbyid/{id}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Animal getAnimalById(@PathParam("id") long id) {
      EntityManager em = emf.createEntityManager();
      

            Animal a = em.find(Animal.class, id);

            if(a==null){
                throw new EntityNotFoundException("cant find the animal"+id);
            }
 
        return a;

}

@Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query
                    = em.createQuery("Select a from Animal a where a.type = :type", Animal.class);
            String returnValue = "null";
            try {
                returnValue = query.setParameter("type", type).getSingleResult().getType();
            } catch (NoResultException e) {
            }
            return returnValue;
        } finally {
            em.close();
        }
    }
@Path("/animalbyRandom")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Animal getAnimalByRandom() {
      EntityManager em = emf.createEntityManager();
      
            Random rand = new Random();
            long id =rand.nextInt(6);
            Animal a = em.find(Animal.class, id);

            if(a==null){
                throw new EntityNotFoundException("cant find the animal"+id);
            }
 
        return a;

}
    
}
