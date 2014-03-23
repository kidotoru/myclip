/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.treewoods.myclip.entity.CollectTarget;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kido
 */
public class CollectTargetDAOTest {
    
    private static EntityManagerFactory emf;
    private static EntityManager em;    
    public CollectTargetDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
                    
        emf = Persistence.createEntityManagerFactory("net.treewoods_RssReader_jar_1.0.0PU");
        em = emf.createEntityManager();
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        em.getTransaction().begin();

    }
    
    @After
    public void tearDown() {
        
        em.getTransaction().commit();
    
    
    }

    /**
     * Test of selectAll method, of class CollectTargetDAO.
     */
    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        
    }
}