#!/usr/bin/env bash


function site() {
  ./mvnw -e clean install site site:deploy
}

function runGithubCi(){
    ./mvnw -B -DskipTests clean dependency:list install --file pom.xml
}

function makeRelease() {
    ./mvnw -DskipTests -Dresume=false -DdryRun=true release:prepare
    ./mvnw -DskipTests -Dresume=false release:prepare release:perform
}

function publishSite() {
    ./mvnw clean install site site:stage scm-publish:publish-scm
}

function run() {
    ./mvnw
}

function main() {
    #site
    #runGithubCi
    #makeRelease
    run
}

main
