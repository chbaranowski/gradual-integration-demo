package basar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import basar.core.Basar;
import basar.core.BasarService;
import basar.data.PositionRepository;
import basar.data.SimplePositionRepository;
import basar.data.SimpleUserRepository;
import basar.data.UserRepository;
import basar.ui.ShoppingCart;
import basar.ui.ShoppingController;

@Configuration
public class BasarContext {
    
    @Bean
    public Basar basar() {
        return new BasarService();
    }

    @Bean
    public PositionRepository positionRepository() {
        return new SimplePositionRepository();
    }

    @Bean
    public UserRepository userRepository() {
        return new SimpleUserRepository();
    }
    
    @Bean
    public ShoppingController shoppingController() {
        return new ShoppingController();
    }
    
    @Bean
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

}
