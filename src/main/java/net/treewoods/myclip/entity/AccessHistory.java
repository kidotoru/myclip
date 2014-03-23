/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author toru
 */
@Entity
@Table(name = "ACCESS_HISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccessHistory.findAll", query = "SELECT a FROM AccessHistory a"),
    @NamedQuery(name = "AccessHistory.findByHistoryId", query = "SELECT a FROM AccessHistory a WHERE a.historyId = :historyId"),
    @NamedQuery(name = "AccessHistory.findByFromIp", query = "SELECT a FROM AccessHistory a WHERE a.fromIp = :fromIp"),
    @NamedQuery(name = "AccessHistory.findByAccessDateTime", query = "SELECT a FROM AccessHistory a WHERE a.accessDateTime = :accessDateTime")})
public class AccessHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HISTORY_ID")
    private Integer historyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "FROM_IP")
    private String fromIp;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ACCESS_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accessDateTime;
    @JoinColumn(name = "TO_ARTICLE_ID", referencedColumnName = "ARTICLE_ID")
    @ManyToOne(optional = false)
    private Article toArticleId;

    public AccessHistory() {
    }

    public AccessHistory(Integer historyId) {
        this.historyId = historyId;
    }

    public AccessHistory(Integer historyId, String fromIp, Date accessDateTime) {
        this.historyId = historyId;
        this.fromIp = fromIp;
        this.accessDateTime = accessDateTime;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public String getFromIp() {
        return fromIp;
    }

    public void setFromIp(String fromIp) {
        this.fromIp = fromIp;
    }

    public Date getAccessDateTime() {
        return accessDateTime;
    }

    public void setAccessDateTime(Date accessDateTime) {
        this.accessDateTime = accessDateTime;
    }

    public Article getToArticleId() {
        return toArticleId;
    }

    public void setToArticleId(Article toArticleId) {
        this.toArticleId = toArticleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historyId != null ? historyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessHistory)) {
            return false;
        }
        AccessHistory other = (AccessHistory) object;
        if ((this.historyId == null && other.historyId != null) || (this.historyId != null && !this.historyId.equals(other.historyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.treewoods.myclip.entity.AccessHistory[ historyId=" + historyId + " ]";
    }
    
}
