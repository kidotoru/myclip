/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author toru
 */
@Entity
@Table(name="ARTICLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByArticleId", query = "SELECT a FROM Article a WHERE a.articleId = :articleId"),
    @NamedQuery(name = "Article.findByArticleUrl", query = "SELECT a FROM Article a WHERE a.articleUrl = :articleUrl"),
    @NamedQuery(name = "Article.findByArticleTitle", query = "SELECT a FROM Article a WHERE a.articleTitle = :articleTitle"),
    @NamedQuery(name = "Article.findByCreteDateTime", query = "SELECT a FROM Article a WHERE a.creteDateTime = :creteDateTime"),
    @NamedQuery(name = "Article.findByUpdateDateTime", query = "SELECT a FROM Article a WHERE a.updateDateTime = :updateDateTime")})
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ARTICLE_ID")
    private Integer articleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ARTICLE_URL")
    private String articleUrl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ARTICLE_TITLE")
    private String articleTitle;
    @Lob
    @Column(name = "ARTICLE_CONTENTS")
    private byte[] articleContents;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "CRETE_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creteDateTime;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "UPDATE_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;
    @JoinColumn(name = "SITE_ID", referencedColumnName = "SITE_ID")
    @ManyToOne
    private CollectTarget siteId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private List<Tag> tagList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toArticleId")
    private List<AccessHistory> accessHistoryList;
    @OneToMany(mappedBy = "articleId")
    private List<CollectArticle> collectArticleList;

    public Article() {
    }

    public Article(Integer articleId) {
        this.articleId = articleId;
    }

    public Article(Integer articleId, String articleUrl, String articleTitle, Date creteDateTime, Date updateDateTime) {
        this.articleId = articleId;
        this.articleUrl = articleUrl;
        this.articleTitle = articleTitle;
        this.creteDateTime = creteDateTime;
        this.updateDateTime = updateDateTime;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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

    public byte[] getArticleContents() {
        return articleContents;
    }

    public void setArticleContents(byte[] articleContents) {
        this.articleContents = articleContents;
    }

    public Date getCreteDateTime() {
        return creteDateTime;
    }

    public void setCreteDateTime(Date creteDateTime) {
        this.creteDateTime = creteDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public CollectTarget getSiteId() {
        return siteId;
    }

    public void setSiteId(CollectTarget siteId) {
        this.siteId = siteId;
    }

    @XmlTransient
    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @XmlTransient
    public List<AccessHistory> getAccessHistoryList() {
        return accessHistoryList;
    }

    public void setAccessHistoryList(List<AccessHistory> accessHistoryList) {
        this.accessHistoryList = accessHistoryList;
    }

    @XmlTransient
    public List<CollectArticle> getCollectArticleList() {
        return collectArticleList;
    }

    public void setCollectArticleList(List<CollectArticle> collectArticleList) {
        this.collectArticleList = collectArticleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articleId != null ? articleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.articleId == null && other.articleId != null) || (this.articleId != null && !this.articleId.equals(other.articleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.treewoods.myclip.entity.Article[ articleId=" + articleId + " ]";
    }
    
}
