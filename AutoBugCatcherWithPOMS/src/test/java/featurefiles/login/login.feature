Feature: Login

    Scenario: Login Correct Username Wrong Password
        Given The employee is on the login page
        When The employee types in g8tor into the username input
        When The employee types in chomp!! into the password input
        When The employee clicks on the login button 
        Then The employee should see an alert saying that password was incorrect

    Scenario: Login Wrong Username Correct Password
        Given The employee is on the login page
        When The employee types in sicEmDawgs into the username input
        When The employee types in chomp! into the password input
        When The employee clicks on the login button
        Then The employee should see an alert saying that Username was incorrect
        # Username capitalized to match text alert capitalization

    Scenario Outline: Login with correct credentials
        Given The employee is on the login page
        When  The employee types "<username>" into username input
        When The employee types "<password>" into password input
        When The employee clicks on the login button
        Then the employee should be on the "<role>" page
        Then The employee should see their name "<fname>" "<lname>" on the home page

        Examples:
            | username   | password | role    | fname   | lname     |
            | g8tor      | chomp!   | Manager | Patty   | Pastiche  |
            | ryeGuy     | coolbeans| Tester  | Fakey   | McFakeFace|
            | cavalier89 | alucard  | Tester  | Dracula | Fangs     |

    Scenario: Login Empty Username and Password
        Given The employee is on the login page
        When The employee clicks on the login button
        Then The employee should see an alert saying that Username was incorrect