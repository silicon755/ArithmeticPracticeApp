
# Arithmetic Practice Application

Welcome to the Arithmetic Practice Application! This is a simple, interactive Windows Forms Application designed to help young pupils practice basic arithmetic operations. The program adapts to the pupil's grade level, presenting arithmetic problems tailored to their learning stage.

# Program Description

This application enables children to practice arithmetic operations (addition, subtraction, multiplication, and division) in an engaging way. The operations are customized based on the grade level of the pupil, ensuring age-appropriate exercises.




## Features

- Login Form:
    - Captures the pupil's date of birth using a DateTime Picker.
    - Determines the pupil's age and validates whether they are eligible:
        - Below 4 years: Displays “Oops! You are too young for this.”
        - Above 8 years: Displays “Ooh, this is too basic for you.”
        - Ages 4–8: Proceeds to the arithmetic operations form.
- Arithmetic Operations Form:
    - Grade Selection:
    - Dropdown menu for selecting the grade level:
        - Grade 1: Single-digit addition only.
        - Grade 2: Two-digit addition and subtraction.
        - Grade 3: Two-digit addition, subtraction, multiplication, and division.
- Generate Random Numbers:
    - Generates two random numbers based on the selected grade.
    - Displays the numbers on labels (read-only).
    - Clears previous input and resets the interface.
- Input Fields:
    - A text box for entering the arithmetic operator (+, -, *, /).
    - A text box for entering the answer.
    - Validates both fields to ensure they are not empty and contain valid input.
- Interactive Questions:
    - Poses a question to the pupil based on the selected operation and numbers.
- Provides feedback:
    - Displays "Correct" for the right answer.
    - Displays "Try Again" for wrong answers.
    - After 5 incorrect attempts, shows the correct answer.
- Unlimited Attempts:
    - Pupils can generate new problems and solve as many as they like.
- Exit Button:

    - Displays a confirmation message box:
        - Yes: Exits the application.
        - No: Retains the current interface.

# Program Rules
- Grade-specific Operations:
    - Grade 1: Addition of single-digit numbers only.
    - Grade 2: Addition and subtraction of two-digit numbers.
    - Grade 3: Addition, subtraction, multiplication, and division of two-digit numbers.
- Error Handling:
    - Ensures operator and answer fields are not empty.
    - Displays “This cannot be empty” for invalid input.
    - Handles invalid operators and division by zero gracefully.

# How It Works
- Launch the application.
- Enter the pupil’s date of birth in the login form.
- Depending on the age:
    - Below 4: Shows a message that the pupil is too young.
    - Above 8: Shows a message that the program is too basic.
    - Ages 4–8: Proceeds to the arithmetic operations form.
- Select the grade level and click Generate Random Numbers.
- Enter the operator and answer to solve the posed arithmetic problem.
- Continue solving problems or exit the application using the Exit button.

# Technologies Used
- Language: Java
- GUI Framework: Swing
- Date Picker: JDatePicker




## Run Locally

Clone the project

```bash
  git clone https://github.com/your-repository/arithmetic-practice-app.git

```

Compile the program

```bash
  javac -cp "lib/JDatePicker.jar" *.java


```

Run the application

```bash
 java -cp ".;lib/JDatePicker.jar" ArithmeticPracticeApp

```

