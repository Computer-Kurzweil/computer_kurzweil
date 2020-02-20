rem cmd /c gradlew -i clean test

cmd /c gradlew clean shadowJar startShadowScripts
rem cmd /c .\build\scriptsShadow\computer_kurzweil.bat

cmd /c java -cp build/libs/computer_kurzweil-all.jar org.woehlke.computer.kurzweil.ComputerKurzweilApplication
