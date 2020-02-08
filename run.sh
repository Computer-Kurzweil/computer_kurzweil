#!/usr/bin/env bash

function bootRunAllinone() {
    ./gradlew clean allinone:jar
    ./gradlew allinone:run
}

function bootRunDefault() {
    ./gradlew clean allinone:run
}

bootRunAllinone
#bootRunDefault
