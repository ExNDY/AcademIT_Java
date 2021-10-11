package kks.oop.temperature.ui.converter;

import kks.oop.temperature.model.scale.Scale;
import kks.oop.temperature.utils.TextUtil;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.text.ParseException;

import static kks.oop.temperature.utils.Constants.getScales;

public class ConverterViewImpl implements ConverterView {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField inputTextField;
    private JTextField outputTextField;
    private JButton swapButton;
    private JButton convertButton;
    private JComboBox<Scale> scaleFromComboBox;
    private JComboBox<Scale> scaleToComboBox;

    public ConverterViewImpl() {
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
        scaleFromComboBox.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(), "Input:"));
        scaleFromComboBox.setSelectedIndex(0);
    }

    private void initScaleToComboBox() {
        scaleToComboBox = new JComboBox<>(getScales);
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
        Icon swapIcon = new ImageIcon("Temperature/src/kks/oop/temperature/resource/ic_swap_24.png");

        swapButton = new JButton("swap");
        swapButton.setIcon(swapIcon);
        swapButton.addActionListener(e -> swapScales());
    }

    private void initConvertButton() {
        Icon convertIcon = new ImageIcon("Temperature/src/kks/oop/temperature/resource/ic_arrow_down_24.png");

        convertButton = new JButton("Convert");
        convertButton.setIcon(convertIcon);
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

    private void swapScales() {
        if (getScaleFrom() == getScaleTo()) {
            return;
        }

        int fromIndex = scaleFromComboBox.getSelectedIndex();
        scaleFromComboBox.setSelectedIndex(scaleToComboBox.getSelectedIndex());
        scaleToComboBox.setSelectedIndex(fromIndex);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "ERROR: Wrong input format", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public double getTemperature() {
        String input = inputTextField.getText().trim();

        double inputValue = 0.0;

        if (input.equals("")) {
            inputTextField.setText(String.valueOf(inputValue));
        } else {
            try {
                inputValue = TextUtil.parseDecimal(input);
                inputTextField.setText(input);
            } catch (NumberFormatException | ParseException ex) {
                try {
                    inputValue = Double.parseDouble(input.replaceAll(",", "."));
                    inputTextField.setText(String.valueOf(inputValue));
                } catch (NumberFormatException e) {
                    outputTextField.setText("");

                    showErrorMessage("Check the entered value.\n Error message: \n" + e);
                }
            }
        }

        return inputValue;
    }

    @Override
    public void setResult(double value) {
        outputTextField.setText(String.valueOf(value));
    }

    @Override
    public Scale getScaleFrom() {
        return (Scale) scaleFromComboBox.getSelectedItem();
    }

    @Override
    public Scale getScaleTo() {
        return (Scale) scaleToComboBox.getSelectedItem();
    }

    @Override
    public void addListener(ActionListener listener) {
        convertButton.addActionListener(listener);
    }
}
