

import estateagentguiproject.EstateAgent;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yasin
 */
public class EstateAgentTest {

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void CalculateCommission_CalculatedSuccessfully() {
        //Giving correct values
        double price = 100;
        double comm = 10;
        
        //Calling method
        EstateAgent agent = new EstateAgent();
        
        // Declaring expected and actual answers
        double expectedAnswer = 10;
        double actualAnswer = agent.CalculateCommission(price, comm);
        //Checking that the results are equal
        assertEquals(expectedAnswer, actualAnswer, 0.001);
        
    }
    
    @Test
    public void CalculateCommission_CalculatedUnsuccessfully() {
        //Giving incorrect values
        double price = 12039500;
        double comm = 10;
        // Calling method
        EstateAgent agent = new EstateAgent();
        
        
        double expectedAnswer = 10;
        double actualAnswer = agent.CalculateCommission(price, comm);
        //Checking that the results are not equal
        assertNotEquals(expectedAnswer, actualAnswer, 0.001);
        
    }
}
