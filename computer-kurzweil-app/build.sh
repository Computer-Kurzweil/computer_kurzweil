#!/usr/bin/env bash

function buildSite() {
      ./mvnw site site:deploy
}

function build() {
    ./mvnw dependency:purge-local-repository clean dependency:resolve dependency:resolve-plugins dependency:tree install
}

function main() {
    build
    buildSite
}

main

