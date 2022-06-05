FROM quay.io/wildfly/wildfly
# Maintainer
MAINTAINER  John Ongwae <john.oenga@systechafrica.com>

# Appserver
ENV WILDFLY_USER admin
ENV WILDFLY_PASS Admin@123

# Databases
ENV DB_NAME notifications
ENV DB_USER postgres
ENV DB_PASS Admin@123
ENV POSTGRES_DATASOURCE_NAME=NotificationDS
ENV DB_URI notifications_db:5444
ENV POSTGRE_VERSION 42.3.3
ENV JBOSS_CLI /opt/jboss/wildfly/bin/jboss-cli.sh
ENV DEPLOYMENT_DIR /opt/jboss/wildfly/standalone/deployments/
ENV JAVA_OPTS -Xms1024M -Xmx2048M -XX:MetaspaceSize=2048M -XX:MaxMetaspaceSize=2048m -XX:CompileThreshold=8000 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=600

# Setting WildFly Admin Console
RUN echo "=> Adding WildFly administrator"
RUN $JBOSS_HOME/bin/add-user.sh -u $WILDFLY_USER -p $WILDFLY_PASS --silent

# Configure setup wildfly
RUN echo "=> Starting WildFly server" && \
  bash -c '$JBOSS_HOME/bin/standalone.sh &' && \
  echo "=> Waiting for the server to boot" && \
  bash -c 'until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do echo `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null`; sleep 1; done' && \
  echo "=> Downloading PostgresSql driver" && \
  curl --location --output /tmp/postgresql-${POSTGRE_VERSION}.jar --url https://repo1.maven.org/maven2/org/postgresql/postgresql/${POSTGRE_VERSION}/postgresql-${POSTGRE_VERSION}.jar && \
  echo "=> Adding Postgres Sql module" && \
  $JBOSS_CLI --connect --command="module add --name=org.postgresql --resources=/tmp/postgresql-${POSTGRE_VERSION}.jar --dependencies=javax.api,javax.transaction.api" && \
  echo "=> Adding Postgres driver" && \
  $JBOSS_CLI --connect --command="/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=org.postgresql,driver-class-name=org.postgresql.Driver)" && \
  echo "=> Creating a new datasource" && \
  echo "=> Increase WildFly upload file limit"&&\
  $JBOSS_CLI --connect --command="/subsystem=undertow/server=default-server/http-listener=default/:write-attribute(name=max-post-size,value=25485760)" && \
  $JBOSS_CLI --connect --command="/subsystem=undertow/server=default-server/http-listener=default:write-attribute(name=max-parameters, value=3000)" && \
    $JBOSS_CLI --connect --command="/extension=org.wildfly.extension.microprofile.openapi-smallrye:add()" && \
    $JBOSS_CLI --connect --command="/subsystem=microprofile-openapi-smallrye:add()" && \
  $JBOSS_CLI --connect --command="/subsystem=undertow/server=default-server/https-listener=https:write-attribute(name=max-parameters, value=3000)" && \
  echo "=> Shutting down WildFly and Cleaning up" && \
  $JBOSS_CLI --connect --command=":shutdown" && \
  rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/ $JBOSS_HOME/standalone/log/* && \
  rm -f /tmp/*.jar
# Expose http and admin ports
EXPOSE 8080 9990

#echo "=> Restarting WildFly"
# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interfaces
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-Djboss.socket.binding.port-offset=0", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

COPY target/notificationsystem.war /opt/jboss/wildfly/standalone/deployments/
