/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.service;

import javax.ejb.embeddable.EJBContainer;
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
public class GetRssServiceTest {
    
    public GetRssServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class GetRssService.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        
        

    }
    @Test
    public void testRemoveHtmlTag() throws Exception {
        System.out.println("testRemoveHtmlTag");
        
        GetRssService s = new GetRssService();
        
        String in =  "<a href=\"#\">リンク</a><br>改行<br />改行<br/>改行<p>aaa</p><p > aaa </p> <img></img><blockquote>aaaaa</blockquote><!-- コメント --> ";
        String out = s.removeHtmlTag(in);
        
        //String in2 = "<table cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td align="left" valign="center"><a href="http://rss.rssad.jp/rss/ad/RWFTmzjGxpl3/Fk4ZGp7VbJay?type=2" target="_blank"><img alt="" style="border: 0;" border="0" src="http://rss.rssad.jp/rss/img/RWFTmzjGxpl3/Fk4ZGp7VbJay?type=2&amp;ent=cc998bf390f41ec241fc7e97b13eee0c"/></a></td></tr><tr><td align="left" valign="top" > 商品やサービスなど消費生活全般に関わる問題について、様々な取り組みをご紹介！ </td></tr></tbody></table><div style="font-size:10px;"><span style="padding-top:5px;"><br style="display:none"/><a href="http://www.rssad.jp/trendmatch/trendmatch.html">Ads by Trend Match</a></span><br/></div>"
        
        
        System.out.println(out);
        

    }

}