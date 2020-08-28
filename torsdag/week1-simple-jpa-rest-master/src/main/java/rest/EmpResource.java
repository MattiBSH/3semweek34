/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;


import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import static facades.Farcade.getAllCustomers;
import static facades.Farcade.getEmpByName;
import static facades.Farcade.getEmpWithHighestSalary;
import static facades.Farcade.getEmployeebyID;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author matti
 */
@Path("employee")
public class EmpResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmpResource
     */
    public EmpResource() {
    }
    @Path("/o")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getXml() {
        return "ok";
        
    }

    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDTO> putAll() {
        List <Employee> e=getAllCustomers();
        List <EmployeeDTO>listDTO = new ArrayList();
        EmployeeDTO dto;
        for (Employee employee : e) {    
            
            listDTO.add(dto=new EmployeeDTO(employee));
        }      
        return listDTO;
    }
    @Path("/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String id(@PathParam("id")Long id) {
        Employee e = new Employee();
        
       e= getEmployeebyID(id);
       EmployeeDTO dto= new EmployeeDTO(e);
        Gson gson=new Gson();
        return new Gson().toJson(dto);
    }
    @Path("/highestpaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String putHighestPaid() {
        Employee e =getEmpWithHighestSalary();
        EmployeeDTO dto = new EmployeeDTO(e);
        Gson gson=new Gson();
        return gson.toJson(dto);
    }
    
    @Path("/name/{name}")
    @GET
    
    @Produces(MediaType.APPLICATION_JSON)
    public String name(@PathParam("name")String name) {
        Employee e =getEmpByName(name);
        EmployeeDTO dto = new EmployeeDTO(e);
        Gson gson=new Gson();
        return gson.toJson(dto);
    }
   
}
