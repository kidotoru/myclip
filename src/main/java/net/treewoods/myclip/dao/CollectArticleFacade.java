/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.treewoods.myclip.entity.CollectArticle;

/**
 *
 * @author toru
 */
@Stateless
public class CollectArticleFacade extends AbstractFacade<CollectArticle> {
    @PersistenceContext(unitName = "net.treewoods_MyClip_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CollectArticleFacade() {
        super(CollectArticle.class);
    }
    
}
