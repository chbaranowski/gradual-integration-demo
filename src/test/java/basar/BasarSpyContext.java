package basar;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

import basar.data.PositionRepository;

public class BasarSpyContext extends BasarContext {

    @Override
    @Bean
    public PositionRepository positionRepository() {
        return Mockito.spy(super.positionRepository());
    }
    
}
