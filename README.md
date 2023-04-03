# Calculator
This is a simple calculator app that allows users to input mathematical expressions and evaluate them. The app supports basic arithmetic operations such as addition, subtraction, multiplication, and division, as well as parentheses for grouping operations.

The app utilizes the Shunting Yard algorithm to convert the input expression into Reverse Polish Notation (RPN) which is then evaluated using a stack-based approach. The app also supports the use of the percent sign (%) as an operator to calculate percentages.

The app is written in Kotlin and follows an object-oriented design pattern. It includes a Calculator class with private functions for tokenizing the input expression, checking operator precedence, performing operations, and applying the percent operator. The evaluateExpression function takes an input expression as a string and returns the evaluated result as a double.

#### Application Screenshot
<img width="219" alt="Screenshot" src="https://user-images.githubusercontent.com/69902076/229521401-439b3f24-28ff-4d33-b031-6152875fced6.JPG">
