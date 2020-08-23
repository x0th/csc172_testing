:: Script for internal testing in CSC172 Fall 2020
:: Made by: Pavlo Pastaryev
:: Email: ppastary@u.rochester.edu

@echo off
if [%1] == [] (
	echo Usage: run_tests_windows.bat Tester1,Tester2,Tester3,... ^<options^>
	echo Options:
	echo  --no-unzip         Do not unzip submissions. Folders from .^\tested directory will be used.
	echo  --directory, -d    Select a custom submissions directory. Default: -d .^\submissions
) else (
	if [%2] == [--directory] (
		javac StageOne.java
		java StageOne %3
	) else if [%2] == [-d] (
		javac StageOne.java
		java StageOne %3
	) else if NOT [%2] == [--no-unzip] (
		javac StageOne.java
		java StageOne
	)
	javac -cp dependencies\*;. TestRunner.java
	for /f "tokens=*" %%a in (tests_to_run.txt) do (
		java -cp dependencies\*;tests;tested\%%a;. TestRunner %1 %%a
	)
	del tests_to_run.txt
)