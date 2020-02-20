#!/usr/bin/env bash

function bootRunAllinone() {
    ./gradlew -i clean allinone:clean allinone:shadowJar
    java -cp allinone/build/libs/allinone-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication

    #jar -tf libs/allinone.jar -C allinone/build
    #jar cfe allinone/build/libs/allinone-0.0.1-SNAPSHOT.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
    #java -jar allinone/build/libs/allinone-0.0.1-SNAPSHOT.jar
    #./gradlew clean allinone:run
}

function bootRunDefault() {
    ./gradlew -i clean allinone:run
}



bootRunAllinone
#bootRunDefault
