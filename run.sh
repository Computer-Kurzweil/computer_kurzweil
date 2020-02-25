#!/usr/bin/env bash

function runJar() {
    ./gradlew -i clean shadowJar runShadow
    java -cp build/libs/computer_kurzweil-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
}

function runDefault() {
    ./gradlew -i clean run
}

function runTest() {
    ./gradlew -i clean build test check
}

runTest
#runJar
#runDefault
