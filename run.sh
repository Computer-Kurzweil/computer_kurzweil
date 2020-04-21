#!/usr/bin/env bash


function site() {
  ./mvnw -e clean install site site:deploy
}

function runGithubCi(){
    ./mvnw -B -DskipTests clean dependency:list install --file pom.xml
}

function makeRelease() {
    ./mvnw -B -DskipTests clean dependency:list install site site:deploy --file pom.xml
    ./mvnw -B -DskipTests release:prepare
}

function run() {
    ./mvnw
}

function main() {
    runGithubCi
    #site
    #run
}

main
