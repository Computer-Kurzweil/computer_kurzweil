#!/usr/bin/env bash

function bootRunAllinone() {
    ./gradlew clean allinone:bootRun --args='--spring.profiles.active=default'
}

function bootRunHerokuLocal() {
    ./gradlew composeUp
    ./gradlew clean assemble
    heroku local web
    ./gradlew composeDown
}

function bootRunDefault() {
    ./gradlew clean bootRun --args='--spring.profiles.active=default'
}

bootRunAllinone
#bootRunHeroku
#bootRunDefault
