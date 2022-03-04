package com.example.demo.entities;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_login", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"user_id"
		})
})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_user_role", 
	          joinColumns = @JoinColumn(name = "user_id"),
	          inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	private boolean mfaEnabled; // flag to indicate of mfa is active for profile
    private String secret; // secret store for the profile, this will be used during the login process.
	
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "user_info_id", nullable = false)
	private UserInfo userInfo;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

	
	
	
	
	
	

}
