package basar.ui;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import basar.common.PriceUtils;
import basar.data.Position;
import basar.data.PositionRepository;
import basar.data.User;
import basar.data.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BasarMockContext.class)
public class ShoppingControllerTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Inject
    PositionRepository mockPositionRepository;
    
    @Inject
    UserRepository mockUserRepository;
    
    @Inject
    ShoppingController shoppingController;
    
    @Inject
    ShoppingCart shoppingCart;

    User defaultUser = new User();
    
    @Before
    public void setup() {
        // 1.) Setup
        shoppingCart.clear();
        defaultUser.setBasarNumber("100");
        when(mockUserRepository.findByBasarNumber(defaultUser.getBasarNumber())).thenReturn(defaultUser);
        when(mockPositionRepository.save(any(Position.class))).thenAnswer(new Answer<Position>() {
            @Override
            public Position answer(InvocationOnMock invocation) throws Throwable {
                return (Position) invocation.getArguments()[0];
            }
        });
    }
    
    @After
    public void cleanup() {
        // 4.) cleanup
        reset(mockPositionRepository, mockUserRepository);
    }
    
    @Test
    public void addItemToCart() {
        // Test parameters
        String price = "10,50";
        String description = "Rad";
        
        // 2.) execute
        CartResource resource = new CartResource();
        resource.setBasarNumber(defaultUser.getBasarNumber());
        resource.setPrice(price);
        resource.setDescription(description);
        shoppingController.addItemToCart(resource);
        
        // 3.) verify
        assertEquals(PriceUtils.formatPriceToLong(price), shoppingCart.sum());
        Position position = shoppingCart.getPositions().iterator().next();
        assertEquals(defaultUser, position.getSeller());
        assertEquals(description, position.getDescription());
        assertEquals(PriceUtils.formatPriceToLong(price), position.getPrice());
    }
    
    @Test
    public void addItemToCart_unknownUser() throws Exception {
        // 3.) Verify
        thrown.expect(Exception.class);
        
        // 2.) execute
        CartResource resource = new CartResource();
        resource.setBasarNumber("Unknown User Id");
        resource.setPrice("10,50");
        resource.setDescription("Rad");
        shoppingController.addItemToCart(resource);
    }
    
}
