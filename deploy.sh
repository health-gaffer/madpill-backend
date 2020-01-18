#!/bin/bash

WORK_DIR=/var/www/madpill/madpill-backend

cd $WORK_DIR
TEMP_PID=`ps -ef | grep "madpill-backend" | grep "java" | awk '{print $2}'`
if [[ -z $TEMP_PID ]]; then
    echo "[ no madpill-backend process is running ]"
else
    echo "[ previous madpill-backend process pid is:$TEMP_PID, going to be killed ]"
    kill -9 $TEMP_PID
fi
source env.sh
nohup java -jar madpill-backend-0.0.1-SNAPSHOT.jar < /dev/null > /dev/null 2>&1 &
echo "[ madpill backend started ]"

exit 0