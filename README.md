[Github repo](https://github.com/isRedacted/csv-processor)
# csv-processor
## Intro
This is a program for assessment 1 to process a specific csv file and allow the user to perform different analyses on it. This includes all required functionality like retrieving posts, deleting posts, adding posts, 

### Logic
This class handles all backend work that involved manipulating or returning the social media posts.
### UI
This class handles anything to do with user input. It instantiates the logic class and handles any exceptions that the logic class may throw. It also tests user input for both menu navigation and validating the details of the user specified post to add.
### Main
This class instantiates the UI class and performs the initial file check, handling any errors related to reading the csv file. It then runs the main UI loop until the signal is given to quit.
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
Then either I screwed up my instructions/files or you screwed up your compiling. Not to worry, though, you can run the included pre-compiled EmergencyMain.jar (Compiled for =>JavaSE-1.8) file inside the bin folder with the command:

java -jar EmergencyMain.jar
