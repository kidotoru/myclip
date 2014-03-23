/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kido
 */
@Entity
@Table(name = "V_ARTICLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VArticle.findAll", query = "SELECT v FROM VArticle v")})
public class VArticle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ARTICLE_ID")
    private Integer articleId;
    
    @Basic(optional = false)
    @Column(name = "COLLECT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date collectDate;

    @Basic(optional = false)
    @Column(name = "SITE_NAME")
    private String siteName;

    
    
    @Basic(optional = false)
    @Column(name = "ARTICLE_URL")
    private String articleUrl;
    @Basic(optional = false)
    @Column(name = "ARTICLE_TITLE")
    private String articleTitle;
    @Column(name = "CNT1")
    private BigInteger cnt;
    @Column(name = "CNT2")
    private BigInteger cnt2;

    @Lob
    @Column(name = "ARTICLE_CONTENTS")
    private byte[] articleContents;    
    
    public VArticle() {
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public BigInteger getCnt() {
        return cnt;
    }

    public void setCnt(BigInteger cnt) {
        this.cnt = cnt;
    }

    public BigInteger getCnt2() {
        return cnt2;
    }

    public void setCnt2(BigInteger cnt2) {
        this.cnt2 = cnt2;
    }


    /**
     * @return the articleId
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * @param articleId the articleId to set
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public byte[] getArticleContents() {
        return articleContents;
    }

    public void setArticleContents(byte[] articleContents) {
        this.articleContents = articleContents;
    }
    
    public String getArticleContentsStr(){
        
        String result = "";
        if ( this.articleContents != null){
            try {
                result = new String(this.articleContents,"UTF8");
            }
            catch (UnsupportedEncodingException ex) {
                Logger.getLogger(VArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
}
