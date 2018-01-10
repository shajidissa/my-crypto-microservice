/home/ubuntu/apache-tomcat-8.5.15/bin/shutdown.sh
rm -R /home/ubuntu/apache-tomcat-8.5.15/webapps/microservice-mysql-simple
rm -R /home/ubuntu/apache-tomcat-8.5.15/webapps/microservice-mysql-simple.war
cd /home/ubuntu/microservice-mysql-simple
mvn clean compile package
rm /home/ubuntu/apache-tomcat-8.5.15/logs/*.*
rm -r /home/ubuntu/apache-tomcat-8.5.15/work/Catalina/localhost/*
cp /home/ubuntu/microservice-mysql-simple/target/microservice-mysql-simple.war /home/ubuntu/apache-tomcat-8.5.15/webapps/
/home/ubuntu/apache-tomcat-8.5.15/bin/start.sh
