#!/usr/bin/env bash

function bootRunAllinone() {
    ./gradlew clean allinone:assemble
}

function bootRunDefault() {
    ./gradlew clean allinone:run
}

bootRunAllinone
#bootRunDefault
