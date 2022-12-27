@Matrix
Feature: Manager & Matrix

# Declarative Style

    #csv has structure of array with name = requirements and cells = defects. + secondary related array called status
    #when any test fails that failure is written to the array of scenario name in a new spot and notes in secondary array in same spot
    #those arrays are then read into csv as rows(or columns for extra complexity) With array name as header and each cell concatenated into one big cell with all failure instances. status in cell below or separate
    #final csv has strucure: head = Requirement desc, req tests, test status
    #req desc = manual boiler plate, req tests = each scenario gets a number and upon completion or failure at assert gets written to array
    Background: 
        Given The manager is logged in as a manager 
        Given The manager is on the home page

    Scenario: Create a New Matrix
        When The manager chooses to create a new matrix
        When The manager creates a title for the matrix 
        When The manager adds requirements to the matrix
        When The manager saves the matrix 
        Then An alert with a success message should appear

    Scenario: Update Defects
        Given The manager is on the matrix homepage
        Given The manager has selected the matrix
        When The manager adds a defect
        When The manager confirms their changes 
        Then Then the matrix should saved  

    Scenario: Update Test Cases
        Given The manager is on the matrix homepage
        Given The manager has selected the matrix
        When The manager adds a Test Cases
        When The manager confirms their changes 
        Then Then the matrix should saved  


