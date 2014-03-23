/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author toru
 */
@Entity
@Table(name = "COLLECT_ARTICLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CollectArticle.findAll", query = "SELECT c FROM CollectArticle c"),
    @NamedQuery(name = "CollectArticle.findById", query = "SELECT c FROM CollectArticle c WHERE c.id = :id")})
public class CollectArticle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ARTICLE_ID")
    @ManyToOne
    private Article articleId;
    @JoinColumn(name = "COLLECT_ID", referencedColumnName = "COLLECT_ID")
    @ManyToOne
    private CollectInfo collectId;

    public CollectArticle() {
    }

    public CollectArticle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Article getArticleId() {
        return articleId;
    }

    public void setArticleId(Article articleId) {
        this.articleId = articleId;
    }

    public CollectInfo getCollectId() {
        return collectId;
    }

    public void setCollectId(CollectInfo collectId) {
        this.collectId = collectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CollectArticle)) {
            return false;
        }
        CollectArticle other = (CollectArticle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.treewoods.myclip.entity.CollectArticle[ id=" + id + " ]";
    }
    
}
