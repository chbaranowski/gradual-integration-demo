package basar.ui;

import javax.inject.Inject;

import basar.common.PriceUtils;
import basar.core.Basar;
import basar.data.Position;


public class ShoppingController {
    
    @Inject
	Basar basar;

    @Inject
	ShoppingCart shoppingCart;
	
	public void addItemToCart(CartResource resource) {
		Position position = basar.createPosition(resource.getBasarNumber(),
		        PriceUtils.formatPriceToLong(resource.getPrice()),
				resource.getDescription());
		shoppingCart.addPosition(position);
	}
}
