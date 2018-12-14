#Please read this file before you check each single unit test
1. We use JUNIT to do the unit testing
2. All test cases are within each corresponding java test file 
3. You may see the output 3/3 pass from the testlog, this is what IDE provide
   with us. We use assertEqual to do the test, in this case, if we have 2 test
   case and they all past, the IDE will give us 2 out of 2 test passes.
4. We have two csv file in the directory, it is because for the Data IO testing,
   we need to test if the funciton we build could get the file as input, in
   this case, we write the test csv file so that the Data IO part could be tested.
5. Two files need to have attention, first file is the Test_main file, that file
   has public keyword, but that is the main function and correlate to the GUI
   part, so we did not write the unit test. Another file is called OpenFile.java,
   this file is used to open and get the file, working as a file selector, but it
   has GUI function in it, opendialog in this case, we did not test this function.
