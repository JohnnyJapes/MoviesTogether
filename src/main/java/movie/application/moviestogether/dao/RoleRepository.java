package movie.application.moviestogether.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.application.moviestogether.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findRoleByName(String theRoleName);

}