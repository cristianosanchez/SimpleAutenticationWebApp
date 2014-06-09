package br.com.demo.realm;

import com.sun.appserv.security.AppservPasswordLoginModule;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
 
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;

public class CustomLoginModule extends AppservPasswordLoginModule {

     public CustomLoginModule() {
        log("CustomLoginModule: Initialization");
    }

    @Override
    protected void authenticateUser() throws LoginException {
        log((new StringBuilder()).append("username:").append(_username).append("password:").append(_password).append("currentrealm:").append(_currentRealm).toString());

        if (!(_currentRealm instanceof CustomRealm)) {
            try {
                throw new LoginException(" ");
            } catch (LoginException ex) {
                Logger.getLogger(CustomLoginModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        CustomRealm samplerealm = (CustomRealm) _currentRealm;
        if (!authenticate(_username, _password)) {
            throw new LoginException((new StringBuilder()).append("customrealm:Login Failed for user  ").append(_username).toString());
        }

        log((new StringBuilder()).append("login succeeded for  ").append(_username).toString());

        Enumeration enumeration = null;
        
        String authenticatedGroups[] = new String[2];
        try {
            enumeration = samplerealm.getGroupNames(_username);
        } catch (InvalidOperationException invalidoperationexception) {
            try {
                throw new LoginException((new StringBuilder()).append("An InvalidOperationException was thrown " + "   while calling getGroupNames() on the realm ").append(invalidoperationexception).toString());
            } catch (LoginException ex) {
                Logger.getLogger(CustomLoginModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchUserException nosuchuserexception) {
            try {
                throw new LoginException((new StringBuilder()).append("A NoSuchUserException was thrown " + "   while calling getGroupNames() on the realm ").append(nosuchuserexception).toString());
            } catch (LoginException ex) {
                Logger.getLogger(CustomLoginModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int i = 0; enumeration != null && enumeration.hasMoreElements(); i++) {
            authenticatedGroups[i] = (String) enumeration.nextElement();
            commitUserAuthentication(authenticatedGroups);
        }
    }

    private boolean authenticate(String username, String password) {
        log((new StringBuilder()).append("=>Application User=").append(username).append(";Application Pwd=").append(password).toString());

        //Check if user exists
        if (!UserRepository.getUsers().containsKey(username)) {
            log((new StringBuilder()).append("No Such User,").append(username).toString());
            return false;
        }

        String configuredPassword = (String) UserRepository.getUsers().get(username);
        if (configuredPassword.equals(password)) {
            log((new StringBuilder()).append("User ").append(username).append("authenticated").toString());
        } else {
            log((new StringBuilder()).append("realm: Login Failed for ").append(username).append(". Wrong Password!").toString());
            return false;
        }
        return true;
    }

    private void log(String s) {
        System.out.println((new StringBuilder()).append("CustomLoginModule").
                append(s).toString());
    }
}
