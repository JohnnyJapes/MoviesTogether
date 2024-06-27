package movie.application.moviestogether.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import movie.application.moviestogether.dao.RoleRepository;
import movie.application.moviestogether.dao.UserRepository;
import movie.application.moviestogether.validation.UserValidation;
import movie.application.moviestogether.entity.Role;
import movie.application.moviestogether.entity.User;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository uRepo;
    private RoleRepository rRepo;
	private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository uRepo, RoleRepository rRepo, BCryptPasswordEncoder passwordEncoder) {
        super();
        this.uRepo = uRepo;
        this.rRepo = rRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
	public User findByEmail(String email) {
		
		User result = uRepo.findByEmail(email);
		if (result!=null) return  result;
		else {
			System.out.println("Did not find Email - " +email);
			return result;
		}

	}

    @Override
	public void save(UserValidation data) {
		User user = new User();
		
		user.setEmail(data.getEmail());
		user.setFirstName(data.getFirstName());
		user.setLastName(data.getLastName());
		user.setPassword(passwordEncoder.encode(data.getPassword()));
		user.setEnabled(true);
		
		// give user default role of "employee"
		user.setRoles(Arrays.asList(rRepo.findRoleByName("ROLE_USER")));

		// save user in the database
		uRepo.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = uRepo.findByEmail(userName);

		if (user == null) {
			System.out.println("Invalid email or password");
			throw new UsernameNotFoundException("Invalid email or password.");
		}

		Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				authorities);
	}

	private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role tempRole : roles) {
			SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
			authorities.add(tempAuthority);
		}

		return authorities;
	}
    
}
