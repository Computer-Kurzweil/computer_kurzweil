#!/usr/bin/env bash


function site() {
  ./mvnw -e clean install site site:deploy
}

function runGithubCi(){
    ./mvnw -B -DskipTests clean dependency:list install --file pom.xml
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
