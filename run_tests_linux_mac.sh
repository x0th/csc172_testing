#!/bin/bash

# Script for internal testing in CSC172 Fall 2020
# Made by: Pavlo Pastaryev
# Email: ppastary@u.rochester.edu

if [ $# -eq 0 ]; then
	echo "Usage: ./run_tests_linux_mac.sh Tester1,Tester2,Tester3... <options>"
	echo Options:
	echo " --no-unzip         Do not unzip submissions. Folders from ./tested directory will be used."
	echo " --directory, -d    Select a custom submissions directory. Default: -d ./submissions"
else
	if [ "$2" == "--directory" ] || [ "$2" == "-d" ]; then
		javac StageOne.java
		java StageOne $3
	elif [ "$2" != "--no-unzip" ]; then
		javac StageOne.java
		java StageOne
	fi

	javac -cp dependencies/junit-4.13.jar:. TestRunner.java
	while IFS= read -r line
		do
			java -cp dependencies/junit-4.13.jar:dependencies/hamcrest-core-1.3.jar:tests:tested/"$line":. TestRunner $1 "$line"
		done < tests_to_run.txt
	rm tests_to_run.txt
fi