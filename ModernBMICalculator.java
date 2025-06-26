import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class ModernBMICalculator extends JFrame {
    // Modern color palette
    private static final Color PRIMARY_COLOR = new Color(15, 90, 100);
    private static final Color SECONDARY_COLOR = new Color(236, 240, 241);
    private static final Color BUTTON_COLOR = new Color(34, 139, 34); 	// Dark green for better contrast
    private static final Color TEXT_COLOR = new Color(50, 50, 50);
    private static final Color PLACEHOLDER_COLOR = new Color(150, 150, 150);
   
    // UI Components
    private JComboBox<String> unitComboBox, genderComboBox;
    private JTextField weightField, heightField;
    private JLabel resultLabel, categoryLabel, adviceLabel;
    private JButton calculateButton;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public ModernBMICalculator() {
        setTitle("Dope BMI Calculator");

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(SECONDARY_COLOR);
       
        // Build UI sections
        mainPanel.add(createHeader(), BorderLayout.NORTH);
        mainPanel.add(createInputPanel(), BorderLayout.CENTER);
        mainPanel.add(createResultPanel(), BorderLayout.SOUTH);
       
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		headerPanel.setBackground(SECONDARY_COLOR);
		headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
       
        // Main title
		JLabel titleLabel = new JLabel("DOPE BMI CALCULATOR");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(PRIMARY_COLOR);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Explanation text with some personality
    JLabel explanationLabel = new JLabel(
        "<html><div style='text-align: center; width: 420px; color:#555; font-style:italic'>" +
        "Body Mass Index measures your Thiccness level<br>" +
        "(Not perfect, but it generally works, you cav'?)</div></html>"
    );
		explanationLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));  // Modify text
		explanationLabel.setForeground(new Color(80, 80, 80)); 			// Dark gray
		explanationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
		headerPanel.add(titleLabel);
		headerPanel.add(Box.createRigidArea(new Dimension(0, 5))); 		// Small space
		headerPanel.add(explanationLabel);
    
		return headerPanel;

    }

private JPanel createInputPanel() {
    JPanel inputPanel = new JPanel(new GridLayout(4, 2, 15, 15));
    inputPanel.setBackground(SECONDARY_COLOR);
   
    // Unit selection
    JLabel unitLabel = createStyledLabel("Unit System:");
    inputPanel.add(unitLabel);
    
    unitComboBox = new JComboBox<>(new String[]{"Choose...", "Metric (kg, m)", "Imperial (lbs, in)", "I dunno"});
    unitComboBox.setFont(new Font("Segoe UI", Font.BOLD, 16));
    unitComboBox.setForeground(PRIMARY_COLOR);
    inputPanel.add(unitComboBox);
   
    // Gender selection
    JLabel genderLabel = createStyledLabel("Gender:");
    inputPanel.add(genderLabel);
    
    genderComboBox = new JComboBox<>(new String[]{"Choose...", "Male", "Female", "Another gender I guess..."});
    genderComboBox.setFont(new Font("Segoe UI", Font.BOLD, 16));
    genderComboBox.setForeground(PRIMARY_COLOR);
    inputPanel.add(genderComboBox);
   
    // Weight input
    JLabel weightLabel = createStyledLabel("Weight:");
    inputPanel.add(weightLabel);
    
    weightField = new JTextField();
    weightField.setFont(new Font("Segoe UI", Font.BOLD, 14));
    setPlaceholder(weightField, "e.g., 70 (kg) or 150 (lbs)");
    inputPanel.add(weightField);
   
    // Height input
    JLabel heightLabel = createStyledLabel("Height:");
    inputPanel.add(heightLabel);
    
    heightField = new JTextField();
    heightField.setFont(new Font("Segoe UI", Font.BOLD, 14));
    setPlaceholder(heightField, "e.g., 1.75 (m) or 68 (in)");
    inputPanel.add(heightField);
       
        return inputPanel;
    }
	
	
// Helper method to create styled labels
private JLabel createStyledLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Bold font
    label.setForeground(PRIMARY_COLOR); // Primary color
    return label;
}


   private void setPlaceholder(JTextField field, String placeholder) {
        field.setForeground(PLACEHOLDER_COLOR);
        field.setText(placeholder);
       
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(TEXT_COLOR);
                }
            }
           
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(PLACEHOLDER_COLOR);
                    field.setText(placeholder);
                }
            }
        });
    }

    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        resultPanel.setBackground(SECONDARY_COLOR);
       
        resultLabel = new JLabel("Your BMI: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        resultLabel.setForeground(PRIMARY_COLOR);
       
        categoryLabel = new JLabel("Category: ", SwingConstants.CENTER);
        categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        categoryLabel.setForeground(TEXT_COLOR);
		adviceLabel = new JLabel(" ", SwingConstants.CENTER);

		adviceLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		adviceLabel.setForeground(TEXT_COLOR);
		adviceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        calculateButton = new JButton("CALCULATE BMI");
		calculateButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        styleCalculateButton();
       
        resultPanel.add(resultLabel);
        resultPanel.add(categoryLabel);
        resultPanel.add(calculateButton);
		resultPanel.add(adviceLabel);
       
        return resultPanel;
    }

    private void styleCalculateButton() {
    calculateButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    calculateButton.setBackground(BUTTON_COLOR);
    calculateButton.setForeground(PRIMARY_COLOR); // Changed from Color.WHITE to Color.BLACK
    calculateButton.setFocusPainted(false);
    calculateButton.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
   
    // Hover effects
    calculateButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            calculateButton.setBackground(BUTTON_COLOR.brighter());
            calculateButton.setForeground(Color.BLACK); // Ensure stays black on hover
        }
       
        @Override
        public void mouseExited(MouseEvent e) {
            calculateButton.setBackground(BUTTON_COLOR);
            calculateButton.setForeground(PRIMARY_COLOR); // Ensure stays black
        }
    });
   
    calculateButton.addActionListener(e -> calculateBMI());
}

    private void calculateBMI() {
        try {
            int unit = unitComboBox.getSelectedIndex();
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());

            if (weight <= 0 || height <= 0) {
                throw new IllegalArgumentException("Values must be positive");
            }

            double bmi = (unit == 0)
                ? weight / (height * height)
                : (weight / (height * height)) * 703;
           
            displayResults(bmi);
           
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers. Examples are even chilling there for you to cav'", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayResults(double bmi) {
        resultLabel.setText("Your BMI: " + decimalFormat.format(bmi));
       
        String category;
        Color color;
		String advice;
		
		if (bmi < 16.0) {
			category = "Severely Underweight (Starvation)";
			color = new Color(100, 180, 255); // Light blue
			advice = "Urgent medical attention required for nutritional rehabilitation";
	} else if (bmi >= 16.0 && bmi < 17.0) {
			category = "Moderately Underweight";
			color = new Color(52, 152, 219); // Medium blue
			advice = "Consult doctor and nutritionist for supervised weight gain plan";
	} else if (bmi >= 17.0 && bmi < 18.5) {
			category = "Mildly Underweight";
			color = new Color(100, 200, 255); // Light blue
			advice = "Increase calorie intake with nutrient-dense foods and strength training";
	} else if (bmi >= 18.5 && bmi < 25.0) {
			category = "Normal (Healthy Weight)";
			color = new Color(46, 204, 113); // Green
			advice = "Maintain balanced diet and regular physical activity";
	} else if (bmi >= 25.0 && bmi < 30.0) {
			category = "Overweight (Pre-obese)";
			color = new Color(255, 215, 0); // Gold
			advice = "Reduce refined carbs/sugars, increase cardio exercise";
	} else if (bmi >= 30.0 && bmi < 35.0) {
			category = "Obese Class I (Moderate)";
			color = new Color(230, 126, 34); // Orange
			advice = "Medical weight management program recommended";
	} else if (bmi >= 35.0 && bmi < 40.0) {
			category = "Obese Class II (Severe)";
			color = new Color(231, 76, 60); // Red
			advice = "Immediate supervised clinical intervention needed";
	} else {
			category = "Obese Class III (Very Severe/Morbid)";
			color = new Color(192, 57, 43); // Dark red
			advice = "Yasis! You NEED URGENT and Immediate medical intervention ekse. Bariatric surgery consultation or something";
	}
       
        if (bmi < 16.0) {
            category = "Severely Underweight";
            color = new Color(100, 180, 255);
            advice = "Consult a doctor and nutritionist for weight gain plan";
        } else if (bmi >= 16.1 && bmi < 18.5) {
            category = "Underweight";
            color = new Color(52, 152, 219);
            advice = "Increase calorie intake with nutrient-dense foods";
        } else if (bmi >= 18.6 && bmi < 25) {
            category = "Normal Weight";
            color = new Color(46, 204, 113);
            advice = "Maintain balanced diet and regular exercise";
        } else if (bmi >= 26 && bmi < 30) {
            category = "Overweight";
            color = new Color(241, 196, 15);
            advice = "Reduce processed foods, increase physical activity";
        } else if (bmi >= 30 && bmi < 35) {
            category = "Moderately Obese (Class I)";
            color = new Color(230, 126, 34);
            advice = "Seek professional guidance for weight management";
        } else if (bmi >= 35 && bmi < 40) {
            category = "Severely Obese (Class II)";
            color = new Color(231, 76, 60);
            advice = "Medical supervision recommended for weight loss";
        } else {
            category = "Morbidly Obese (Class III)";
            color = new Color(192, 57, 43);
            advice = "Yasis! You NEED URGENT and Immediate medical intervention ekse";
        }
       
        categoryLabel.setText("Category: " + category);
        categoryLabel.setForeground(color);
        adviceLabel.setText("<html><center>" + advice + "</center></html>");


    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        SwingUtilities.invokeLater(() -> new ModernBMICalculator());
    }
}