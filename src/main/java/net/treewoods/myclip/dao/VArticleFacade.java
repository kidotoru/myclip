/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.treewoods.myclip.entity.VArticle;

/**
 *
 * @author kido
 */
@Stateless
public class VArticleFacade extends AbstractFacade<VArticle> {
    @PersistenceContext(unitName = "net.treewoods_MyClip_war_1.0.0PU")
    private EntityManager em;

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VArticleFacade() {
        super(VArticle.class);
    }
    
    private static String FIND_BY_COLLECT_ID_SQL;
    static {
        StringBuilder sb  = new StringBuilder();

	sb.append("select ");
	sb.append("  T2.ARTICLE_ID ");
	sb.append("  ,( select ct.SITE_NAME from COLLECT_TARGET ct where ct.SITE_ID = T3.SITE_ID ) as SITE_NAME ");
	sb.append("  ,T3.CRETE_DATE_TIME as COLLECT_DATE");
	sb.append("  ,T3.ARTICLE_URL ");
	sb.append("  ,T3.ARTICLE_TITLE ");
	sb.append("  ,(select COUNT(distinct T.FROM_IP)  from ACCESS_HISTORY T where T.TO_ARTICLE_ID =T3.ARTICLE_ID) as CNT1 ");
	sb.append("  ,(select COUNT(T.FROM_IP) from ACCESS_HISTORY T where T.TO_ARTICLE_ID =T3.ARTICLE_ID) as CNT2 ");
	sb.append("  ,T3.ARTICLE_CONTENTS ");
	sb.append("from ");
	sb.append("  COLLECT_INFO T1 ");
	sb.append("  , COLLECT_ARTICLE T2 ");
	sb.append("  , ARTICLE T3 ");
	sb.append("where ");
	sb.append("  T1.COLLECT_ID = :COLLECT_ID ");
	sb.append("  and T1.COLLECT_ID = T2.COLLECT_ID ");
	sb.append("  and T2.ARTICLE_ID = T3.ARTICLE_ID ");
	sb.append("order by T3.CRETE_DATE_TIME desc, T3.ARTICLE_ID ");

//	sb.append("select ");
//	sb.append("  T1.ARTICLE_ID ");
//	sb.append(", (select CT.SITE_NAME from COLLECT_TARGET CT where CT.SITE_ID = T1.SITE_ID) as SITE_NAME ");
//	sb.append("  ,T1.CRETE_DATE_TIME as COLLECT_DATE ");
//	sb.append("  ,T1.ARTICLE_URL ");
//	sb.append("  ,T1.ARTICLE_TITLE ");
//	sb.append("  ,(select COUNT(distinct T.FROM_IP)  from ACCESS_HISTORY T where T.TO_ARTICLE_ID =T1.ARTICLE_ID) as CNT1 ");
//	sb.append("  ,(select COUNT(T.FROM_IP) from ACCESS_HISTORY T where T.TO_ARTICLE_ID =T1.ARTICLE_ID) as CNT2 ");
//	sb.append("  ,T1.ARTICLE_CONTENTS ");
//	sb.append("from ");
//	sb.append("  ARTICLE T1 ");
//	sb.append("order by T1.CRETE_DATE_TIME desc, T1.ARTICLE_ID ");
//	sb.append("limit :START_POS ,:END_POS ");

        
        FIND_BY_COLLECT_ID_SQL = sb.toString();
    }
        
    
    public List<VArticle> findByCollectId(int collectId){
        List<VArticle> resultList =
                em.createNativeQuery(FIND_BY_COLLECT_ID_SQL, VArticle.class)
                .setParameter("COLLECT_ID", collectId)
                .getResultList();
        return resultList;
    }
}
