#!/usr/bin/env bash

function releaseMe(){
    export $JAVA_OPTS
    ./mvnw -e -DskipTests=true -B -V dependency:purge-local-repository clean release:clean
    ./mvnw -e -DskipTests=true -B -V release:prepare && ./mvnw -e -DskipTests=true -B -V release:perform
}

releaseMe
