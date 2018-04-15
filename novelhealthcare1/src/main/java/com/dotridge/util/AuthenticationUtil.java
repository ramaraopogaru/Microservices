package com.dotridge.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthenticationUtil {

	public static Authentication getAuthorizationToken(String userName,String userpassword) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
			String role = LDAPUtil.getGroupByUserId(userName);
			GrantedAuthority roleGrant = new SimpleGrantedAuthority(role);
			authorities.add(roleGrant);

			String perm = LDAPUtil.getPermissionByUserId(userName);
			if (perm != null && !perm.isEmpty()) {
				String[] permArray = perm.split(",");
				for (String per : permArray) {
					GrantedAuthority permGrant = new SimpleGrantedAuthority(per);
					authorities.add(permGrant);
				}
			}

		} catch (Exception e) {
          e.printStackTrace();
		}
		return new UsernamePasswordAuthenticationToken(userName, userpassword, authorities);
	}

}
