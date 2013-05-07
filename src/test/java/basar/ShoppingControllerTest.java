package basar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import basar.common.PriceUtils;
import basar.data.Position;
import basar.ui.CartResource;
import basar.ui.ShoppingCart;
import basar.ui.ShoppingController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BasarContext.class)
public class ShoppingControllerTest {

    @Inject
    ShoppingController shoppingController;
    
    @Inject
    ShoppingCart shoppingCart;
        
    @Before
    public void setup() {
        // 1.) Setup
        shoppingCart.clear();
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
        assertEquals(PriceUtils.formatPriceToLong(price), shoppingCart.sum());        
        Position position = shoppingCart.getPositions().iterator().next();
        assertEquals(PriceUtils.formatPriceToLong(price), position.getPrice());
        assertEquals(description, position.getDescription());
        // Finds a integration bug, but not the location.
        assertNotNull("each position should have a seller", position.getSeller());
        assertEquals(basarNumber, position.getSeller().getBasarNumber());
    }

}
