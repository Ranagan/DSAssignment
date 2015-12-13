TO RUN THE RMI SERVICE
----------------------

Place the vigenere.jar file into the tomcat lib folder.

In the command prompt, type -> java -cp vigenere.jar ie.gmit.sw.VigenereBreakerImpl "<Text File Location>.txt"

eg -> java -cp vigenere.jar ie.gmit.sw.VigenereBreakerImpl "../WarAndPeace.txt"

This starts up the RMI service.



TO RUN THE CRACKER.WAR on TOMCAT
--------------------------------

Place the cracker.war file into the webapps folder in your tomcat directory.
When you run startup.bat in the bin folder, tomcat will automatically create a new folder called cracker.

All you need to do to access this is, in a browser, go to "localhost:8080/cracker"

Then you just need to enter the cypher text, max key length and press the "Crack Cypher" button, and the text you entered should be decyphered.


LINK TO GITHUB
--------------

http://github.com/Ranagan/DSAssignment