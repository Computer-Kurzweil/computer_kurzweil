#!/usr/bin/env bash

function bootRunAllinone() {
    ./gradlew clean allinone:jar
    jar xvf allinone/build/libs/allinone-0.0.1-SNAPSHOT.jar
    #java -jar allinone/build/libs/allinone-0.0.1-SNAPSHOT.jar
    #./gradlew allinone:run
}

function bootRunDefault() {
    ./gradlew clean allinone:run
}

bootRunAllinone
#bootRunDefault
