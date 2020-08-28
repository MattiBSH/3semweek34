package facades;

import entities.Employee;
import static facades.Farcade.addE;
import static facades.Farcade.getAllCustomers;
import static facades.Farcade.getEmpByName;
import static facades.Farcade.getEmpWithHighestSalary;
import static facades.Farcade.getEmployeebyID;
import static facades.Farcade.getEmployeebyName;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FacadeExampleTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final FacadeExample FE = FacadeExample.getFacadeExample(ENF);
    public FacadeExampleTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        //teknisk set test af addE()

    }
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    
    @BeforeEach
    public void setUp() {
                
    }
    
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /**
     * Test a method here.
     */
    //IDTEST
    @Test
    public void testFindingbyID() {
        Employee e= new Employee("matti","ellergård",20000);
        assertEquals(getEmployeebyID(1).getName(),e.getName());
    }
    //TESTNAME
    @Test
    public void testAddingAndFindingbyName() {        
        Employee e= new Employee("matti","ellergård",20000);
        assertEquals(getEmpByName("matti").getName(),e.getName());
    }
    //TEST MAX SALARY INDIVIDUAL
   @Test
    public void testEMPWithMaxSalary(){
        Employee e= new Employee("matti","ellergård",20000);
        assertEquals(getEmpWithHighestSalary().getName(),e.getName());
        assertEquals(getEmpWithHighestSalary().getSalary(),e.getSalary());
        assertEquals(getEmpWithHighestSalary().getAdress(),e.getAdress());
    }
    @Test
    public void testGetAll(){
    assertEquals(getAllCustomers().size(),1);
    }
}
