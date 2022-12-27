@TestCases
Feature: Test Cases

    Background:
        Given The tester is on the test case dashboard 

        #maybe should pass -- referring to username inputs not description or steps inputs
    Scenario: Add A Test Case
        When The tester types Description into input with
        """
        Verify that usernames cannot have illegal characters
        """
        When The tester types Steps into input with  
        """
        1. Go to create a new account page 
        2. create several users with each username having 1 illegal character
        3. Make sure none of them are successful
        illegal characters: $, #, /, <, >, (, ), ^
        """
        When The tester presses the submit button 
        Then The test case should appear at the bottom of the table
        Then The test case result should say UNEXECUTED

        #says 'No One' for new test cases, has other info for existing test cases   ORIGINAL CORRECT HERE
#    Scenario: Update a Test Case
#        When The tester presses on details
#        Then A test case modal should appear showing the case ID
#        Then The performed by field should say No One
#        When The tester presses the close button
#        Then The Modal Should be closed

        #check first and last test cases for correct 'Performed by" value as newly added values may be different to existing ones
    Scenario Outline: Update a Test Case
        When The tester presses on details of "<position>" defect
        Then A test case modal should appear showing the case ID
        Then The performed by field should say No One
        When The tester presses the close button
        Then The Modal Should be closed

        Examples:
        |position|
        |2|
        |last()|
    
    Scenario: Edit Existing Case 
        When The tester presses on details 
        Then A test case modal should appear showing the case ID
        When The Tester clicks on edit within the modal 
        Then The Tester should be on the case editor for that case 
        When The tester clicks on the edit button
        When The tester types in "new description" into the description text area
        When The tester types in "new steps" into the steps text area
        When The tester clicks on the automated check mark 
        When The tester selects "ryeGuy" for performed from drop down 
        When The tester selects "FAIL" for test result from drop down 
        When The tester types in "new summary" into the summary text area
        When The tester clicks save on test case page 
        Then A confirmation prompt should appear 
        When The tester clicks on Ok 
        Then An alert says the test case has been saved 

    Scenario: Reset Test Case
        When The tester presses on details
        Then A test case modal should appear showing the case ID
        When The Tester clicks on edit within the modal 
        Then The Tester should be on the case editor for that case 
        When The tester clicks on the edit button
        When The tester types in "updated description" into the description text area
        When The tester types in "updated steps" into the steps text area
        When The tester selects "cavalier89" for performed from drop down 
        When The tester selects "FLAKY" for test result from drop down 
        When The tester types in "updated summary" into the summary text area
        When The tester clicks on the reset button 
        Then The fields should be populated to their old values 
