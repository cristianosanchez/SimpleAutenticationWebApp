SimpleAutenticationWebApp
=========================

Projeto inicial para construção de uma POC que irá utilizar basic HTTP authentication com transferência de parâmetros codificados em BASE 64.

A implementação permite a requisição seja feita de duas formas, através de um Servlet ou através do JSF.

=== Segurança

# Realm

A aplicação irá solicitar a autenticação dos usuários sempre que uma requisição for invocada para "/protected/*" conforme indicado no arquivo web.xml:

<web-resource-collection>
    <web-resource-name>AuthenticationCollection</web-resource-name>
    <url-pattern>/protected/*</url-pattern>
</web-resource-collection>

Os usuários serão mapeados dentro da aplicação através de um realm chamado "file". 

<login-config>
    <auth-method>BASIC</auth-method>
    <realm-name>file</realm-name>
</login-config>
	
Este é o realm default do Glassfish (3.1.2) onde os usuários podem ser gerenciados através do Console Administrativo (http://localhost:4848), via menu Configurations > default-config > Security > Realms > file e clicando no botão Manage Users. Para outros containers (Tomcat, Weblogic, verificar a respectiva documentação).

As roles utilizadas são "public" e "admin", cujos mapeamentos se fazem necessário tanto na aplicação via web.xml quanto no deploy, neste caso com a ajuda do arquivo proprietário glassfish-web.xml.

web.xml

<security-role>
    <role-name>public</role-name>
</security-role>
<security-role>
    <role-name>admin</role-name>
</security-role>
	
glassfish-web.xml

<security-role-mapping>
    <role-name>public</role-name>
    <group-name>public</group-name>
</security-role-mapping>
<security-role-mapping>
    <role-name>admin</role-name>
    <group-name>admin</group-name>
</security-role-mapping>

=== Filtro de autenticação

Uma forma de realizar manualmente a autenticação, quando não for possível mapear um realm e não seja desejado criar um Custom Realm de autenticação utilizando a API do JAAS, é através da implementação de um filtro, via javax.servlet.Filter. Verifique a classe br.com.demo.web.AuthenticationFilter para mais detalhes.






