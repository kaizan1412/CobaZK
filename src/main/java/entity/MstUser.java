package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import component.BaseEntity;
import entity.pk.MstUserPk;

@Entity
@Table(name="mst_user")
@IdClass(value=MstUserPk.class)
@NamedQueries({
	@NamedQuery(name="MstUser.findById", query="SELECT p FROM MstUser p WHERE p.id = :id")
})
public class MstUser extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5070573180666120122L;

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
//	@Override
	public void setId(String id) {
		this.id = id;
	}

//	@Override
	public String getId() {
		return this.id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "MstUser [id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}

}
