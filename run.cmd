rem cmd /c gradlew -i clean test

cmd /c gradlew computer_kurzweil_app:clean computer_kurzweil_app:shadowJar computer_kurzweil_app:runShadow

rem cmd /c java -cp build/libs/computer_kurzweil-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
