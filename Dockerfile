FROM maven:3-jdk-8-alpine
MAINTAINER jsk <999@test.com.cn>
ENV app_name="template-project"
ENV work_dir="/home/admin/$app_name"

ENV JAVA_OPTS=" -Dcatalina.vendor=kaka -Djava.security.egd=file:/dev/./urandom -Dlog4j.defaultInitOverride=true -Dorg.apache.tomcat.util.http.ServerCookie.ALLOW_EQUALS_IN_VALUE=true -Dorg.apache.tomcat.util.http.ServerCookie.ALLOW_HTTP_SEPARATORS_IN_V0=true -server -Xms512m -Xmx512m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -Xmn256m -XX:MaxDirectMemorySize=1g -XX:SurvivorRatio=10 -XX:+UseConcMarkSweepGC -XX:CMSMaxAbortablePrecleanTime=5000 -XX:+CMSClassUnloadingEnabled -XX:CMSInitiatingOccupancyFraction=80 -XX:+UseCMSInitiatingOccupancyOnly -XX:+ExplicitGCInvokesConcurrent -Dsun.rmi.dgc.server.gcInterval=2592000000 -Dsun.rmi.dgc.client.gcInterval=2592000000 -XX:ParallelGCThreads=4 -Xloggc:$work_dir/logs/gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$work_dir/logs/java.hprof -Djava.awt.headless=true -Dsun.net.client.defaultConnectTimeout=10000 -Dsun.net.client.defaultReadTimeout=30000 -DJM.LOG.PATH=$work_dir/logs -DJM.SNAPSHOT.PATH=$work_dir/snapshots -Dfile.encoding=utf-8 "

COPY ./boot/target/$app_name.jar  $work_dir

EXPOSE 8001
EXPOSE 20880

WORKDIR $work_dir

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dlogging.path=/app/logs/ -jar  $work_dir/$app_name.jar"]

