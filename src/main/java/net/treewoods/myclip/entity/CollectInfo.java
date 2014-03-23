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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author toru
 */
@Entity
@Table(name = "COLLECT_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CollectInfo.findAll", query = "SELECT c FROM CollectInfo c"),
    @NamedQuery(name = "CollectInfo.findByCollectId", query = "SELECT c FROM CollectInfo c WHERE c.collectId = :collectId"),
    @NamedQuery(name = "CollectInfo.findByCollectDate", query = "SELECT c FROM CollectInfo c WHERE c.collectDate = :collectDate")})
public class CollectInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COLLECT_ID")
    private Integer collectId;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "COLLECT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date collectDate;
    @OneToMany(mappedBy = "collectId")
    private List<CollectArticle> collectArticleList;

    public CollectInfo() {
    }

    public CollectInfo(Integer collectId) {
        this.collectId = collectId;
    }

    public CollectInfo(Integer collectId, Date collectDate) {
        this.collectId = collectId;
        this.collectDate = collectDate;
    }

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
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
        hash += (collectId != null ? collectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CollectInfo)) {
            return false;
        }
        CollectInfo other = (CollectInfo) object;
        if ((this.collectId == null && other.collectId != null) || (this.collectId != null && !this.collectId.equals(other.collectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.treewoods.myclip.entity.CollectInfo[ collectId=" + collectId + " ]";
    }
    
}
