#!/usr/bin/env bash

function runJar() {
    ./gradlew -i clean
    ./gradlew -i shadowJar
    java -cp build/libs/computer_kurzweil-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
}

function runDefault() {
    ./gradlew -i clean
    ./gradlew -i --warning-mode all runShadow
}

function runTest() {
    ./gradlew -i  --warning-mode all clean build test check runShadow
}

runTest
#runJar
#runDefault
