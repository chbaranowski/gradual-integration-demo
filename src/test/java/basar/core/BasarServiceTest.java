package basar.core;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import basar.BasarContext;
import basar.data.Position;
import basar.data.SimplePositionRepository;
import basar.data.SimpleUserRepository;
import basar.data.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BasarContext.class)
public class BasarServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Inject
    Basar basar;
    
    @Inject
    SimpleUserRepository simpleUserRepository;
    
    @Inject
    SimplePositionRepository simplePositionRepository;
    
    User defaultTestUser = new User();

    @Before
    public void setup() {
        // 1.) Setup
        defaultTestUser.setBasarNumber("100");
        simpleUserRepository.addUser(defaultTestUser );
        simplePositionRepository.getPositions().clear();
    }
    
    @Test
    public void createPosition() throws Exception {
        // Test Parameter
        Long price = 1050L;
        String description = "Rad";
        
        // 2.) Execution
        Position position = basar.createPosition(
                defaultTestUser.getBasarNumber(), 
                price, 
                description);
        
        // 3.) Verify
        assertEquals(price, position.getPrice());
        assertEquals(description, position.getDescription());
        assertNotNull("postion should have a seller", position.getSeller());
        assertEquals(defaultTestUser, position.getSeller());
    }
    
    /**
     * Test should fail, test find a integration bug and the location.
     */
    @Test
    public void createPosition_UnknownUser() throws Exception {
        thrown.expect(Exception.class);
        basar.createPosition("UNKNOWN USER", 1050L, "Rad");
    }
    
}
