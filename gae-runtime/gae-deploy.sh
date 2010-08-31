#!/bin/bash

#
# This script installs the Google App Engine artifacts into you local Maven repository.
#
# If you would like to avoid the need for this step, ask Google by voting for the following issue:
#   http://code.google.com/p/googleappengine/issues/detail?id=1296
#

SDK_VERSION=1.3.7

if [ "$1" == "" ]
then
    echo "usage: $0 /path/to/appengine-java-sdk-$SDK_VERSION [install|deploy]"
    exit 1
fi

if [ "$2" == "" ]
then
    echo "usage: $0 /path/to/appengine-java-sdk-$SDK_VERSION [install|deploy]"
    exit 1
fi

GAE_SDK_PATH="$1"
TASK="$2"
URL="svn:https://maven-gae-plugin.googlecode.com/svn/repository"

mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/lib/user/appengine-api-1.0-sdk-$SDK_VERSION.jar -DgroupId=com.google.appengine -DartifactId=appengine-api-1.0-sdk -Dversion=$SDK_VERSION -DgeneratePom=true -Dpackaging=jar
 
mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/docs/javadoc/appengine-api-1.0-sdk-$SDK_VERSION-javadoc.jar -DgroupId=com.google.appengine -DartifactId=appengine-api-1.0-sdk -Dversion=$SDK_VERSION -DgeneratePom=true -Dclassifier=javadoc -Dpackaging=jar
 
mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/lib/user/appengine-api-labs-$SDK_VERSION.jar -DgroupId=com.google.appengine -DartifactId=appengine-api-labs -Dversion=$SDK_VERSION -DgeneratePom=true -Dpackaging=jar
 
mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/lib/appengine-tools-api.jar -DgroupId=com.google.appengine -DartifactId=appengine-tools-sdk -Dversion=$SDK_VERSION -DgeneratePom=true -Dpackaging=jar

mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/lib/impl/appengine-local-runtime.jar -DgroupId=com.google.appengine -DartifactId=appengine-local-runtime -Dversion=$SDK_VERSION -DgeneratePom=true -Dpackaging=jar

mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/lib/impl/appengine-api-stubs.jar -DgroupId=com.google.appengine -DartifactId=appengine-api-stubs -Dversion=$SDK_VERSION -DgeneratePom=true -Dpackaging=jar

mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/lib/testing/appengine-testing.jar -DgroupId=com.google.appengine -DartifactId=appengine-testing -Dversion=$SDK_VERSION -DgeneratePom=true -Dpackaging=jar

mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/docs/testing/javadoc/appengine-testing-javadoc.jar -DgroupId=com.google.appengine -DartifactId=appengine-testing -Dversion=$SDK_VERSION -DgeneratePom=true -Dclassifier=javadoc -Dpackaging=jar

mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/lib/user/orm/jdo2-api-2.3-eb.jar -DgroupId=com.google.appengine.orm -DartifactId=jdo2-api -Dversion=2.3-eb -DgeneratePom=true -Dpackaging=jar

mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/lib/user/orm/datanucleus-appengine-1.0.7.final.jar -DgroupId=com.google.appengine.orm -DartifactId=datanucleus-appengine -Dversion=1.0.7 -DgeneratePom=true -Dpackaging=jar

mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/lib/user/orm/datanucleus-core-1.1.5.jar -DgroupId=com.google.appengine.orm -DartifactId=datanucleus-core -Dversion=1.1.5 -DgeneratePom=true -Dpackaging=jar

mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/lib/user/orm/datanucleus-jpa-1.1.5.jar -DgroupId=com.google.appengine.orm -DartifactId=datanucleus-jpa -Dversion=1.1.5 -DgeneratePom=true -Dpackaging=jar

mvn $TASK:$TASK-file -Durl=$URL -Dfile=$GAE_SDK_PATH/lib/user/appengine-jsr107cache-$SDK_VERSION.jar -DgroupId=com.google.appengine -DartifactId=jsr107cache -Dversion=$SDK_VERSION -DgeneratePom=true -Dpackaging=jar
