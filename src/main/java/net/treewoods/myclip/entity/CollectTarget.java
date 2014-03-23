/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "COLLECT_TARGET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CollectTarget.findAll", query = "SELECT c FROM CollectTarget c"),
    @NamedQuery(name = "CollectTarget.findBySiteId", query = "SELECT c FROM CollectTarget c WHERE c.siteId = :siteId"),
    @NamedQuery(name = "CollectTarget.findByUrl", query = "SELECT c FROM CollectTarget c WHERE c.url = :url"),
    @NamedQuery(name = "CollectTarget.findBySiteName", query = "SELECT c FROM CollectTarget c WHERE c.siteName = :siteName"),
    @NamedQuery(name = "CollectTarget.findByDeleteFlg", query = "SELECT c FROM CollectTarget c WHERE c.deleteFlg = :deleteFlg"),
    @NamedQuery(name = "CollectTarget.findByLastPubDateTime", query = "SELECT c FROM CollectTarget c WHERE c.lastPubDateTime = :lastPubDateTime")})
public class CollectTarget implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SITE_ID")
    private Integer siteId;
    @Size(max = 512)
    private String url;
    @Size(max = 512)
    @Column(name = "SITE_NAME")
    private String siteName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DELETE_FLG")
    private char deleteFlg;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "LAST_PUB_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPubDateTime;
    @ManyToMany(mappedBy = "collectTargetList")
    private List<Category> categoryList;
    @OneToMany(mappedBy = "siteId")
    private List<Article> articleList;

    public CollectTarget() {
    }

    public CollectTarget(Integer siteId) {
        this.siteId = siteId;
    }

    public CollectTarget(Integer siteId, char deleteFlg, Date lastPubDateTime) {
        this.siteId = siteId;
        this.deleteFlg = deleteFlg;
        this.lastPubDateTime = lastPubDateTime;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public char getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(char deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public Date getLastPubDateTime() {
        return lastPubDateTime;
    }

    public void setLastPubDateTime(Date lastPubDateTime) {
        this.lastPubDateTime = lastPubDateTime;
    }

    @XmlTransient
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @XmlTransient
    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (siteId != null ? siteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CollectTarget)) {
            return false;
        }
        CollectTarget other = (CollectTarget) object;
        if ((this.siteId == null && other.siteId != null) || (this.siteId != null && !this.siteId.equals(other.siteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.treewoods.myclip.entity.CollectTarget[ siteId=" + siteId + " ]";
    }
    
}
