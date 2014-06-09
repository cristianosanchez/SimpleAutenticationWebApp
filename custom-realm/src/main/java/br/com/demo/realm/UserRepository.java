package br.com.demo.realm;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private static Map<String, String> userPwdTable;
	private static Map<String, String> userRolesTable;

    static {
        userPwdTable = new HashMap<String, String>();
        userRolesTable = new HashMap<String, String>();

        userPwdTable.put("Anita", "123");
        userRolesTable.put("Anita", "admin");
		
		userPwdTable.put("Shakira", "123");
        userRolesTable.put("Shakira", "guest");
    }

    public static Map<String, String> getUsers() {
        return userPwdTable;
    }

    public static Map<String, String> getRoles() {
        return userRolesTable;
    }

}
