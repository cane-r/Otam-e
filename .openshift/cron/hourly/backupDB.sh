#!/bin/bash
mysqldump -h $OPENSHIFT_MYSQL_DB_HOST -P ${OPENSHIFT_MYSQL_DB_PORT:-3306} -u ${OPENSHIFT_MYSQL_DB_USERNAME:-'admin'} --password="$OPENSHIFT_MYSQL_DB_PASSWORD" --extended-insert=FALSE --lock-tables=false onlinetool | gzip > ~/app-root/data/backups/onlinetool_$(date +%Y-%m-%d-%H.%M.%S).sql.gz
