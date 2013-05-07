package basar.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import basar.BasarContext;

import basar.core.BasarService;
import basar.data.Position;
import basar.data.SimpleUserRepository;
import basar.data.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BasarContext.class)
public class BasarServiceTest {
    
    @Autowired
    BasarService basarService;

    @Autowired
    SimpleUserRepository userRepository;
    
    @Before
    public void setup() {
        User user = new User();
        user.setBasarNumber("100");
        userRepository.addUser(user);
    }
    
    @Test
    public void createPosition_WithExistingUser() {
        Position position = basarService
                .createPosition("100", Long.valueOf(10), "desc");
        assertNotNull(position.getSeller());
    }
    
    @Test(expected=Exception.class)
    public void createPostion_NonExtingUser() throws Exception {
        basarService.createPosition("200", Long.valueOf(10), "desc");
    }

}
