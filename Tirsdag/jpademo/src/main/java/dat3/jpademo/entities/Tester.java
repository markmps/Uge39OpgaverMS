/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat3.jpademo.entities;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Marks
 */
public class Tester {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Person p1 = new Person("Jønke",1963);
        Person p2 = new Person("Blodie",1959);
        
        Address a1 = new Address("Store torv 1",2323,"Nr.snede");
        Address a2 = new Address("Langgade 34",1212,"Valby");
        
        p1.setAddress(a1);
        p2.setAddress(a2);
        
        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(300);
        
        p1.AddFee(f1);
        p1.AddFee(f3);
        p2.AddFee(f2);
        
        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("ButterFly");
        SwimStyle s3 = new SwimStyle("Breast stroke");
        
        p1.AddSwimStyle(s1);
        p1.AddSwimStyle(s3);
        p2.AddSwimStyle(s2);

        
        em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
        
        em.getTransaction().commit();
        
        
        em.getTransaction().begin();
           p1.removeSwimStyle(s3);
        em.getTransaction().commit();
        
        System.out.println("p1 " + p1.getId() + "," + p1.getName());
        System.out.println("p2 "+ p2.getId() + "," + p2.getName());
        
        System.out.println("Jønkes gade: " + p1.getAddress().getStreet());
        
        System.out.println("Lad os se om to-vejs virker: " + a1.getPerson().getName());
        
        System.out.println("Hvem har betalt f2? Det har : " + f2.getPerson().getName());
        
        System.out.println("Hvad er der blevet betalt i alt?");
        
        TypedQuery<Fee> q1 = em.createQuery("SELECT f from Fee f",Fee.class);
        List<Fee> fees = q1.getResultList();
        
        for(Fee f: fees){
            System.out.println(f.getPerson().getName() + ": " + f.getAmount() + " kr. Den: " + f.getPayDate() + " Adr: " + f.getPerson().getAddress().getCity());
        }
    }
    
}
