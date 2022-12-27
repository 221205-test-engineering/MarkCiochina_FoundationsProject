@ReportDefect
Feature: Defect Reporting

    Scenario Outline: Report New Defect
        Given The employee is on the Defect Reporter Page
        When The employee selects todays date
        When The employee types in Description with
            """
            Users are able to create multiple accounts using the same username but
            with a different password.
            """
        When The employee types in Steps with
            """
            1. User creates a new account
            2. User reloads the new user page
            3. User creates an account with same username but different password
            """
        When The employee selects "<priority>" priority
        When The employee selects "<severity>" severity
        When The employee clicks the report button
        Then There should be a confirmation box
        When The employee clicks Ok
        Then A modal should appear with a Defect ID
        When The employee clicks close
        Then The modal should disappear

        Examples:
            | priority | severity |
            | Medium  | Medium  |


    # this scenario should fail: textarea does not have a min limit
    Scenario: Steps too short
        Given The employee is on the Defect Reporter Page
        When The employee selects todays date
        # Bug fixed here. Originally "Description" below was "description" leading to two methods of the same name having an error
        #BUT not being recognized as the same '@When' step, leading to compiler thinking something's missing
        When The employee types in Description with
            """
            Users are able to create multiple accounts using the same username but
            with a different password.
            """
        When The employee types in Steps with
            """
            meh
            """
        When The employee selects high priority
        When The employee selects low severity
        When The employee clicks the report button
        Then No confirmation dialog appears



        #Pass with restrictions
    Scenario: Description too short
        Given The employee is on the Defect Reporter Page
        When The employee selects todays date
        #Description again originally lower case
        When The employee types in Description with
            """
            meh
            """
        #Same trick here with steps being both capitalized and uncapitalized
        When The employee types in Steps with
            """
            1. User creates a new account
            2. User reloads the new user page
            3. User creates an account with same username but different password
            """
        When The employee selects high priority
        When The employee selects low severity
        When The employee clicks the report button
        Then No confirmation dialog appears


    Scenario: No Date Selected
        Given The employee is on the Defect Reporter Page
        #Description uncapitalized again
        When The employee types in Description with
            """
            Users are able to create multiple accounts using the same username but
            with a different password.
            """
        #Steps uncapitalized again
        When The employee types in Steps with
            """
            1. User creates a new account
            2. User reloads the new user page
            3. User creates an account with same username but different password
            """
        When The employee selects high priority 
        When The employee selects low severity 
        When The employee clicks the report button 
        Then No confirmation dialog appears