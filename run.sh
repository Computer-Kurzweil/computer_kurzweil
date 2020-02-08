#!/usr/bin/env bash

function bootRunAllinone() {
    ./gradlew clean allinone:assemble
}

function bootRunHerokuLocal() {
    ./gradlew composeUp
    ./gradlew clean assemble
    heroku local web
    ./gradlew composeDown
}

function bootRunDefault() {
    ./gradlew clean allinone:run
}

bootRunAllinone
#bootRunHeroku
#bootRunDefault
