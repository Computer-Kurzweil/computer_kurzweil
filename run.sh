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
    #./gradlew -i --warning-mode all computer_kurzweil_app:clean computer_kurzweil_app:build computer_kurzweil_app:test computer_kurzweil_app:check computer_kurzweil_app:runShadow
    #./gradlew -i --warning-mode all insourcing_cyclic_cellular_automaton:clean insourcing_cyclic_cellular_automaton:build insourcing_cyclic_cellular_automaton:test insourcing_cyclic_cellular_automaton:check insourcing_cyclic_cellular_automaton:runShadow
    #./gradlew -i --warning-mode all insourcing_mandelbrot:clean insourcing_mandelbrot:build insourcing_mandelbrot:test insourcing_mandelbrot:check insourcing_mandelbrot:runShadow
    #./gradlew -i --warning-mode all insourcing_simulated_evolution:clean insourcing_simulated_evolution:build insourcing_simulated_evolution:test insourcing_simulated_evolution:check insourcing_simulated_evolution:runShadow
    #./gradlew -i --warning-mode all insourcing_diffusion_limited_aggregation:clean insourcing_diffusion_limited_aggregation:build insourcing_diffusion_limited_aggregation:test insourcing_diffusion_limited_aggregation:check insourcing_diffusion_limited_aggregation:runShadow
}

#runJar
#runDefault

#runAlllTests
#runSubmoduleCca
#runSubmoduleDla
runSubmoduleMandelbrot
#runSubmoduleSimulatedEvolution
#runSubmoduleMain
