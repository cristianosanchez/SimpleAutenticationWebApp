package br.com.demo.realm;

import com.sun.appserv.security.AppservRealm;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.IASRealm;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchRealmException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

public class CustomRealm extends AppservRealm {

    private static final String PARAM_JAAS_CONTEXT = "jaas-context";

    public CustomRealm() {}

    /*public void init(Properties properties)
            throws BadRealmException, NoSuchRealmException {
        log("Init CustomRealm");

        String propJaasContext = properties.getProperty(PARAM_JAAS_CONTEXT);
        if (propJaasContext != null) {
            setProperty(PARAM_JAAS_CONTEXT, propJaasContext);
        }
    }*/

    @Override
    protected void init(Properties props) throws BadRealmException,
            NoSuchRealmException {
        String jaasCtx = props.getProperty(IASRealm.JAAS_CONTEXT_PARAM);
        this.setProperty(IASRealm.JAAS_CONTEXT_PARAM, jaasCtx);
        if (jaasCtx == null) {
            String msg = sm.getString("<my realm name>.badconfig", jaasCtx);
            throw new BadRealmException(msg);
        }
    }

   
    public Enumeration getGroupNames(String user)
            throws InvalidOperationException, NoSuchUserException {
        log("Init getGroupNames");
        Vector vector = new Vector();
        vector.add(UserRepository.getRoles().get(user));
        return vector.elements();
    }

    private void log(String s) {
        System.out.println((new StringBuilder()).append("CustomRealm").
                append(s).toString());
    }

    @Override
    public String getAuthType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
  
}
