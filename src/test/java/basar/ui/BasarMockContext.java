package basar.ui;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

import basar.BasarContext;
import basar.data.PositionRepository;
import basar.data.UserRepository;

public class BasarMockContext extends BasarContext {

    @Override
    @Bean
    public PositionRepository positionRepository() {
        return Mockito.mock(PositionRepository.class);
    }
    
    @Override
    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }
    
}
