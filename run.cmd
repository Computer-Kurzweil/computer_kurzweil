cmd /c gradlew clean allinone:clean allinone:shadowJar

cmd /c java -cp allinone/build/libs/allinone-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
