#!/usr/bin/env bash

function runJar() {
    ./gradlew -i computer_kurzweil_app:clean
    ./gradlew -i computer_kurzweil_app:shadowJar
    java -cp computer_kurzweil_app/build/libs/computer_kurzweil-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
}

function runDefault() {
    ./gradlew -i computer_kurzweil_app:clean
    ./gradlew -i --warning-mode all computer_kurzweil_app:runShadow
}

function runSubmodule() {
    submodule=$1
    ./gradlew -i --warning-mode all $submodule:clean $submodule:build $submodule:test $submodule:check $submodule:runShadow
}

function runSubmoduleMain() {
    runSubmodule "computer_kurzweil_app"
}

function runSubmoduleCca() {
    runSubmodule "insourcing_cyclic_cellular_automaton"
}

function runSubmoduleDla() {
    runSubmodule "insourcing_diffusion_limited_aggregation"
}

function runSubmoduleMandelbrot() {
    runSubmodule "insourcing_mandelbrot"
}

function runSubmoduleSimulatedEvolution() {
    runSubmodule "insourcing_simulated_evolution"
}

function buildSubmodule() {
    submodule=$1
    ./gradlew -i --warning-mode all $submodule:clean $submodule:build $submodule:test $submodule:check
}

function runAlllTests() {
    submodules="computer_kurzweil_app insourcing_cyclic_cellular_automaton insourcing_diffusion_limited_aggregation insourcing_mandelbrot insourcing_simulated_evolution"
    for i in $submodules
    do
        echo $i
        buildSubmodule $i
    done
}

function main() {
    #runJar
    #runDefault
    #runAlllTests
    #runSubmoduleCca
    runSubmoduleDla
    #runSubmoduleMandelbrot
    #runSubmoduleSimulatedEvolution
    #runSubmoduleMain
}
