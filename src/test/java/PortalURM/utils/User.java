package PortalURM.utils;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class User {
	private JSONObject user;
	private JSONObject role;
	private JSONArray roleArray;
	private JSONArray userArray;
	
	public JSONArray getUsers() {
		initializeUser();
		return userArray;
	}
	
	public JSONArray getRoles() {
		initializeRoles();
		return roleArray;
	}
	
	private void initializeUser() {		
		userArray = new JSONArray();
		
		// 0.case for role-create-success and create-user-success 
		user = new JSONObject();	
		user.put("subscriber", "CDOT");
		user.put("name", "JASWANT MEENA");
		user.put("company", "C-Dot");
		user.put("phone", "9611234522");
		user.put("emailId", "jmeena@cdot.in");
		user.put("role", "SRE");
		user.put("webMailPassword", "csaJaswant.13");
		userArray.add(user);
		
	
		
		// 1. case for role-create-failed (Error: same privileges for given role) and create-user-failed (role not found)
		user = new JSONObject();	
		user.put("subscriber", "CDOT");
		user.put("name", "SACHIN JAIN");
		user.put("company", "c-dot");
		user.put("phone", "9611234522");
		user.put("emailId", "sachin@cdot.in");
		user.put("role", "Team_Leader");
		user.put("webMailPassword", "cdot@123");
		userArray.add(user);
		
		// 2. Twitter user with twitter_admin_role and twitter emailId : Success case 
		user = new JSONObject();	
		user.put("subscriber", "CDOT");
		user.put("name", "RAHUL");
		user.put("company", "C-DOT");
		user.put("phone", "9611234522");
		user.put("emailId", "krahul@cdot.in");
		user.put("role", "Research_Enginner");
		user.put("webMailPassword", "cdot@123");
		userArray.add(user);
		
		// 3. new 
		user = new JSONObject();	
		user.put("subscriber", "CDOT");
		user.put("name", "CISCOAdmin");
		user.put("company", "CISCO");
		user.put("phone", "9611234522");
		user.put("emailId", "jagan@cdot.in");//
		user.put("role", "ResourceTreeViewer");
		user.put("webMailPassword", "cdot@123");
		userArray.add(user);
		
		//4. 
		user = new JSONObject();	
		user.put("subscriber", "CDOT");
		user.put("name", "Prateek");
		user.put("company", "C-DOT");
		user.put("phone", "9611234522");
		user.put("emailId", "prateekv@cdot.in");//admin@cisco.in
		user.put("role", "RE2");
		user.put("webMailPassword", "cdot@123");
		userArray.add(user);
	

	}
		
	private void initializeRoles() {	
		//0.	user.put("name", "CDOT_ADMIN");
		roleArray = new JSONArray();
		List<String> privileges = new ArrayList<String>();
		role = new JSONObject();				
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[2]/div");//SSM
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[3]/td[2]/table/tbody/tr[1]/td[2]/div");//URM
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[6]/td[2]/table/tbody/tr[1]/td[2]/div");//RTV
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[4]/td[2]/table/tbody/tr[1]/td[2]/div");// CCSPREPORT		
		role.put("xpath", privileges);	
		role.put("name", "SRE");
		roleArray.add(role);
		
		//1.user.put("name", "CdacAdmin");
		privileges = new ArrayList<String>();
		role = new JSONObject();		
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[2]/div");//SSM
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[3]/td[2]/table/tbody/tr[1]/td[2]/div");//URM
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[6]/td[2]/table/tbody/tr[1]/td[2]/div");//RTV
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[7]/td[2]/table/tbody/tr[1]/td[2]/div");// CCSPREPORT	
		role.put("xpath", privileges);	
		role.put("name", "Team_Leader");
		roleArray.add(role);
		
		//2.	user.put("name", "TwitterAdmin");
		privileges = new ArrayList<String>();
		role = new JSONObject();	
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[1]/td[2]/table/tbody/tr[1]/td[2]/div");// SSM-Node Topology- view node topology
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[3]/td[2]/table/tbody/tr[1]/td[2]/div");//URM
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[6]/td[2]/table/tbody/tr[1]/td[2]/div");//RTV
		role.put("xpath", privileges);	
		role.put("name", "Research_Enginner");
		roleArray.add(role);
		
		//3.	user.put("name", "CISCOAdmin");
		privileges = new ArrayList<String>();
		role = new JSONObject();	
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[6]/td[2]/table/tbody/tr[1]/td[2]/div");
		
		role.put("xpath", privileges);	
		role.put("name", "ResourceTreeViewer");
		roleArray.add(role);
		
		//4. 	user.put("name", "AdminFacebook");
		privileges = new ArrayList<String>();
		role = new JSONObject();		
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[2]/div");// SSM-Node Topology- view node topology
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[3]/td[2]/table/tbody/tr[1]/td[2]/div");//URM
		privileges.add("//*[@id='treeId']/div/table/tbody/tr[4]/td[2]/table/tbody/tr[1]/td[2]/div");//RTV
		role.put("xpath", privileges);	
		role.put("name", "RE2");
		roleArray.add(role);
		
	}
}
