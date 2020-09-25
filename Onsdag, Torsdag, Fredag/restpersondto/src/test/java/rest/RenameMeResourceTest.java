//package rest;
//
//
//import entities.Person;
//import dto.PersonDTO;
//import dto.PersonsDTO;
//import exception.MissingInputException;
//import exception.PersonNotFoundException;
//import utils.EMF_Creator;
//import io.restassured.RestAssured;
//
//import static io.restassured.RestAssured.given;
//
//import io.restassured.parsing.Parser;
//
//import java.net.URI;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.ws.rs.core.UriBuilder;
//
//import org.glassfish.grizzly.http.server.HttpServer;
//import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
//import org.glassfish.jersey.server.ResourceConfig;
//
//import static io.restassured.RestAssured.when;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//public class PersonResourceTest {
//
//    private static final int SERVER_PORT = 7777;
//    private static final String SERVER_URL = "http://localhost/api";
//    private static Person p1, p2;
//
//    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
//    private static HttpServer httpServer;
//    private static EntityManagerFactory emf;
//
//    static HttpServer startServer() {
//        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
//        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//        EMF_Creator.startREST_TestWithDB();
//        emf = EMF_Creator.createEntityManagerFactory();
//
//        httpServer = startServer();
//        RestAssured.baseURI = SERVER_URL;
//        RestAssured.port = SERVER_PORT;
//        RestAssured.defaultParser = Parser.JSON;
//    }
//
//    @AfterAll
//    public static void closeTestServer() {
//        EMF_Creator.endREST_TestWithDB();
//        httpServer.shutdownNow();
//    }
//
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        p1 = new Person("Kim", "Hansen", "13234");
//        p2 = new Person("Pia", "Hansen", "2222222");
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
//            em.persist(p1);
//            em.persist(p2);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//    
//    @Test
//    public void getAllPersons() throws Exception {
//        List<PersonDTO> personsDtos;
//        
//        personsDtos = given()
//                .contentType("application/json")
//                .when()
//                .get("/person/all")
//                .then()
//                .extract().body().jsonPath().getList("all", PersonDTO.class);
//        
//        PersonsDTO p1DTO = new PersonDTO(p1);
//        PersonsDTO p2DTO = new PersonDTO(p2);
//        assertThat(personsDtos), containsInAnyOrder(p1DTO, p2DTO));
//        
//        
//        
//    }
//    @Test
//public void addPerson() throws Exception {
//    given()
//            .contentType("application/json")
//            .body(new PersonDTO)("ib","xxx","123") )
//    .when()
//            .post("person")
//            .then()
//            .body("fName", equalTo("ib") )
//            .body("lName", equalTo("xxx") )
//            .body("id", notNullValue());
//    
//   
//    
//    
//    }
//
//}  
//
