import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Properties;
import java.util.Random;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class ArithmeticPracticeApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginForm::new);
    }
}

class LoginForm extends JFrame {
    private final JDatePickerImpl datePicker;

    public LoginForm() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());

        JLabel lblDOB = new JLabel("Select your Date of Birth:");
        lblDOB.setHorizontalAlignment(SwingConstants.CENTER);

        // Configure Date Picker
        UtilDateModel model = new UtilDateModel();
        model.setDate(2000, 0, 1); // Set default date (e.g., January 1, 2000)
        model.setSelected(true); // Ensure the date is marked as selected
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> validateAge());

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(lblDOB);
        panel.add(datePicker);
        panel.add(btnLogin);

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void validateAge() {
        try {
            // Retrieve date directly from the UtilDateModel
            UtilDateModel model = (UtilDateModel) datePicker.getModel();

            // Ensure a date is selected
            if (!model.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please select a valid date of birth.");
                return;
            }

            // Convert to LocalDate
            LocalDate dob = LocalDate.of(model.getYear(), model.getMonth() + 1, model.getDay());

            // Calculate age
            int age = Period.between(dob, LocalDate.now()).getYears();

            // Check age conditions
            if (age < 4) {
                JOptionPane.showMessageDialog(this, "Oops! You are too young for this.");
            } else if (age > 8) {
                JOptionPane.showMessageDialog(this, "Ooh, this is too basic for you.");
            } else {
                dispose(); // Close login form
                new ArithmeticForm();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please select a valid date of birth.");
        }
    }
}

class ArithmeticForm extends JFrame {
    private JComboBox<String> gradeDropdown;
    private JLabel lblNum1, lblNum2, lblQuestion;
    private JTextField txtOperator, txtAnswer;
    private int num1, num2, trials;

    public ArithmeticForm() {
        setTitle("Arithmetic Practice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new GridLayout(6, 2));

        // Components
        JLabel lblGrade = new JLabel("Select Grade:");
        gradeDropdown = new JComboBox<>(new String[]{"Grade 1", "Grade 2", "Grade 3"});

        lblNum1 = new JLabel("Num 1:");
        lblNum2 = new JLabel("Num 2:");
        lblQuestion = new JLabel("Question:");

        txtOperator = new JTextField();
        txtAnswer = new JTextField();

        JButton btnGenerate = new JButton("Generate Random Numbers");
        JButton btnSubmit = new JButton("Submit");
        JButton btnExit = new JButton("Exit");

        // Add components
        add(lblGrade);
        add(gradeDropdown);
        add(new JLabel("Number 1:"));
        add(lblNum1);
        add(new JLabel("Number 2:"));
        add(lblNum2);
        add(new JLabel("Operator (+, -, *, /):"));
        add(txtOperator);
        add(lblQuestion);
        add(txtAnswer);
        add(btnGenerate);
        add(btnSubmit);
        add(btnExit);

        // Action Listeners
        btnGenerate.addActionListener(e -> generateNumbers());
        btnSubmit.addActionListener(e -> validateAnswer());
        btnExit.addActionListener(e -> exitApplication());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateNumbers() {
        String grade = (String) gradeDropdown.getSelectedItem();
        Random rand = new Random();

        if ("Grade 1".equals(grade)) {
            num1 = rand.nextInt(9) + 1; // 1 to 9
            num2 = rand.nextInt(9) + 1; // 1 to 9
        } else if ("Grade 2".equals(grade) || "Grade 3".equals(grade)) {
            num1 = rand.nextInt(90) + 10; // 10 to 99
            num2 = rand.nextInt(90) + 10; // 10 to 99
        }

        lblNum1.setText(String.valueOf(num1));
        lblNum2.setText(String.valueOf(num2));
        lblQuestion.setText("");
        txtOperator.setText("");
        txtAnswer.setText("");
        trials = 0;
    }

    private void validateAnswer() {
        if (txtOperator.getText().isEmpty() || txtAnswer.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inputs cannot be empty.");
            return;
        }

        String grade = (String) gradeDropdown.getSelectedItem();
        char operator = txtOperator.getText().charAt(0);
        int userAnswer;

        try {
            userAnswer = Integer.parseInt(txtAnswer.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            return;
        }

        int correctAnswer = 0;
        boolean validOperator = false;

        switch (operator) {
            case '+':
                correctAnswer = num1 + num2;
                validOperator = "Grade 1".equals(grade) || "Grade 2".equals(grade) || "Grade 3".equals(grade);
                break;
            case '-':
                correctAnswer = num1 - num2;
                validOperator = "Grade 2".equals(grade) || "Grade 3".equals(grade);
                break;
            case '*':
                correctAnswer = num1 * num2;
                validOperator = "Grade 3".equals(grade);
                break;
            case '/':
                if (num2 != 0) correctAnswer = num1 / num2;
                validOperator = "Grade 3".equals(grade);
                break;
        }

        if (!validOperator) {
            JOptionPane.showMessageDialog(this, "Invalid operator for selected grade.");
            return;
        }

        if (userAnswer == correctAnswer) {
            JOptionPane.showMessageDialog(this, "Correct!");
            trials = 0;
        } else {
            trials++;
            if (trials >= 5) {
                JOptionPane.showMessageDialog(this, "Try again! The correct answer is " + correctAnswer + ".");
                trials = 0;
            } else {
                JOptionPane.showMessageDialog(this, "Try again.");
            }
        }
    }

    private void exitApplication() {
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to quit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}

class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private final java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Object stringToValue(String text) throws java.text.ParseException {
        return LocalDate.parse(text, dateFormatter);
    }

    @Override
    public String valueToString(Object value) throws java.text.ParseException {
        if (value != null && value instanceof java.util.Calendar) {
            java.util.Calendar cal = (java.util.Calendar) value;
            LocalDate date = LocalDate.of(
                    cal.get(java.util.Calendar.YEAR),
                    cal.get(java.util.Calendar.MONTH) + 1,
                    cal.get(java.util.Calendar.DAY_OF_MONTH)
            );
            return dateFormatter.format(date);
        }
        return "";
    }
}
