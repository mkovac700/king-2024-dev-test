<a name="readme-top"></a>

<!-- INTRO --> 
<div align="center">

  <h1 align="center">Stručni zadatak za KING ICT Academy 2024</h3>

  <p align="center">
    Middleware REST API s naprednim mogućnostima pretraživanja i filtriranja
  </p>
  <!-- TABLE OF CONTENTS -->
  <a href="#-opis-zadatka">Opis zadatka</a> • 
  <a href="#-konfiguracija">Konfiguracija</a> • 
  <a href="#-upotreba">Upotreba</a> 
</div>

<br>

<!-- ABOUT THE PROJECT -->

## 📝 Opis zadatka

### Uvod

Potrebno je razviti middleware koje će imati mogućnost dohvata proizvoda iz različitih izvora (web servisi, baze podataka, file system, RSS). Kako ste tek počeli s projektom dobili ste samo prvi izvor podataka, a to je REST API koji se nalazi na dnu dokumenta. Vaš zadatak je razviti middleware REST API koja prikazuje proizvode s naprednim mogućnostima filtriranja. Imajte na umu da u budućnosti morate moći dodati i druge izvore proizvoda.

### Zadatak

* Implementirajte endpoint koji vraća listu proizvoda (slika, naziv, cijena, skraćen opis do 100 znakova)
* Implementirajte endpoint koji vraća detalje jednog proizvoda
* Implementirajte endpoint koji omogućava filtriranje po kategoriji i cijeni
* Implementirajte endpoint koji za uneseni tekst pretražuje proizvode po nazivu

### Nice to have

* Autentifikacija i autorizacija
* Razmislite kako biste riješili ako korisnik više puta poziva endpoint pretrage ili filtriranja proizvoda s istim parametrima
* Logiranje (npr. Info, warning, error…)
* Testovi (npr. Integracijski, unit…)

### Dodatni zahtjevi

* Obratiti pozornost na strukturu projekta i korištenje preporučenih dobrih praksi i konvencija
* Dokumentirati endpointove
* Za API poziv možete koristiti https://dummyjson.com i to za:
    * Podaci o korisnicima sustava - https://dummyjson.com/users
    * Prijava u sustav i dohvat tokena - https://dummyjson.com/docs/auth
    * Proizvode - https://dummyjson.com/products
    * Kategorije - https://dummyjson.com/products/categories

<br/>

Za detalje kliknite <a href="./assets/King_Akademija_2024_DEV_test.pdf"><strong>ovdje</strong></a>.

<p align="right">(<a href="#readme-top">povratak na vrh</a>)</p>

## ⚙️ Konfiguracija

### Preduvjeti

Za pokretanje rješenja bit će vam potrebno sljedeće:

* Java 17 ili veća:
  * za izradu rješenja korišten je `jdk-17.0.2`

* Eclipse IDE (opcionalno)

* Maven 
    * za izradu rješenja korištena je Maven Eclipse integracija:
    https://marketplace.eclipse.org/content/eclipse-m2e-maven-support-eclipse-ide

* Springboot v3.3.0
    * za izradu rješenja korištena je Springboot Eclipse integracija:
    https://marketplace.eclipse.org/content/spring-tools-4-aka-spring-tool-suite-4

* Postman
    * korišten za testiranje servisa

### Priprema

  * Instalirati potrebne Eclipse dodatke (ako se koristi Eclipse)

  * Klonirajte ovaj repozitorij:

    https://github.com/mkovac700/king-2024-dev-test.git

  * Otvorite projekt u Eclipse IDE:

    * `File > Open Projects from File System... > Directory...` 

    * Odaberite korijenski direktorij projekta
  
  * Pokrenuti Spring Boot aplikaciju:

    * `Run As > Spring Boot App`

    * Ako je sve bilo u redu, aplikacija bi trebala biti dostupna na `localhost:8080/api/products`

<p align="right">(<a href="#readme-top">povratak na vrh</a>)</p>

## 🚀 Upotreba

* Aplikacija koristi osnovnu autentifikaciju, pa je prilikom pristupanja resursima potrebno unijeti sljedeće testne podatke:

    * username: `user`
    * password: `password`

* Endpoint za dohvaćanje svih proizvoda:

    Osnovna putanja:
    ```bash
    localhost:8080/api/products
    ```
    Moguće je koristiti i straničenje:
    ```bash
    localhost:8080/api/products?page=0&size=5
    ```

    <div align="center">
    <a href="https://raw.githubusercontent.com/mkovac700/king-2024-dev-test/docs/assets/Screenshot_1.png"><img alt="king" src="https://raw.githubusercontent.com/mkovac700/king-2024-dev-test/docs/assets/Screenshot_1.png"></a>
    </div>

* Endpoint za dohvaćanje jednog proizvoda (prema ID):

    Osnovna putanja:
    ```bash
    localhost:8080/api/products/1
    ```

    <div align="center">
    <a href="https://raw.githubusercontent.com/mkovac700/king-2024-dev-test/docs/assets/Screenshot_2.png"><img alt="king" src="https://raw.githubusercontent.com/mkovac700/king-2024-dev-test/docs/assets/Screenshot_2.png"></a>
    </div>

* Endpoint za filtriranje po kategoriji i cijeni:

    Osnovna putanja:
    ```bash
    localhost:8080/api/products/filter?category=beauty&priceMin=8.99&priceMax=12.99
    ```

    <div align="center">
    <a href="https://raw.githubusercontent.com/mkovac700/king-2024-dev-test/docs/assets/Screenshot_3.png"><img alt="king" src="https://raw.githubusercontent.com/mkovac700/king-2024-dev-test/docs/assets/Screenshot_3.png"></a>
    </div>

* Endpoint za pretraživanje proizvoda po nazivu:

    Osnovna putanja:
    ```bash
    localhost:8080/api/products/search?name=calvin
    ```

    <div align="center">
    <a href="https://raw.githubusercontent.com/mkovac700/king-2024-dev-test/docs/assets/Screenshot_4.png"><img alt="king" src="https://raw.githubusercontent.com/mkovac700/king-2024-dev-test/docs/assets/Screenshot_4.png"></a>
    </div>

<br/>

* Testiranje endpoint-ova moguće je obaviti putem Postmana. Moguće je importati Postman kolekciju dostupnu na `assets\king-2024-dev-test.postman_collection.json`

* Testiranje ispravnosti rada endpoint-ova moguće je obaviti i korištenjem Unit Testova:

    * `src/test/java/org.king.test.mkovac/`
    * Desni klik na `ProductControllerUnitTest.java` i potom `Run As > JUnit Test` za izvršavanje testova

    

<p align="right">(<a href="#readme-top">povratak na vrh</a>)</p>


