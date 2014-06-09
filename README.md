SimpleAutenticationWebApp
=========================

Projeto inicial para construção de uma POC que irá utilizar basic HTTP authentication


Configure the following security resources into your web container: 

# Realm

The app will authenticate against a ream named DatabaseUser. This is the default Tomcat (7) realm, where the users can be managed at <tomcat_base>/conf/tomcat-users.xml

# Users

The users and roles which the app will look for are (Tomcat 7 based):

<role rolename="public"/>
<role rolename="admin"/>
<user username="public" password="public" roles="public"/>
<user username="admin" password="admin" roles="public,admin"/>