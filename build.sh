#!/usr/bin/env bash

function runGithubCi(){
    ./mvnw -B -DskipTests clean dependency:list install --file pom.xml
}

function makeReleaseDryRun() {
    ./mvnw -DskipTests -Dresume=false -DdryRun=true release:prepare
}

function makeRelease() {
    makeReleaseDryRun
    ./mvnw -DskipTests -Dresume=false release:prepare release:perform
}

function publishSite() {
    ./mvnw site site:stage scm-publish:publish-scm
}

function site() {
  ./mvnw -e site site:deploy
}

function buildClean() {
    ./mvnw dependency:purge-local-repository clean
}

function buildResolve() {
    ./mvnw dependency:resolve dependency:resolve-plugins dependency:tree
}

function build() {
    buildClean
    buildResolve
    ./mvnw install
    #site
}

function main() {
    #runGithubCi
    #makeRelease
    build
}

main
