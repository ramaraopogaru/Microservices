package com.dotridge.util;

import java.util.Base64;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.dotridge.bean.UserProfileBean;
import com.dotridge.domain.UserProfile;

public class LDAPUtil 
{
	private static DirContext ldapContext = null;
	
	public static void main(String[] args) 
	{
		try 
		{
		 /*  authenticateUser("Jack", "testUser@123");
			String loginToken = getLoginTokenUserId("Jack");
			System.out.println("loginToken is:\t"+loginToken);
		
		UserProfileBean upb = getUserByUserId("Jack");
		System.out.println(upb);*/
			
			createGroup("admin");
			//createPermission("delete");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DirContext getLdapConnection()
	{
		if(ldapContext == null)
		{
			 try
		     {
			     String url = "ldap://localhost:1389";
			     String rootUser  = "cn=Directory Manager";
			     String password = "Adance123$";
			       
			     Hashtable<String, String> environment = new Hashtable<String, String>();
			 
			     environment.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
			     environment.put(Context.PROVIDER_URL,url);         
			     environment.put(Context.SECURITY_PRINCIPAL,rootUser);
			     environment.put(Context.SECURITY_CREDENTIALS, password);
			 
			     ldapContext = new InitialDirContext(environment);
			 
			     System.out.println("admin Bind successful");
		     }
		     catch(Exception exception)
		     {
		    	 exception.printStackTrace();
		     }
		}
		return ldapContext;
	}
	
	public static boolean createGroup(String groupName)
	{
		try {
			Attributes atbs = new BasicAttributes();
			// Attribute atbUid = new BasicAttribute("uid","1122");
			Attribute atbCn = new BasicAttribute("cn", "permissions");
			Attribute atbSn = new BasicAttribute("sn", "permissions");
			// Attribute atbMail = new
			// BasicAttribute("mail","java2db@mail.com");
			// Attribute atbPassword = new
			// BasicAttribute("userPassword","password");
			Attribute atbObjClass = new BasicAttribute("objectClass", "inetOrgPerson");

			// atbs.put(atbUid);
			atbs.put(atbCn);
			atbs.put(atbSn);
			// atbs.put(atbMail);
			// atbs.put(atbPassword);
			atbs.put(atbObjClass);

			ldapContext = getLdapConnection();
			ldapContext.createSubcontext("ou=" + groupName + "," + getBaseDn() + "", atbs);
			System.out.println("Entry Added successfully");
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
	
	public static String getGroupByUserId(String userId)
	{
		ldapContext = getLdapConnection();
		String filter = "(uid=" + userId + ")";
		SearchControls ctrl = new SearchControls();
		ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String userDn = getUid(userId);
		try 
		{
			NamingEnumeration answer = ldapContext.search(getBaseDn(), filter, ctrl);
			String dn;
			if (answer.hasMore()) {
				SearchResult result = (SearchResult) answer.next();
				Attribute atb = result.getAttributes().get("ou");
				dn = atb.get().toString();
			} else {
				dn = null;
			}
			answer.close();
			return dn;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		/*SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		NamingEnumeration results2 = ldapContext.search("dc=novelhealth,dc=com", "(uid = " + userId+ ")",searchControls);
		
		int entryCount = 0;
		while(results2.hasMore())
		{
			SearchResult searchRes = (SearchResult)results2.next();
			Attributes atbs = searchRes.getAttributes();
			Attribute atb = atbs.get("uid");
			//This way we can select all attributes from the entry
			String userId2 = null;
			userId2 = (atb == null)?"Id is null":(String)atb.get();
			entryCount = entryCount+1;
			System.out.println("User Id "+userId);
		}
		 
		System.out.println("Total selected entries are : "+entryCount);
		return "group";*/
	}
	
	public static String getLoginTokenUserId(String userId)
	{
		ldapContext = getLdapConnection();
		String filter = "(uid=" + userId + ")";
		SearchControls ctrl = new SearchControls();
		ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String userDn = getUid(userId);
		try 
		{
			NamingEnumeration answer = ldapContext.search(getBaseDn(), filter, ctrl);
			String dn;
			if (answer.hasMore()) {
				SearchResult result = (SearchResult) answer.next();
				Attribute atb = result.getAttributes().get("secretary");
				dn = atb.get().toString();
			} else {
				dn = null;
			}
			answer.close();
			return dn;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		/*SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		NamingEnumeration results2 = ldapContext.search("dc=novelhealth,dc=com", "(uid = " + userId+ ")",searchControls);
		
		int entryCount = 0;
		while(results2.hasMore())
		{
			SearchResult searchRes = (SearchResult)results2.next();
			Attributes atbs = searchRes.getAttributes();
			Attribute atb = atbs.get("uid");
			//This way we can select all attributes from the entry
			String userId2 = null;
			userId2 = (atb == null)?"Id is null":(String)atb.get();
			entryCount = entryCount+1;
			System.out.println("User Id "+userId);
		}
		 
		System.out.println("Total selected entries are : "+entryCount);
		return "group";*/
	}
	
	private static String getBaseDn()
	{
		return "dc=dotridge,dc=com";
	}
	
	public static UserProfile createUser(UserProfile userProfile, String groupName)
	{
			try
			{
			   Attributes atbs = new  BasicAttributes();
			   BasicAttribute ocattr = new BasicAttribute("objectclass");
			      /*ocattr.add("top");
			      ocattr.add("person");*/
			      ocattr.add("inetOrgPerson");
			      ocattr.add("organizationalRole");
			   atbs.put(ocattr);
			   Attribute atbUid = new BasicAttribute("uid", userProfile.getUserId());
			   Attribute atbCn = new BasicAttribute("cn", userProfile.getFirstName());
			   Attribute atbSn= new BasicAttribute("sn", userProfile.getLastName());
			   Attribute atbMail = new BasicAttribute("mail", userProfile.getEmail());
			   Attribute atbPassword = new BasicAttribute("userPassword", userProfile.getPassword());
			   Attribute atbMobile = new BasicAttribute("mobile", String.valueOf(userProfile.getPhoneNumber()));
			   Attribute atbLoginToken = new BasicAttribute("secretary", userProfile.getLoginToken());
			   //Attribute atbRole = new BasicAttribute("roleOccupant", userProfile.getRole().getRoleName());
			   Attribute atbGroup = new BasicAttribute("ou", groupName);
			 //  Attribute atbObjClass = new BasicAttribute("objectClass","inetOrgPerson");
			  // Attribute roleOrgObjClass = new BasicAttribute("objectClass","organizationalRole");
			 
			atbs.put(atbUid);
			atbs.put(atbCn);
			atbs.put(atbSn);
			atbs.put(atbMail);
			atbs.put(atbPassword);
			atbs.put(atbMobile);
			atbs.put(atbLoginToken);
			atbs.put(atbGroup);
			//atbs.put(atbObjClass);
			//atbs.put(atbRole);
			//atbs.put(roleOrgObjClass);

			ldapContext = getLdapConnection();
			// String role = userProfile.getRole().getRoleName();
			String userId = userProfile.getUserId();
			String userDn = "uId = " + userId + "," + " ou = " + groupName + ", " + getBaseDn();
			ldapContext.createSubcontext(userDn, atbs);
			System.out.println(userDn);
			System.out.println("User Added successfully");
			if(userDn !=null && groupName.equals("doctor")) {
				assignPermissionsToUser(userProfile.getUserId(), new String[] {"create","view","edit"});
			}else if(userDn !=null && groupName.equals("patient")) {
				assignPermissionsToUser(userProfile.getUserId(), new String[] {"view"});
			}else if(userDn !=null && groupName.equals("admin")) {
				assignPermissionsToUser(userProfile.getUserId(), new String[] {"create","view","edit","delete","search"});
			}
			return userProfile;
			}
			catch(Exception exception)
			{
				exception.printStackTrace();  
			}
		return null;
	}
	
	public static String getUid(String user)
	{
		ldapContext = getLdapConnection();
		String filter = "(uid=" + user + ")";
		SearchControls ctrl = new SearchControls();
		ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
		NamingEnumeration answer;
		try 
		{
			System.out.println("base dn is:\t"+getBaseDn());
			answer = ldapContext.search(getBaseDn(), filter, ctrl);
			System.out.println("answer is:\t"+answer);

			String dn;
			if (answer.hasMore()) {
				SearchResult result = (SearchResult) answer.next();
				dn = result.getNameInNamespace();
			} else {
				dn = null;
			}
			answer.close();
			return dn;
		}
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean authenticateUser(String userId, String password)
	{
		try 
		{
			String user = getUid(userId);
			boolean flag = getLdapUserConnection(user, password);
			return flag;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean getLdapUserConnection(String userDn , String password)
	{
		try 
		{
			String url = "ldap://localhost:1389";

			Hashtable<String, String> environment = new Hashtable<String, String>();

			environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			environment.put(Context.PROVIDER_URL, url);
			environment.put(Context.SECURITY_PRINCIPAL, userDn);
			environment.put(Context.SECURITY_CREDENTIALS, password);

			DirContext userLdapContext = new InitialDirContext(environment);
			//ldapContext = new InitialDirContext(environment);
            if(userLdapContext != null){
            	System.out.println("User authentication successful");
            	//userLdapContext.unbind(userDn);
            	return true;
            }
			
			return false;
		} 
		catch (Exception exception) 
		{
			exception.printStackTrace();
		}
		return false;
	}
	
	public static boolean createPermission(String permissionName)
	{
		try
		{
		   Attributes atbs = new  BasicAttributes();
		   
		   Attribute atbCn = new BasicAttribute("cn", permissionName);
		   Attribute atbSn= new BasicAttribute("sn", permissionName);
		   Attribute atbGroup = new BasicAttribute("ou", "permissions");
		   Attribute atbObjClass = new BasicAttribute("objectClass","inetOrgPerson");
		 
		   atbs.put(atbCn);
		   atbs.put(atbSn);
		   atbs.put(atbGroup);
		   atbs.put(atbObjClass);
		   
		   ldapContext = getLdapConnection();
		   		String permissionDn = "uId = " + permissionName + "," + " ou =  permissions" + ", " + getBaseDn();
		      ldapContext.createSubcontext(permissionDn, atbs); 
		      System.out.println(permissionDn);
		      System.out.println("User Added successfully");
		      return true;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();  
		}
		return false;
	}
	
	public static boolean assignPermissionsToUser(String userName, String[] permissions)
	{
		try 
		{
			ldapContext = getLdapConnection();
			String userDn = getUid(userName);
			
			Attributes atbs = new BasicAttributes();
			Attribute atb = new BasicAttribute("roleOccupant", getStringFromStringArray(permissions));
			atbs.put(atb);
			
			ldapContext.modifyAttributes(userDn, DirContext.REPLACE_ATTRIBUTE, atbs);
			System.out.println("update successful");
			return true;
		} 
		catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
	
	private static String getStringFromStringArray(String[] permissions)
	{
		StringBuffer sb = new StringBuffer();
		
		for(String st : permissions)
		{
			sb.append(st);
			sb.append(",");
		}
		String perm = sb.toString();
		perm = perm.substring(0, perm.length()-1);
		return perm;
	}
	
	public static String getPermissionByUserId(String userId)
	{
		ldapContext = getLdapConnection();
		String filter = "(uid=" + userId + ")";
		SearchControls ctrl = new SearchControls();
		ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String userDn = getUid(userId);
		try 
		{
			NamingEnumeration answer = ldapContext.search(getBaseDn(), filter, ctrl);
			String dn;
			if (answer.hasMore()) {
				SearchResult result = (SearchResult) answer.next();
				Attribute atb = result.getAttributes().get("roleOccupant");
				dn = atb.get().toString();
			} else {
				dn = null;
			}
			answer.close();
			return dn;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static UserProfileBean getUserByUserId(String userId)
	{
		ldapContext = getLdapConnection();
		String filter = "(uid=" + userId + ")";
		SearchControls ctrl = new SearchControls();
		ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String userDn = getUid(userId);
		try 
		{
			NamingEnumeration answer = ldapContext.search(getBaseDn(), filter, ctrl);
			String dn;
			if (answer.hasMore()) {
				UserProfileBean upb = new UserProfileBean();
				SearchResult result = (SearchResult) answer.next();
				Attribute roleOccupant = result.getAttributes().get("roleOccupant");
				if(roleOccupant != null)
				upb.setRole(roleOccupant.get().toString());
				
				Attribute uId = result.getAttributes().get("uid");
				upb.setUserId(uId.get().toString());
				
								
				Attribute firstname = result.getAttributes().get("cn");
				upb.setFirstName(firstname.get().toString());
				
				Attribute lastName = result.getAttributes().get("sn");
				upb.setLastName(lastName.get().toString());
				
				Attribute mail = result.getAttributes().get("mail");
				upb.setEmail(mail.get().toString());
				
				Attribute mobile = result.getAttributes().get("mobile");
				upb.setPhoneNumber(Long.valueOf(mobile.get().toString()));
				return upb;
			} else {
				dn = null;
			}
			answer.close();
			return null;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
