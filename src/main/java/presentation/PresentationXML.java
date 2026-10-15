package presentation;

import metier.IMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresentationXML {
    public static void main(String[] args) {
        System.out.println("========== SPRING DI - CONFIGURATION XML ==========\n");

        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContext.xml")) {
            IMetier metier = (IMetier) context.getBean("metier");
            System.out.println("[SPRING XML] Bean metier chargé depuis applicationContext.xml");
            System.out.println("[SPRING XML] DAO injecté : dao2");
            System.out.println();
            double result = metier.calcul();
            System.out.println("\nRésultat (XML) = " + result);
            System.out.println("\n[OK] Injection par setter avec configuration XML.");
        }
    }
}
