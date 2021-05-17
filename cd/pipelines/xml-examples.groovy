pipeline 'XML Examples', {
  description = ''
  disableMultipleActiveRuns = '0'
  disableRestart = '0'
  enabled = '1'
  overrideWorkspace = '0'
  projectName = 'pcherry'
  skipStageMode = 'ENABLED'

  formalParameter 'ec_stagesToRun', {
    expansionDeferred = '1'
    required = '0'
  }

  stage 'XMLEdit Plugin', {
    description = ''
    colorCode = '#289ce1'
    completionType = 'auto'
    pipelineName = 'XML Examples'
    waitForPlannedStartDate = '0'

    gate 'PRE', {
      }

    gate 'POST', {
      }

    task 'Create Example File', {
      description = ''
      actualParameter = [
        'AddNewLine': '0',
        'Append': '0',
        'Content': '''<?xml version="1.0" encoding="UTF-8"?>

<bookstore>

<book category="cooking">
  <title lang="en">Everyday Italian</title>
  <author>Giada De Laurentiis</author>
  <year>2005</year>
  <price>30.00</price>
</book>

<book category="children">
  <title lang="en">Harry Potter</title>
  <author>J K. Rowling</author>
  <year>2005</year>
  <price>29.99</price>
</book>

<book category="web">
  <title lang="en">XQuery Kick Start</title>
  <author>James McGovern</author>
  <author>Per Bothner</author>
  <author>Kurt Cagle</author>
  <author>James Linn</author>
  <author>Vaidyanathan Nagarajan</author>
  <year>2003</year>
  <price>49.99</price>
</book>

<book category="web">
  <title lang="en">Learning XML</title>
  <author>Erik T. Ray</author>
  <year>2003</year>
  <price>39.95</price>
</book>

</bookstore>''',
        'Path': '/tmp/bookstore-example.xml',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-FileOps'
      subprocedure = 'AddTextToFile'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Read Element', {
      description = ''
      actualParameter = [
        'element_outpp': '/myJob/result/bookTitle',
        'element_outpsp': '',
        'filepath': '/tmp/bookstore-example.xml',
        'property_path': '',
        'read_query': '/bookstore/book[@category="children"]/title',
        'return_type': 'value',
        'selection': 'first',
        'source': 'filepath',
        'xml_code': '',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-XMLEdit'
      subprocedure = 'ReadElement'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Read Attrtibute', {
      description = ''
      actualParameter = [
        'attribute': 'lang',
        'attribute_outpp': '/myJob/result/bookLanguage',
        'attribute_outpsp': '',
        'filepath': '/tmp/bookstore-example.xml',
        'property_path': '',
        'read_query': '/bookstore/book[@category="children"]/title',
        'return_type': 'value',
        'selection': 'first',
        'source': 'filepath',
        'xml_code': '',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-XMLEdit'
      subprocedure = 'ReadAttribute'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Update Element', {
      description = ''
      actualParameter = [
        'element_new_value': 'Draco Malfoy',
        'filepath': '/tmp/bookstore-example.xml',
        'property_path': '',
        'read_query': '/bookstore/book[@category="children"]/title',
        'selection': 'first',
        'source': 'filepath',
        'xml_code': '',
        'xml_outpp': '',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-XMLEdit'
      subprocedure = 'UpdateElement'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Update Attribute', {
      description = ''
      actualParameter = [
        'attribute_name': 'lang',
        'attribute_value': 'us',
        'filepath': '/tmp/bookstore-example.xml',
        'property_path': '',
        'read_query': '/bookstore/book[@category="cooking"]/title',
        'selection': 'first',
        'source': 'filepath',
        'xml_code': '',
        'xml_outpp': '',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-XMLEdit'
      subprocedure = 'UpdateAttribute'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Extract Element', {
      description = ''
      actualParameter = [
        'element_outpp': '/myJob/result/bookTitle',
        'element_outpsp': '',
        'filepath': '/tmp/bookstore-example.xml',
        'property_path': '',
        'read_query': '/bookstore/book[@category="web" and title="XQuery Kick Start"]/author[text()="Per Bothner"]',
        'return_type': 'value',
        'selection': 'first',
        'source': 'filepath',
        'xml_code': '',
        'xml_outpp': '',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-XMLEdit'
      subprocedure = 'ExtractElement'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Extract Attribute', {
      description = ''
      actualParameter = [
        'attribute': 'lang',
        'attribute_outpp': '/myJob/result/bookLanguage',
        'attribute_outpsp': '',
        'filepath': '/tmp/bookstore-example.xml',
        'property_path': '',
        'read_query': '/bookstore/book[@category="web" and title="Learning XML"]/title',
        'return_type': 'value',
        'selection': 'first',
        'source': 'filepath',
        'xml_code': '',
        'xml_outpp': '',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-XMLEdit'
      subprocedure = 'ExtractAttribute'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'XPathQuery', {
      description = ''
      actualParameter = [
        'filepath': '/tmp/bookstore-example.xml',
        'property_path': '',
        'source': 'filepath',
        'xml_code': '',
        'xpath_query': '//bookstore/book[@category="web" and title="XQuery Kick Start"]/author',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-XMLEdit'
      subprocedure = 'XPathQuery'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Delete Element', {
      description = ''
      actualParameter = [
        'filepath': '/tmp/bookstore-example.xml',
        'property_path': '',
        'read_query': '//bookstore/book[@category="web" and title="XQuery Kick Start"]/author[text()="James Linn"]',
        'selection': 'first',
        'source': 'filepath',
        'xml_code': '',
        'xml_outpp': '',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-XMLEdit'
      subprocedure = 'DeleteElement'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Delete Attribute', {
      description = ''
      actualParameter = [
        'attribute_name': 'lang',
        'filepath': '/tmp/bookstore-example.xml',
        'property_path': '',
        'read_query': '/bookstore/book[@category="cooking"]/title',
        'selection': 'first',
        'source': 'filepath',
        'xml_code': '',
        'xml_outpp': '',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-XMLEdit'
      subprocedure = 'DeleteAttribute'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Insert Element', {
      description = ''
      actualParameter = [
        'element': 'Phil Cherry',
        'element_tag': 'author',
        'filepath': '/tmp/bookstore-example.xml',
        'property_path': '',
        'read_query': '/bookstore/book[@category="web" and title="XQuery Kick Start"]',
        'selection': 'last',
        'source': 'filepath',
        'xml_code': '',
        'xml_outpp': '',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-XMLEdit'
      subprocedure = 'InsertElement'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Insert Attribute', {
      description = ''
      actualParameter = [
        'attribute_name': 'importance',
        'attribute_value': 'high',
        'filepath': '/tmp/bookstore-example.xml',
        'property_path': '',
        'read_query': '/bookstore/book[@category="web" and title="XQuery Kick Start"]',
        'selection': 'first',
        'source': 'filepath',
        'xml_code': '',
        'xml_outpp': '',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-XMLEdit'
      subprocedure = 'InsertAttribute'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Show XML Content', {
      description = ''
      actualParameter = [
        'commandToRun': 'cat /tmp/bookstore-example.xml',
        'shellToUse': 'bash',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }
  }

  stage 'Without Plugin', {
    description = ''
    colorCode = '#ff7f0e'
    completionType = 'auto'
    pipelineName = 'XML Examples'
    waitForPlannedStartDate = '0'

    gate 'PRE', {
      }

    gate 'POST', {
      }

    task 'Create Example File', {
      description = ''
      actualParameter = [
        'AddNewLine': '0',
        'Append': '0',
        'Content': '''<?xml version=\'1.0\' encoding=\'UTF-8\'?>

<server xmlns="urn:jboss:domain:1.2">

    <extensions>
        <extension module="org.jboss.as.clustering.infinispan"/>
        <extension module="org.jboss.as.configadmin"/>
        <extension module="org.jboss.as.connector"/>
        <extension module="org.jboss.as.deployment-scanner"/>
        <extension module="org.jboss.as.ee"/>
        <extension module="org.jboss.as.ejb3"/>
        <extension module="org.jboss.as.jaxrs"/>
        <extension module="org.jboss.as.jdr"/>
        <extension module="org.jboss.as.jmx"/>
        <extension module="org.jboss.as.jpa"/>
        <extension module="org.jboss.as.logging"/>
        <extension module="org.jboss.as.mail"/>
        <extension module="org.jboss.as.naming"/>
        <extension module="org.jboss.as.osgi"/>
        <extension module="org.jboss.as.pojo"/>
        <extension module="org.jboss.as.remoting"/>
        <extension module="org.jboss.as.sar"/>
        <extension module="org.jboss.as.security"/>
        <extension module="org.jboss.as.threads"/>
        <extension module="org.jboss.as.transactions"/>
        <extension module="org.jboss.as.web"/>
        <extension module="org.jboss.as.webservices"/>
        <extension module="org.jboss.as.weld"/>
    </extensions>

    <management>
        <security-realms>
            <security-realm name="ManagementRealm">
                <authentication>
                    <properties path="mgmt-users.properties" relative-to="jboss.server.config.dir"/>
                </authentication>
            </security-realm>
            <security-realm name="ApplicationRealm">
                <authentication>
                    <properties path="application-users.properties" relative-to="jboss.server.config.dir"/>
                </authentication>
            </security-realm>
        </security-realms>
        <management-interfaces>
            <native-interface security-realm="ManagementRealm">
                <socket-binding native="management-native"/>
            </native-interface>
            <http-interface security-realm="ManagementRealm">
                <socket-binding http="management-http"/>
            </http-interface>
        </management-interfaces>
    </management>

    <profile>
        <subsystem xmlns="urn:jboss:domain:logging:1.1">
            <console-handler name="CONSOLE">
                <level name="INFO"/>
                <formatter>
                    <pattern-formatter pattern="%d{HH:mm:ss,SSS} %-5p [%c] (%t) %s%E%n"/>
                </formatter>
            </console-handler>
            <periodic-rotating-file-handler name="FILE">
                <formatter>
                    <pattern-formatter pattern="%d{HH:mm:ss,SSS} %-5p [%c] (%t) %s%E%n"/>
                </formatter>
                <file relative-to="jboss.server.log.dir" path="server.log"/>
                <suffix value=".yyyy-MM-dd"/>
                <append value="true"/>
            </periodic-rotating-file-handler>
            <logger category="com.arjuna">
                <level name="WARN"/>
            </logger>
            <logger category="org.apache.tomcat.util.modeler">
                <level name="WARN"/>
            </logger>
            <logger category="sun.rmi">
                <level name="WARN"/>
            </logger>
            <logger category="jacorb">
                <level name="WARN"/>
            </logger>
            <logger category="jacorb.config">
                <level name="ERROR"/>
            </logger>
            <root-logger>
                <level name="INFO"/>
                <handlers>
                    <handler name="CONSOLE"/>
                    <handler name="FILE"/>
                </handlers>
            </root-logger>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:configadmin:1.0"/>
        <subsystem xmlns="urn:jboss:domain:datasources:1.0">
            <datasources>
                <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1</connection-url>
                    <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                        <password>sa</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                </drivers>
            </datasources>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:deployment-scanner:1.1">
            <deployment-scanner path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000" deployment-timeout="300"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:ee:1.0"/>
        <subsystem xmlns="urn:jboss:domain:ejb3:1.2">
            <session-bean>
                <stateless>
                    <bean-instance-pool-ref pool-name="slsb-strict-max-pool"/>
                </stateless>
                <stateful default-access-timeout="5000" cache-ref="simple"/>
                <singleton default-access-timeout="5000"/>
            </session-bean>
            <pools>
                <bean-instance-pools>
                    <strict-max-pool name="slsb-strict-max-pool" max-pool-size="20" instance-acquisition-timeout="5" instance-acquisition-timeout-unit="MINUTES"/>
                    <strict-max-pool name="mdb-strict-max-pool" max-pool-size="20" instance-acquisition-timeout="5" instance-acquisition-timeout-unit="MINUTES"/>
                </bean-instance-pools>
            </pools>
            <caches>
                <cache name="simple" aliases="NoPassivationCache"/>
                <cache name="passivating" passivation-store-ref="file" aliases="SimpleStatefulCache"/>
            </caches>
            <passivation-stores>
                <file-passivation-store name="file"/>
            </passivation-stores>
            <async thread-pool-name="default"/>
            <timer-service thread-pool-name="default">
                <data-store path="timer-service-data" relative-to="jboss.server.data.dir"/>
            </timer-service>
            <remote connector-ref="remoting-connector" thread-pool-name="default"/>
            <thread-pools>
                <thread-pool name="default">
                    <max-threads count="10"/>
                    <keepalive-time time="100" unit="milliseconds"/>
                </thread-pool>
            </thread-pools>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:infinispan:1.2" default-cache-container="hibernate">
            <cache-container name="hibernate" default-cache="local-query">
                <local-cache name="entity">
                    <transaction mode="NON_XA"/>
                    <eviction strategy="LRU" max-entries="10000"/>
                    <expiration max-idle="100000"/>
                </local-cache>
                <local-cache name="local-query">
                    <transaction mode="NONE"/>
                    <eviction strategy="LRU" max-entries="10000"/>
                    <expiration max-idle="100000"/>
                </local-cache>
                <local-cache name="timestamps">
                    <transaction mode="NONE"/>
                    <eviction strategy="NONE"/>
                </local-cache>
            </cache-container>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:jaxrs:1.0"/>
        <subsystem xmlns="urn:jboss:domain:jca:1.1">
            <archive-validation enabled="true" fail-on-error="true" fail-on-warn="false"/>
            <bean-validation enabled="true"/>
            <default-workmanager>
                <short-running-threads>
                    <core-threads count="50"/>
                    <queue-length count="50"/>
                    <max-threads count="50"/>
                    <keepalive-time time="10" unit="seconds"/>
                </short-running-threads>
                <long-running-threads>
                    <core-threads count="50"/>
                    <queue-length count="50"/>
                    <max-threads count="50"/>
                    <keepalive-time time="10" unit="seconds"/>
                </long-running-threads>
            </default-workmanager>
            <cached-connection-manager/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:jdr:1.0"/>
        <subsystem xmlns="urn:jboss:domain:jmx:1.1">
            <show-model value="true"/>
            <remoting-connector/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:jpa:1.0">
            <jpa default-datasource=""/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:mail:1.0">
            <mail-session jndi-name="java:jboss/mail/Default">
                <smtp-server outbound-socket-binding-ref="mail-smtp"/>
            </mail-session>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:naming:1.1"/>
        <subsystem xmlns="urn:jboss:domain:osgi:1.2" activation="lazy">
            <properties>
                <property name="org.osgi.framework.startlevel.beginning">
                    1
                </property>
            </properties>
            <capabilities>
                <capability name="javax.servlet.api:v25"/>
                <capability name="javax.transaction.api"/>
                <capability name="org.apache.felix.log" startlevel="1"/>
                <capability name="org.jboss.osgi.logging" startlevel="1"/>
                <capability name="org.apache.felix.configadmin" startlevel="1"/>
                <capability name="org.jboss.as.osgi.configadmin" startlevel="1"/>
            </capabilities>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:pojo:1.0"/>
        <subsystem xmlns="urn:jboss:domain:remoting:1.1">
            <connector name="remoting-connector" socket-binding="remoting" security-realm="ApplicationRealm"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:resource-adapters:1.0"/>
        <subsystem xmlns="urn:jboss:domain:sar:1.0"/>
        <subsystem xmlns="urn:jboss:domain:security:1.1">
            <security-domains>
                <security-domain name="other" cache-type="default">
                    <authentication>
                        <login-module code="Remoting" flag="optional">
                            <module-option name="password-stacking" value="useFirstPass"/>
                        </login-module>
                        <login-module code="RealmUsersRoles" flag="required">
                            <module-option name="usersProperties" value="${jboss.server.config.dir}/application-users.properties"/>
                            <module-option name="rolesProperties" value="${jboss.server.config.dir}/application-roles.properties"/>
                            <module-option name="realm" value="ApplicationRealm"/>
                            <module-option name="password-stacking" value="useFirstPass"/>
                        </login-module>
                    </authentication>
                </security-domain>
                <security-domain name="other2" cache-type="default">
                    <authentication>
                        <login-module code="org.jboss.security.auth.spi.UsersRolesLoginModule" flag="required">
                            <module-option name="usersProperties" value="/my-users.properties"/>
                            <module-option name="rolesProperties" value="/my-user-roles.properties"/>
                        </login-module>
                    </authentication>
                </security-domain>
                <security-domain name="myApplicationName" cache-type="default">
                    <authentication>
                        <login-module code="org.jboss.security.auth.spi.UsersRolesLoginModule" flag="required">
                            <module-option name="usersProperties" value="/my-users.properties"/>
                            <module-option name="rolesProperties" value="/my-user-roles.properties"/>
                        </login-module>
                    </authentication>
                </security-domain>
                <security-domain name="jboss-web-policy" cache-type="default">
                    <authorization>
                        <policy-module code="Delegating" flag="required"/>
                    </authorization>
                </security-domain>
                <security-domain name="jboss-ejb-policy" cache-type="default">
                    <authorization>
                        <policy-module code="Delegating" flag="required"/>
                    </authorization>
                </security-domain>
            </security-domains>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:threads:1.1"/>
        <subsystem xmlns="urn:jboss:domain:transactions:1.1">
            <core-environment>
                <process-id>
                    <uuid/>
                </process-id>
            </core-environment>
            <recovery-environment socket-binding="txn-recovery-environment" status-socket-binding="txn-status-manager"/>
            <coordinator-environment default-timeout="300"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:web:1.1" default-virtual-server="default-host" native="false">
            <connector name="http" protocol="HTTP/1.1" scheme="http" socket-binding="http"/>
            <virtual-server name="default-host" enable-welcome-root="true">
                <alias name="localhost"/>
                <alias name="example.com"/>
            </virtual-server>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:webservices:1.1">
            <modify-wsdl-address>true</modify-wsdl-address>
            <wsdl-host>${jboss.bind.address:127.0.0.1}</wsdl-host>
            <endpoint-config name="Standard-Endpoint-Config"/>
            <endpoint-config name="Recording-Endpoint-Config">
                <pre-handler-chain name="recording-handlers" protocol-bindings="##SOAP11_HTTP ##SOAP11_HTTP_MTOM ##SOAP12_HTTP ##SOAP12_HTTP_MTOM">
                    <handler name="RecordingHandler" class="org.jboss.ws.common.invocation.RecordingServerHandler"/>
                </pre-handler-chain>
            </endpoint-config>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:weld:1.0"/>
    </profile>

    <interfaces>
        <interface name="management">
            <inet-address value="0.0.0.0"/>
        </interface>
        <interface name="public">
            <inet-address value="0.0.0.0"/>
        </interface>
        <interface name="unsecure">
            <inet-address value="${jboss.bind.address.unsecure:127.0.0.1}"/>
        </interface>
    </interfaces>

    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
        <socket-binding name="management-native" interface="management" port="${jboss.management.native.port:9999}"/>
        <socket-binding name="management-http" interface="management" port="${jboss.management.http.port:9990}"/>
        <socket-binding name="management-https" interface="management" port="${jboss.management.https.port:9443}"/>
        <socket-binding name="ajp" port="8009"/>
        <socket-binding name="http" port="8080"/>
        <socket-binding name="https" port="8443"/>
        <socket-binding name="osgi-http" interface="management" port="8090"/>
        <socket-binding name="remoting" port="4447"/>
        <socket-binding name="txn-recovery-environment" port="4712"/>
        <socket-binding name="txn-status-manager" port="4713"/>
        <outbound-socket-binding name="mail-smtp">
            <remote-destination host="localhost" port="25"/>
        </outbound-socket-binding>
    </socket-binding-group>

</server>''',
        'Path': '/tmp/jboss-example.xml',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-FileOps'
      subprocedure = 'AddTextToFile'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Replace value using sed', {
      description = ''
      actualParameter = [
        'commandToRun': 'sed -i \'s/<socket-binding name="management-native" interface="management" port="${jboss.management.native.port:9999}"/<socket-binding name="management-native" interface="management" port="${jboss.management.native.port:$[/myPipeline/managamentNativePort]}"/g\' /tmp/jboss-example.xml',
        'shellToUse': 'bash',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Read value using grep', {
      description = ''
      actualParameter = [
        'commandToRun': 'grep -oP \'jboss.management.native.port:\\K\\d+(?=})\' /tmp/jboss-example.xml',
        'postp': 'postp --loadProperty /myPipeline/readvalue_postp',
        'shellToUse': 'bash',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Insert Element using sed', {
      description = ''
      actualParameter = [
        'commandToRun': '''sed -ie \'/jboss.management.native.port/a \\
        <socket-binding name="phil"/>\' /tmp/jboss-example.xml''',
        'shellToUse': 'bash',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Delete Element using sed', {
      description = ''
      actualParameter = [
        'commandToRun': ' sed -i \'/<socket-binding name="ajp" port="8009"\\/>/d\' /tmp/jboss-example.xml',
        'shellToUse': 'bash',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Show XML Content', {
      description = ''
      actualParameter = [
        'commandToRun': 'cat /tmp/jboss-example.xml',
        'shellToUse': 'bash',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }
  }

  stage 'Using Groovy', {
    description = ''
    colorCode = '#2ca02c'
    completionType = 'auto'
    pipelineName = 'XML Examples'
    waitForPlannedStartDate = '0'

    gate 'PRE', {
      }

    gate 'POST', {
      }

    task 'Create Example File', {
      description = ''
      actualParameter = [
        'AddNewLine': '0',
        'Append': '0',
        'Content': '''<?xml version="1.0" encoding="UTF-8"?>

<bookstore>

<book category="cooking">
  <title lang="en">Everyday Italian</title>
  <author>Giada De Laurentiis</author>
  <year>2005</year>
  <price>30.00</price>
</book>

<book category="children">
  <title lang="en">Harry Potter</title>
  <author>J K. Rowling</author>
  <year>2005</year>
  <price>29.99</price>
</book>

<book category="web">
  <title lang="en">XQuery Kick Start</title>
  <author>James McGovern</author>
  <author>Per Bothner</author>
  <author>Kurt Cagle</author>
  <author>James Linn</author>
  <author>Vaidyanathan Nagarajan</author>
  <year>2003</year>
  <price>49.99</price>
</book>

<book category="web">
  <title lang="en">Learning XML</title>
  <author>Erik T. Ray</author>
  <year>2003</year>
  <price>39.95</price>
</book>

</bookstore>''',
        'Path': '/tmp/bookstore-example.xml',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-FileOps'
      subprocedure = 'AddTextToFile'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Read Element', {
      description = ''
      actualParameter = [
        'commandToRun': '''import groovy.xml.*
  
File file = new File(\'/tmp/bookstore-example.xml\')
String fileContent = file.text

def bookstore = new XmlSlurper().parseText(fileContent)
def result = bookstore.children().find { node ->
    node.name() == \'book\' && node[\'@category\'] == \'children\'
}

println result.title.text()''',
        'postp': 'postp --loadProperty /myPipeline/readvalue_postp',
        'shellToUse': 'ec-groovy',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Read Attribute', {
      description = ''
      actualParameter = [
        'commandToRun': '''import groovy.xml.*
  
File file = new File(\'/tmp/bookstore-example.xml\')
String fileContent = file.text

def bookstore = new XmlSlurper().parseText(fileContent)
def result = bookstore.children().find { node ->
    node.name() == \'book\' && node[\'@category\'] == \'children\'
}

println result.title[\'@lang\'].text()''',
        'postp': 'postp --loadProperty /myPipeline/readvalue_postp',
        'shellToUse': 'ec-groovy',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Replace Element', {
      description = ''
      actualParameter = [
        'commandToRun': '''import groovy.xml.*

// load the xml file
File file = new File(\'/tmp/bookstore-example.xml\')
String fileContent = file.text

// Find an XML node
def bookstore = new XmlSlurper().parseText(fileContent)
def result = bookstore.children().find { node ->
    node.name() == \'book\' && node[\'@category\'] == \'children\'
}

// replace a node
result.replaceNode {
  	book(category: "children") {
        title("The Gruffalo")
        author("Julia Donaldson")
      	year("1999")
      	price("10.99")
  }
}

newbookstore = new XmlSlurper().parseText(XmlUtil.serialize(bookstore))

// Display new xml in log
println XmlUtil.serialize(newbookstore)

//Save File
def writer = new FileWriter(\'/tmp/bookstore-example.xml\')

//Pretty print XML to file
XmlUtil.serialize(newbookstore, writer)''',
        'shellToUse': 'ec-groovy',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Delete Element', {
      description = ''
      actualParameter = [
        'commandToRun': '''import groovy.xml.*

// load the xml file
File file = new File(\'/tmp/bookstore-example.xml\')
String fileContent = file.text

// Find an XML node
def bookstore = new XmlSlurper().parseText(fileContent)
def result = bookstore.children().find { node ->
    node.name() == \'book\' && node[\'@category\'] == \'web\' && node.title == "XQuery Kick Start"
}

// delete node
result.replaceNode {
}

newbookstore = new XmlSlurper().parseText(XmlUtil.serialize(bookstore))

// Display new xml in log
println XmlUtil.serialize(newbookstore)

//Save File
def writer = new FileWriter(\'/tmp/bookstore-example.xml\')

//Pretty print XML to file
XmlUtil.serialize(newbookstore, writer)''',
        'shellToUse': 'ec-groovy',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Insert Element', {
      description = ''
      actualParameter = [
        'commandToRun': '''import groovy.xml.*

// load the xml file
File file = new File(\'/tmp/bookstore-example.xml\')
String fileContent = file.text

// Find an XML node
def bookstore = new XmlSlurper().parseText(fileContent)
//def result = bookstore.children().find { node ->
//    node.name() == \'book\' && node[\'@category\'] == \'cooking\' && node.title == "Everyday Italian"
//}

// delete node
bookstore.appendNode {
	book(category: "cooking") {
    	title("Everyday Vegetarian")
      	author("Gordon Ramsey")
      	year("2010")
    }
}

newbookstore = new XmlSlurper().parseText(XmlUtil.serialize(bookstore))

// Display new xml in log
println XmlUtil.serialize(newbookstore)

//Save File
def writer = new FileWriter(\'/tmp/bookstore-example.xml\')

//Pretty print XML to file
XmlUtil.serialize(newbookstore, writer)''',
        'shellToUse': 'ec-groovy',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }
  }

  // Custom properties

  property 'ec_counters', {

    // Custom properties
    pipelineCounter = '18'
  }
  managamentNativePort = '1111'
  readvalue_postp = '''use ElectricCommander;
push (@::gMatchers,
{
   id => "returnedValue",
   pattern => q{(.*)},
   action => q{
   setProperty("/myJob/result", "$1");
   }
});'''
}