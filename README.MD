# Simple Demo for Basic Authentication

This is a simple demo app configured to authenticate using Java EE 5 features and simple file from Tomcat 7 as realm.

#### Realm and User for HTTP Basic Authentication

1. Create the real DatabaseUser into container. This is the default realm from Tomcat 7, users from file conf/tomcat-users.xml

2. The folowing roles needs to be added to container DatabaseUser realm:

<role rolename="public"/>
<role rolename="admin"/>
<user username="public" password="public" roles="public"/>
<user username="admin" password="admin" roles="public,admin"/>