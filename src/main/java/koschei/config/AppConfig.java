package koschei.config;

import koschei.models.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "koschei")
public class AppConfig {

    @Bean
    public static Island2 getIsland(Wood3 wood) {
        return new Island2(wood);
    }

    @Bean
    public static Wood3 getWood(Rabbit4 rabbit) {
        return new Wood3(rabbit);
    }

    @Bean
    public static Rabbit4 getRabbit(Duck5 duck) {
        return new Rabbit4(duck);
    }

    @Bean
    public static Duck5 getDuck(Egg6 egg) {
        return new Duck5(egg);
    }

    @Bean
    public static Egg6 getEgg(Needle7 needle) {
        return new Egg6(needle);
    }

    @Bean
    public static Needle7 getNeedle(Deth8 deth) {
        return new Needle7(deth);
    }

}
