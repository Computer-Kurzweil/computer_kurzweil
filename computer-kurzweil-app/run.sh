#!/usr/bin/env bash

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
