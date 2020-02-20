#!/usr/bin/env bash

function runJar() {
    ./gradlew -i clean allinone:clean allinone:shadowJar
    java -cp allinone/build/libs/allinone-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
}

function runDefault() {
    ./gradlew -i clean allinone:run
}

function runTest() {
    ./gradlew -i clean allinone:clean allinone:test
}

#runTest
runJar
#runDefault
