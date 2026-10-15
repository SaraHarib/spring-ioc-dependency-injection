package presentation;

import metier.IMetier;
import metier.MetierImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"dao", "metier"})
public class Presentation2 {
    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println("========== SPRING DI - ANNOTATIONS ==========\n");

        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(Presentation2.class)) {
            IMetier metier = context.getBean(IMetier.class);
            // Lire le qualifier pour que l'affichage suive le choix fait dans MetierImpl.
            String daoSelectionne = MetierImpl.class.getDeclaredField("dao")
                    .getAnnotation(Qualifier.class).value();

            System.out.println("[SPRING] Bean métier récupéré depuis le contexte.");
            System.out.println("[SPRING] Implémentation DAO sélectionnée : " + daoSelectionne);
            System.out.println();
            double result = metier.calcul();
            System.out.println("\nRésultat = " + result);
            System.out.println("\n[OK] Injection Spring avec @Autowired et @Qualifier(\""
                    + daoSelectionne + "\").");
        }
    }
}
