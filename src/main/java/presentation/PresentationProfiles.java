package presentation;

import dao.DaoImpl;
import dao.DaoImpl2;
import dao.IDao;
import java.util.Locale;
import metier.IMetier;
import metier.MetierImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public class PresentationProfiles {
    public static void main(String[] args) {
        String profil = args.length == 0 ? "dev" : args[0];
        if (!"dev".equals(profil) && !"prod".equals(profil)) {
            throw new IllegalArgumentException("Profil attendu : dev ou prod.");
        }

        System.out.println("========== SPRING DI - PROFIL "
                + profil.toUpperCase(Locale.ROOT) + " ==========\n");

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.getEnvironment().setActiveProfiles(profil);
            context.register(ConfigurationProfils.class);
            context.refresh();

            IMetier metier = context.getBean(IMetier.class);
            double result = metier.calcul();
            System.out.println("\nRésultat = " + result);
            System.out.println("\n[OK] Profil Spring actif : " + profil);
        }
    }

    // Configuration dédiée, sans component scanning : un seul DAO par profil.
    @Configuration
    public static class ConfigurationProfils {
        // Deux noms pour le même bean permettent de conserver l'un ou l'autre
        // qualifier dans MetierImpl. Ici, c'est le profil qui choisit l'implémentation.
        @Bean(name = {"dao", "dao2"})
        @Profile("prod")
        public IDao daoProduction() {
            System.out.println("Version de production");
            return new DaoImpl();
        }

        @Bean(name = {"dao", "dao2"})
        @Profile("dev")
        public IDao daoDeveloppement() {
            System.out.println("Version de développement");
            return new DaoImpl2();
        }

        @Bean
        public IMetier metier() {
            // Spring injecte le DAO du profil actif dans le champ @Autowired.
            return new MetierImpl();
        }
    }
}
