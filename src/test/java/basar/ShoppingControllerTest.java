package basar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import basar.data.Position;
import basar.data.PositionRepository;
import basar.ui.CartResource;
import basar.ui.ShoppingCart;
import basar.ui.ShoppingController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BasarSpyContext.class)
public class ShoppingControllerTest {

    @Inject
    ShoppingController shoppingController;
    
    @Inject
    ShoppingCart shoppingCart;
    
    @Inject
    PositionRepository spyPositionRepository;
        
    @Before
    public void setup() {
        // 1.) Setup
        shoppingCart.clear();
    }
    
    public void cleanup() {
        // 4.) cleanup
        reset(spyPositionRepository);
    }
    
    @Test
    public void addItemToCart() {
        // Test Parameter
        String price = "10,50";
        String basarNumber = "100";
        String description = "Rad";
        
        // 2.) Execute
        assertNotNull(shoppingController);
        CartResource resource = new CartResource();
        resource.setBasarNumber(basarNumber);
        resource.setPrice(price);
        resource.setDescription(description);
        shoppingController.addItemToCart(resource);
        
        // 3.) Verify
        ArgumentCaptor<Position> positionCaptor = ArgumentCaptor.forClass(Position.class);
        verify(spyPositionRepository).save(positionCaptor.capture());
        // Test finds a integration bug and the location.
        assertNotNull("each position should have a seller", positionCaptor.getValue().getSeller());
    }

}
