#!/usr/bin/env bash

function runJar() {
    ./gradlew -i clean shadowJar
    java -cp allinone/build/libs/allinone-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
}

function runDefault() {
    ./gradlew -i clean run
}

function runTest() {
    ./gradlew -i clean test
}

#runTest
runJar
#runDefault
