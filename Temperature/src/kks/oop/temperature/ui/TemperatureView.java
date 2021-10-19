package kks.oop.temperature.ui;

import kks.oop.temperature.model.scale.Scale;
import kks.oop.temperature.utils.TextUtil;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;

public class TemperatureView {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField inputTextField;
    private JTextField outputTextField;
    private JButton swapButton;
    private JButton convertButton;
    private JComboBox<Scale> scaleFromComboBox;
    private JComboBox<Scale> scaleToComboBox;

    private ActionListener actionListener;

    public TemperatureView(String title, Scale[] scalesArray) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {

            }

            initComponents(title, scalesArray);
        });
    }

    private void initComponents(String title, Scale[] scalesArray) {
        initFrame(title);
        initPanel();
        initLabel();
        initScaleFromComboBox(scalesArray);
        initScaleToComboBox(scalesArray);
        initInputTextField();
        initOutputTextField();
        initSwapButton();
        initConvertButton();
        initLayout();

        frame.pack();
        frame.setVisible(true);
    }

    private void initFrame(String title) {
        frame = new JFrame(title);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        frame.add(panel);
    }

    private void initLabel() {
        label = new JLabel("Choose scales for conversion:");
    }

    private void initScaleFromComboBox(Scale[] scalesArray) {
        scaleFromComboBox = new JComboBox<>(scalesArray);
        scaleFromComboBox.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(), "Input:"));
        scaleFromComboBox.setSelectedIndex(0);
    }

    private void initScaleToComboBox(Scale[] scalesArray) {
        scaleToComboBox = new JComboBox<>(scalesArray);
        scaleToComboBox.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(), "Output:"));
        scaleToComboBox.setSelectedIndex(1);
    }

    private void initInputTextField() {
        inputTextField = new JTextField();
        inputTextField.setHorizontalAlignment(SwingConstants.CENTER);
        inputTextField.setText("0.0");
    }

    private void initOutputTextField() {
        outputTextField = new JTextField();
        outputTextField.setEditable(false);
        outputTextField.setHorizontalAlignment(SwingConstants.CENTER);
        outputTextField.setText("0.0");
    }

    private void initSwapButton() {
        Icon swapIcon = new ImageIcon("Temperature/src/kks/oop/temperature/assets/ic_swap_24.png");

        swapButton = new JButton("swap");
        swapButton.setIcon(swapIcon);
        swapButton.addActionListener(actionListener);
    }

    private void initConvertButton() {
        Icon convertIcon = new ImageIcon("Temperature/src/kks/oop/temperature/assets/ic_arrow_down_24.png");

        convertButton = new JButton("Convert");
        convertButton.setIcon(convertIcon);
        convertButton.addActionListener(actionListener);
    }

    private void initLayout() {
        GroupLayout groupLayout = new GroupLayout(panel);

        panel.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(label)
                .addGroup(groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(scaleFromComboBox)
                                .addComponent(swapButton)
                                .addComponent(scaleToComboBox))
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(inputTextField)
                                .addComponent(convertButton)
                                .addComponent(outputTextField)))
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(label)
                .addGroup(groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(scaleFromComboBox)
                                .addComponent(inputTextField))
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(swapButton)
                                .addComponent(convertButton))
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(scaleToComboBox)
                                .addComponent(outputTextField)))
        );
    }

    public void addActionListener(ActionListener listener) {
        actionListener = listener;
    }

    public Scale getScaleFrom() {
        return (Scale) scaleFromComboBox.getSelectedItem();
    }

    public Scale getScaleTo() {
        return (Scale) scaleToComboBox.getSelectedItem();
    }

    public JButton getSwapButton() {
        return swapButton;
    }

    public JButton getConvertButton() {
        return convertButton;
    }

    public double getTemperature() {
        return parseInputValue(inputTextField.getText());
    }

    public void setConversionResult(double resultConversion) {
        outputTextField.setText(String.valueOf(TextUtil.roundToNearestHundred(resultConversion)));
    }

    public void swapScales() {
        if (getScaleFrom().equals(getScaleTo())) {
            return;
        }

        int fromIndex = scaleFromComboBox.getSelectedIndex();
        scaleFromComboBox.setSelectedIndex(scaleToComboBox.getSelectedIndex());
        scaleToComboBox.setSelectedIndex(fromIndex);
    }

    private double parseInputValue(String input) {
        try {
            return TextUtil.parseStringToDouble(input);
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Check the entered value: \"" + input + "\"", "ERROR: Wrong input format", JOptionPane.ERROR_MESSAGE);
        }

        return 0.0;
    }
}
