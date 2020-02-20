rem cmd /c gradlew -i clean test

cmd /c gradlew clean shadowJar
cmd /c java -cp allinone/build/libs/allinone-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
