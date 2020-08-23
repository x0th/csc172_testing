To run tests for a lab on students' submissions:

1. Place all zipped submissions in the 'submissions' folder.

2. Place a tester (e.g. Lab1Tester.java) in the 'tests' folder.

3. From a terminal, run a script depending on your system (either run_tests_linux_mac.sh or run_tests_windows.bat) with a tester as the parameter (e.g. ./run_tests_linux_mac.sh Lab1Tester).

Make sure you have java in your PATH. To test for that, try running java -version and javac -version in your terminal.

For Linux or Mac users: if you get permission denied error, try running chmod +x run_tests_linux_mac.sh in your terminal before the actual script.

Script usage: run_tests_your_platform Tester1,Tester2,Tester3,... &lt;options>

Options:

 --no-unzip         Do not unzip submissions. Folders from ./tested directory will be used.

 --directory, -d    Select a custom submissions directory. Default: -d ./submissions 