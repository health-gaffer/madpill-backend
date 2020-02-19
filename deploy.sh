#!/bin/bash

WORK_DIR=/var/www/madpill/madpill-backend

cd $WORK_DIR || exit
TEMP_PID=$(ps -ef | grep "madpill-backend" | grep "java" | awk '{print $2}')
if [[ -z $TEMP_PID ]]; then
    echo "[ no madpill-backend process is running ]"
else
    echo "[ previous madpill-backend process pid is:$TEMP_PID, going to be killed ]"
    kill -9 "$TEMP_PID"
fi

nohup java -jar madpill-backend-0.0.1-SNAPSHOT.jar -Djasypt.encryptor.password="$JASYPT_ENCRYPTOR_PASSWORD" -Dspring.profiles.active=prod < /dev/null > /dev/null 2>&1 &
echo "[ madpill backend started ]"

exit 0