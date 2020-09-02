#!/bin/bash

source env_param.sh

# zk and its status
# kafka anf checking
# druid (all nodes)

# this script should run after MAC connects all nodes successfully.
# add ip-host lists into MAC's hosts file:  sudo vi /etc/hosts(EIP)
# ./hosts should be intranet ip.  192.168.*
# IPS=(master query kafka-zk datanode-0001 datanode-0002 datanode-0003)
# IPS=( master query kafka-zk )
IPS=(datanode-0003)
for IP in ${IPS[@]}
do
#  ssh $IP "bash -c cd /opt/druid/apache-druid-0.19.0-SNAPSHOT/extensions/; tar -zxf lts.tar.gz"
	
	scp ./hosts root@$IP:/etc/hosts
			
	echo "=== Begin to start ${IP} ===> "
		if [[ $IP =~ master ]]
		then
			ssh root@$IP "rm -rf $ZK_DIR/var/zk/version-2 && rm -f $ZK_DIR/var/zk/zookeeper_server.pid && rm -rf /tmp/zk/version-2"
		 	ssh root@$IP "echo 1 > $ZK_DIR/var/zk/myid"
		 	ssh root@$IP sed -i "s/druid.host=master/druid.host=$IP/g" $DRUID_DIR/conf/druid/cluster/_common/common.runtime.properties
		 	ssh root@$IP "cd $ZK_DIR && bin/zkServer.sh start" 
		 	ssh root@$IP "cd $DRUID_DIR && setsid bin/start-cluster-master-no-zk-server > master-server.log 2>&1 &"
		fi

		if [[ $IP =~ query ]] 
		then
			ssh root@$IP "rm -rf $ZK_DIR/var/zk/version-2 && rm -f $ZK_DIR/var/zk/zookeeper_server.pid && rm -rf /tmp/zk/version-2"
			ssh root@$IP "echo 2 > $ZK_DIR/var/zk/myid"
			ssh root@$IP sed -i "s/druid.host=master/druid.host=$IP/g" $DRUID_DIR/conf/druid/cluster/_common/common.runtime.properties
			ssh root@$IP "cd $ZK_DIR && bin/zkServer.sh start"
			ssh root@$IP "cd $DRUID_DIR && setsid bin/start-cluster-query-server > query-server.log 2>&1 &"
		fi 
		
		if [[ $IP =~ kafka-zk ]]
		then
			ssh root@$IP "rm -rf $ZK_DIR/var/zk/version-2 && rm -f $ZK_DIR/var/zk/zookeeper_server.pid && rm -rf /tmp/zk/version-2"
			ssh root@$IP "echo 3 > $ZK_DIR/var/zk/myid"
			# ssh root@$IP sed -i "s/druid.host=master/druid.host=$IP/g" $DRUID_DIR/conf/druid/cluster/_common/common.runtime.properties
			# ps -ef | grep -i druid  and kill them (not kafka)
			ssh root@$IP "ps aux |grep zook |grep zoo.cfg | awk '{print \$2}' | xargs kill"
			ssh root@$IP "cd $ZK_DIR && bin/zkServer.sh start" 
			ssh root@$IP "cd $KAFKA_DIR && bin/kafka-server-start.sh config/server.properties 2>&1 > var/kafka.log &"
		fi

		if [[ $IP =~ datanode ]]
		then
			ssh root@$IP sed -i "s/druid.host=master/druid.host=$IP/g" $DRUID_DIR/conf/druid/cluster/_common/common.runtime.properties;
			# format SSD disk and mount it
			# fdisk /dev/vdb1 (CLI)
			# mkfs -t ext4 /dev/vdb1
			# ssh root@$IP mount /dev/vdb1 /data
			# ps aux |grep druid |grep /opt/druid/apache | awk '{print \$2}' | xargs kill
			ssh root@$IP "cd $DRUID_DIR && nohup bin/start-cluster-data-server > data-server.log 2>&1 &"
		fi
done

# clean historical data from all nodes

# generate relative contents(settings, startup script)

# copy files to right nodes

# startup 

# check the status and reports

