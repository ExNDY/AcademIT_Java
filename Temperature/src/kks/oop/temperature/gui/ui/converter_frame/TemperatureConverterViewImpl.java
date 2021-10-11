package kks.oop.temperature.gui.ui.converter_frame;

import kks.oop.temperature.gui.model.scale.Scale;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static kks.oop.temperature.gui.utils.Constants.getScales;

public class TemperatureConverterViewImpl implements TemperatureConverterView {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField inputTextField;
    private JTextField outputTextField;
    private JButton swapButton;
    private JButton convertButton;

    private JComboBox<Scale> scaleFromComboBox;
    private JComboBox<Scale> scaleToComboBox;

    public TemperatureConverterViewImpl() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {

        }

        initFrame();
        initPanel();
        initLabel();
        initScaleFromComboBox();
        initScaleToComboBox();
        initInputTextField();
        initOutputTextField();
        initSwapButton();
        initConvertButton();
        initLayout();

        frame.pack();
        frame.setVisible(true);
    }

    private void initFrame() {
        frame = new JFrame("Temperature converter");
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
        label = new JLabel("Choose scales for convertation:");
    }

    private void initScaleFromComboBox() {
        scaleFromComboBox = new JComboBox<>(getScales);
        scaleFromComboBox.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(), "Input"));
        scaleFromComboBox.setSelectedIndex(0);
    }

    private void initScaleToComboBox() {
        scaleToComboBox = new JComboBox<>(getScales);
        scaleToComboBox.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(), "Input"));
        scaleToComboBox.setSelectedIndex(1);
    }

    private void initInputTextField() {
        inputTextField = new JTextField();
        inputTextField.setText("");
    }

    private void initOutputTextField() {
        outputTextField = new JTextField();
        outputTextField.setEditable(false);
        outputTextField.setText("");
    }

    private void initSwapButton() {
        swapButton = new JButton("<->");
        swapButton.addActionListener(e -> swapScales());
    }

    private void initConvertButton() {
        convertButton = new JButton("Convert");
    }

    private void initLayout() {
        GroupLayout groupLayout = new GroupLayout(panel);

        panel.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(label)
                .addGroup(groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(scaleFromComboBox)
                                .addComponent(swapButton)
                                .addComponent(scaleToComboBox))
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(inputTextField)
                                .addComponent(convertButton)
                                .addComponent(outputTextField)))
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(label)
                .addGroup(groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(scaleFromComboBox)
                                .addComponent(inputTextField))
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(swapButton)
                                .addComponent(convertButton))
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(scaleToComboBox)

                                .addComponent(outputTextField)))
        );
    }

    private void swapScales() {
        if (getFrom() == getTo()) {
            return;
        }

        int fromIndex = scaleFromComboBox.getSelectedIndex();
        scaleFromComboBox.setSelectedIndex(scaleToComboBox.getSelectedIndex());
        scaleToComboBox.setSelectedIndex(fromIndex);
    }

    @Override
    public double getTemperature() {
        String input = inputTextField.getText().trim();

        double inputValue = 0.0;

        if (input.equals("")) {
            inputTextField.setText(String.valueOf(inputValue));
        } else {
            try {
                inputValue = Double.parseDouble(input);
                inputTextField.setText(input);
            } catch (NumberFormatException ex) {
                try {
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());

                    char correctSeparator = symbols.getDecimalSeparator();
                    char incorrectSeparator = correctSeparator == ',' ? '.' : ',';

                    inputValue = Double.parseDouble(input.replace(incorrectSeparator, correctSeparator));
                    inputTextField.setText(String.valueOf(inputValue));
                } catch (NumberFormatException ignored) {

                }
            }
        }

        return inputValue;
    }

    @Override
    public void setValue(double value) {
        outputTextField.setText(String.valueOf(value));
    }

    @Override
    public Scale getFrom() {
        return (Scale) scaleFromComboBox.getSelectedItem();
    }

    @Override
    public Scale getTo() {
        return (Scale) scaleToComboBox.getSelectedItem();
    }

    @Override
    public void addListener(ActionListener listener) {
        convertButton.addActionListener(listener);
    }
}
