/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "asset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asset.findAll", query = "SELECT a FROM Asset a")
    , @NamedQuery(name = "Asset.findById", query = "SELECT a FROM Asset a WHERE a.id = :id")
    , @NamedQuery(name = "Asset.findByName", query = "SELECT a FROM Asset a WHERE a.name = :name")
    , @NamedQuery(name = "Asset.findByQuantity", query = "SELECT a FROM Asset a WHERE a.quantity = :quantity")
    , @NamedQuery(name = "Asset.findByIsDelete", query = "SELECT a FROM Asset a WHERE a.isDelete = :isDelete")})
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "is_delete")
    private String isDelete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asset", fetch = FetchType.LAZY)
    private List<DetailAsset> detailAssetList;
    @JoinColumn(name = "category", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;

    public Asset() {
    }

    public Asset(String id) {
        this.id = id;
    }

    public Asset(String id, String name, int quantity, String isDelete) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.isDelete = isDelete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @XmlTransient
    public List<DetailAsset> getDetailAssetList() {
        return detailAssetList;
    }

    public void setDetailAssetList(List<DetailAsset> detailAssetList) {
        this.detailAssetList = detailAssetList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AssetManagement.AssetManagement.entities.Asset[ id=" + id + " ]";
    }
    
}
