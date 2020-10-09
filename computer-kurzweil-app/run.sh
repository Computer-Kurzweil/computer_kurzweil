#!/usr/bin/env bash

function site() {
    ../mvnw -e clean install site site:deploy
}

function runFromJar() {
    ../mvnw
}

function runFromBuild() {
    ../mvnw
     ../mvnw exec:java
}

function main() {
    #site
    runFromBuild
}

main
