package com.dotridge.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.dotridge.dao.RegistrationDao;
import com.dotridge.domain.Module;
import com.dotridge.domain.ModuleToPermission;
import com.dotridge.domain.Role;
import com.dotridge.domain.UserProfile;
import com.dotridge.util.CustomPasswordEncoder;
import com.dotridge.util.LDAPUtil;

@Component
public class CutomAuthenticationProvider implements AuthenticationProvider
{

	
	@Autowired
	private RegistrationDao regDao; 
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException 
	{
		System.out.println("i am in authentication provider class");
		String userName = authentication.getName();
		String password = (String) authentication.getCredentials();
		System.out.println("UserName : " + userName + " Password : " + password);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		try {
			String userDn = LDAPUtil.getUid(userName);
			System.out.println("user dn is:\t"+userDn);
			boolean isAuthenticated = LDAPUtil.getLdapUserConnection(userDn, password);
			System.out.println("is user authenticated:\t"+isAuthenticated);
			if(isAuthenticated){
				String role = LDAPUtil.getGroupByUserId(userName);
				System.out.println("role is:\t"+role);
				GrantedAuthority roleGrant = new SimpleGrantedAuthority(role);
				authorities.add(roleGrant);
				
				String perm = LDAPUtil.getPermissionByUserId(userName);
				System.out.println("permission is:\t"+perm);
				if(perm != null && !perm.isEmpty()){
					String[] permArray = perm.split(",");
					for(String per : permArray){
						GrantedAuthority permGrant = new SimpleGrantedAuthority(per);
						authorities.add(permGrant);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		/*password = CustomPasswordEncoder.encodePassword(password);
		
		UserProfile up = regDao.getUserByName(userName, password);
		
		Role role = up.getRole();
		
		System.out.println("role found is:\t"+role.getRoleName());
		
		GrantedAuthority roleGrant = new SimpleGrantedAuthority(role.getRoleName());
		authorities.add(roleGrant);
		
		List<Module> modList = regDao.getAllModulesOfRole(role.getRoleId());
		
		if(modList != null && !modList.isEmpty()){
			for(Module mod : modList){
				System.out.println("module found is:\t"+mod.getModuleName());
				GrantedAuthority modGrant = new SimpleGrantedAuthority(mod.getModuleName());
				authorities.add(modGrant);
				List<ModuleToPermission> mtpList = mod.getModuleToPermission();
				for(ModuleToPermission mtp : mtpList){
					//permList.add(mtp.getPermission());
					System.out.println("permission found is:\t"+mtp.getPermission().getPermissionType());
					authorities.add(new SimpleGrantedAuthority(mtp.getPermission().getPermissionType()));
				}
			}
		}*/
		
		return new UsernamePasswordAuthenticationToken(userName, password, authorities);
	}

	public boolean supports(Class<?> authentication) 
	{
		return true;
	}

}
