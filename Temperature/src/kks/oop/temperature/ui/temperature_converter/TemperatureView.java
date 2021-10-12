package kks.oop.temperature.ui.temperature_converter;

import kks.oop.temperature.model.scale.Scale;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

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

    public TemperatureView(String title, Scale[] scalesArray) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {

        }

        initComponents(title, scalesArray);
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
        label = new JLabel("Choose scales for convertation:");
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
    }

    private void initConvertButton() {
        Icon convertIcon = new ImageIcon("Temperature/src/kks/oop/temperature/assets/ic_arrow_down_24.png");

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

    public Scale getScaleFrom() {
        return (Scale) scaleFromComboBox.getSelectedItem();
    }

    public Scale getScaleTo() {
        return (Scale) scaleToComboBox.getSelectedItem();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JTextField getInputTextField() {
        return inputTextField;
    }

    public void setInputTextField(JTextField inputTextField) {
        this.inputTextField = inputTextField;
    }

    public JTextField getOutputTextField() {
        return outputTextField;
    }

    public void setOutputTextField(JTextField outputTextField) {
        this.outputTextField = outputTextField;
    }

    public JButton getSwapButton() {
        return swapButton;
    }

    public void setSwapButton(JButton swapButton) {
        this.swapButton = swapButton;
    }

    public JButton getConvertButton() {
        return convertButton;
    }

    public void setConvertButton(JButton convertButton) {
        this.convertButton = convertButton;
    }

    public JComboBox<Scale> getScaleFromComboBox() {
        return scaleFromComboBox;
    }

    public void setScaleFromComboBox(JComboBox<Scale> scaleFromComboBox) {
        this.scaleFromComboBox = scaleFromComboBox;
    }

    public JComboBox<Scale> getScaleToComboBox() {
        return scaleToComboBox;
    }

    public void setScaleToComboBox(JComboBox<Scale> scaleToComboBox) {
        this.scaleToComboBox = scaleToComboBox;
    }
}
