/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author toru
 */
@Embeddable
public class TagPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARTICLE_ID")
    private int articleId;
    @Basic(optional = false)
    @NotNull
    private int seq;

    public TagPK() {
    }

    public TagPK(int articleId, int seq) {
        this.articleId = articleId;
        this.seq = seq;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) articleId;
        hash += (int) seq;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagPK)) {
            return false;
        }
        TagPK other = (TagPK) object;
        if (this.articleId != other.articleId) {
            return false;
        }
        if (this.seq != other.seq) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.treewoods.myclip.entity.TagPK[ articleId=" + articleId + ", seq=" + seq + " ]";
    }
    
}
