# csv-processor
## Compilation instructions
1. Unzip csv-processor.zip to a folder
2. Navigate your terminal/command line interface of choice to the "assignment1" folder containing "src"
3. Copy your chosen flavour of junit 4 to this folder
4. Compile and then run using the following commands: 

### Linux (Tested on Mint)
javac -classpath junit.jar:hamcrest.jar -d bin ./src/assignment1/*.java

### Windows
javac -classpath junit.jar;hamcrest.jar -d bin src/assignment1/*.java

5. Navigate to the bin folder

6. Run with:
java assignment1.Main

## But what if it still doesn't work!?
Then either I screwed up my instructions or you screwed up your compiling. Not to worry, though, you can run the included pre-compiled EmergencyMain.jar (Compiled for =>JavaSE-1.8) file inside the bin folder with the command:

java -jar EmergencyMain.jar
