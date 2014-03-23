/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author toru
 */
@Entity
@Table(name = "TAG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
    @NamedQuery(name = "Tag.findByArticleId", query = "SELECT t FROM Tag t WHERE t.tagPK.articleId = :articleId"),
    @NamedQuery(name = "Tag.findBySeq", query = "SELECT t FROM Tag t WHERE t.tagPK.seq = :seq"),
    @NamedQuery(name = "Tag.findByTag", query = "SELECT t FROM Tag t WHERE t.tag = :tag")})
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TagPK tagPK;
    @Size(max = 256)
    private String tag;
    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ARTICLE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;

    public Tag() {
    }

    public Tag(TagPK tagPK) {
        this.tagPK = tagPK;
    }

    public Tag(int articleId, int seq) {
        this.tagPK = new TagPK(articleId, seq);
    }

    public TagPK getTagPK() {
        return tagPK;
    }

    public void setTagPK(TagPK tagPK) {
        this.tagPK = tagPK;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagPK != null ? tagPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.tagPK == null && other.tagPK != null) || (this.tagPK != null && !this.tagPK.equals(other.tagPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.treewoods.myclip.entity.Tag[ tagPK=" + tagPK + " ]";
    }
    
}
