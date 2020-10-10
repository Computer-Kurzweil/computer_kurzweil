#!/usr/bin/env bash


function site() {
  ../../../mvnw -e site site:deploy
}

function run() {
    ../../../mvnw
}

function main() {
    #site
    run
}

main
