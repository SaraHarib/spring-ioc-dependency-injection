# Spring IoC et Injection de Dépendances

Ce TP met en pratique l’IoC et l’injection de dépendances avec Spring à travers `@Component`, `@Autowired`, `@Qualifier`, la configuration XML et les profils Spring.

---

## Injection par annotations avec `dao`

`DaoImpl`, sélectionné avec `@Qualifier("dao")`, retourne `100.0`, ce qui donne un résultat final de `200.0`.

![Injection avec dao](images/01-annotations-dao-200.png)

---

## Injection par annotations avec `dao2`

`DaoImpl2`, sélectionné avec `@Qualifier("dao2")`, retourne `150.0`, ce qui donne un résultat final de `300.0`.

![Injection avec dao2](images/02-qualifier-dao2-300.png)

---

## Configuration XML

Spring charge les beans depuis `applicationContext.xml` et injecte `dao2` dans la couche métier via le setter.

![Configuration XML](images/03-configuration-xml.png)

---

## Profils Spring

Les profils permettent de changer d’implémentation selon l’environnement :

- `dev` → `DaoImpl2` → `300.0`
- `prod` → `DaoImpl` → `200.0`

![Profils Spring](images/04-profils-spring.png)

---

## Technologies

- Java 17
- Maven
- Spring Context 6.1.2
- JUnit 4.13.2

---

## Exécution

```bash
mvn clean test
```
