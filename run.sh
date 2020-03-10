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
    #./gradlew -i  --warning-mode all computer_kurzweil_app:clean computer_kurzweil_app:build computer_kurzweil_app:test computer_kurzweil_app:check computer_kurzweil_app:runShadow
    ./gradlew -i  --warning-mode all insourcing_cyclic_cellular_automaton:clean insourcing_cyclic_cellular_automaton:build insourcing_cyclic_cellular_automaton:test insourcing_cyclic_cellular_automaton:check insourcing_cyclic_cellular_automaton:runShadow
}

runTest
#runJar
#runDefault
