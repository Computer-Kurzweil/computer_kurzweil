rem cmd /c gradlew -i clean test

cmd /c gradlew clean shadowJar runShadow

rem cmd /c java -cp build/libs/computer_kurzweil-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
