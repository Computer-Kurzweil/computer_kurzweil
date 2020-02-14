#!/usr/bin/env bash

function bootRunAllinone() {
    ./gradlew clean allinone:clean allinone:shadowJar
    java -cp allinone/build/libs/allinone-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication

    #jar -tf libs/allinone.jar -C allinone/build
    #jar cfe allinone/build/libs/allinone-0.0.1-SNAPSHOT.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
    #java -jar allinone/build/libs/allinone-0.0.1-SNAPSHOT.jar
    #./gradlew clean allinone:run
}

function bootRunDefault() {
    ./gradlew clean allinone:run
}



bootRunAllinone
#bootRunDefault
