package bsu.rfe.java.group8.lab2.Kedyshko.var7;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame
{
    // РАЗМЕРЫ ОКНА
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    // ТЕКСТОВЫЕ ПОЛЯ
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private JTextField textFieldMemory1;
    private JTextField textFieldMemory2;
    private JTextField textFieldMemory3;

    // ГРУППЫ РАДИО-КНОПОК С КОНТЕЙНЕРАМИ
    private ButtonGroup radioButtonsFormula = new ButtonGroup();
    private Box horizontalBoxFormulaType = Box.createHorizontalBox();
    private ButtonGroup radioButtonsMemory = new ButtonGroup();
    private Box horizontalBoxMemoryType = Box.createHorizontalBox();

    //ПЕРЕКЛЮЧАТЕЛИ
    private int formulaId = 1;
    private int memoryId = 1;

    //ПЕРЕМЕННЫЕ
    private Double result;
    private Double memory1 = 0.0;
    private Double memory2 = 0.0;
    private Double memory3 = 0.0;

    // ФОРМУЛЫ ДЛЯ РАССЧЁТОВ
    public Double formula1(Double x, Double y, Double z)
    {
        return Math.sin(Math.log(y) + Math.sin(Math.PI * y * y)) * Math.pow(x * x + Math.sin(z)+ Math.exp(Math.cos(z)), 1.0/4);
    }
    public Double formula2(Double x, Double y, Double z)
    {
        return Math.pow(Math.cos(Math.exp(x)) + Math.log((1 + y) * (1 + y)) + Math.sqrt(Math.exp(Math.cos(x)) + Math.pow(Math.sin(Math.PI * z), 2)) + Math.sqrt(1 / x) + Math.cos(y * y), Math.sin(z));
    }

    // ДОБАВЛЕНИЕ КНОПОК НА ПАНЕЛЬ
    private void addRadioButtonFormula(String buttonName, final int formulaId)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtonsFormula.add(button);
        horizontalBoxFormulaType.add(button);
    }

    private void addRadioButtonMemory(String buttonName, final int memoryId)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent em)
            {
                MainFrame.this.memoryId = memoryId;
            }
        });
        radioButtonsMemory.add(button);
        horizontalBoxMemoryType.add(button);
    }

    // КОНСТРУКТОР
    public MainFrame()
    {
        // РАЗМЕЩЕНИЕ ОКНА ПО ЦЕНТРУ ЭКРАНА
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);

        // ДОБАВЛЕНИЕ РАДИО-КНОПОК
        horizontalBoxFormulaType.add(Box.createHorizontalGlue());
        addRadioButtonFormula("Формула 1", 1);
        addRadioButtonFormula("Формула 2", 2);
        radioButtonsFormula.setSelected(radioButtonsFormula.getElements().nextElement().getModel(), true);
        horizontalBoxFormulaType.add(Box.createHorizontalGlue());
        horizontalBoxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        // СОЗДАНИЕ ПОЛЕЙ ВВОДА
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 50);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 50);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 50);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box horizontalBoxVariables = Box.createHorizontalBox();
        horizontalBoxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        horizontalBoxVariables.add(Box.createHorizontalGlue());
        horizontalBoxVariables.add(labelForX);
        horizontalBoxVariables.add(Box.createHorizontalStrut(10));
        horizontalBoxVariables.add(textFieldX);
        horizontalBoxVariables.add(Box.createHorizontalStrut(20));
        horizontalBoxVariables.add(labelForY);
        horizontalBoxVariables.add(Box.createHorizontalStrut(10));
        horizontalBoxVariables.add(textFieldY);
        horizontalBoxVariables.add(Box.createHorizontalStrut(20));
        horizontalBoxVariables.add(labelForZ);
        horizontalBoxVariables.add(Box.createHorizontalStrut(10));
        horizontalBoxVariables.add(textFieldZ);
        horizontalBoxVariables.add(Box.createHorizontalGlue());

        // СОЗДАНИЕ ПОЛЯ ВЫВОДА
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 50);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        Box horizontalBoxResult = Box.createHorizontalBox();
        horizontalBoxResult.add(Box.createHorizontalGlue());
        horizontalBoxResult.add(labelForResult);
        horizontalBoxResult.add(Box.createHorizontalStrut(10));
        horizontalBoxResult.add(textFieldResult);
        horizontalBoxResult.add(Box.createHorizontalGlue());
        horizontalBoxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        // СОЗДАНИЕ КНОПОК ПОЛЯ ПАМЯТИ
        JButton buttonMemoryClear = new JButton("MC");
        buttonMemoryClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent em) {
                if (memoryId == 1) {
                    memory1 = 0.0;
                    textFieldMemory1.setText(memory1.toString());
                } else if (memoryId == 2) {
                    memory2 = 0.0;
                    textFieldMemory2.setText(memory2.toString());
                } else {
                    memory3 = 0.0;
                    textFieldMemory3.setText(memory3.toString());
                }
            }
        });

        JButton buttonMemoryPlus = new JButton("M+");
        buttonMemoryPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent em) {
                result = Double.parseDouble(textFieldResult.getText());
                if (memoryId == 1) {
                    memory1 += result;
                    textFieldMemory1.setText(memory1.toString());
                } else if (memoryId == 2) {
                    memory2 += result;
                    textFieldMemory2.setText(memory2.toString());
                } else {
                    memory3 += result;
                    textFieldMemory3.setText(memory3.toString());
                }
            }
        });

        Box horizontalBoxMemoryButton = Box.createHorizontalBox();
        horizontalBoxMemoryButton.add(Box.createHorizontalGlue());
        horizontalBoxMemoryButton.add(buttonMemoryClear);
        horizontalBoxMemoryButton.add(Box.createHorizontalStrut(30));
        horizontalBoxMemoryButton.add((buttonMemoryPlus));
        horizontalBoxMemoryButton.add(Box.createHorizontalGlue());
        horizontalBoxMemoryButton.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        // СОЗДАНИЕ ПОЛЯ ПАМЯТИ
        JLabel labelForMemory1 = new JLabel("Ячейка 1");
        textFieldMemory1 = new JTextField(memory1.toString(), 50);
        textFieldMemory1.setMaximumSize(textFieldMemory1.getPreferredSize());
        JLabel labelForMemory2 = new JLabel("Ячейка 2");
        textFieldMemory2 = new JTextField(memory2.toString(), 50);
        textFieldMemory2.setMaximumSize(textFieldMemory2.getPreferredSize());
        JLabel labelForMemory3 = new JLabel("Ячейка 3");
        textFieldMemory3 = new JTextField(memory3.toString(), 50);
        textFieldMemory3.setMaximumSize(textFieldMemory3.getPreferredSize());

        Box horizontalBoxMemory = Box.createHorizontalBox();
        horizontalBoxMemory.add(Box.createHorizontalGlue());
        horizontalBoxMemory.add(labelForMemory1);
        horizontalBoxMemory.add(Box.createHorizontalStrut(10));
        horizontalBoxMemory.add(textFieldMemory1);
        horizontalBoxMemory.add(Box.createHorizontalStrut(20));
        horizontalBoxMemory.add(labelForMemory2);
        horizontalBoxMemory.add(Box.createHorizontalStrut(10));
        horizontalBoxMemory.add(textFieldMemory2);
        horizontalBoxMemory.add(Box.createHorizontalStrut(20));
        horizontalBoxMemory.add(labelForMemory3);
        horizontalBoxMemory.add(Box.createHorizontalStrut(10));
        horizontalBoxMemory.add(textFieldMemory3);
        horizontalBoxMemory.add(Box.createHorizontalGlue());
        horizontalBoxMemory.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // ДОБАВЛЕНИЕ РАДИО-КНОПОК
        horizontalBoxMemoryType.add(Box.createHorizontalGlue());
        addRadioButtonMemory("Ячейка 1", 1);
        addRadioButtonMemory("Ячейка 2", 2);
        addRadioButtonMemory("Ячейка 3", 3);
        radioButtonsMemory.setSelected(radioButtonsMemory.getElements().nextElement().getModel(), true);
        horizontalBoxMemoryType.add(Box.createHorizontalGlue());
        horizontalBoxMemoryType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        // СОЗДАНИЕ ОБЛАСТИ КНОПОК
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    if (formulaId == 1)
                        result = formula1(x, y, z);
                    else
                        result = formula2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldResult.setText("0");
            }
        });

        Box horizontalBoxButtons = Box.createHorizontalBox();

        horizontalBoxButtons.add(buttonCalc);
        horizontalBoxButtons.add(Box.createHorizontalGlue());
        horizontalBoxButtons.add(buttonReset);

        horizontalBoxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        // КОМПАНОВКА
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(horizontalBoxFormulaType);
        contentBox.add(horizontalBoxVariables);
        contentBox.add(horizontalBoxResult);
        contentBox.add(horizontalBoxMemoryButton);
        contentBox.add(horizontalBoxMemory);
        contentBox.add(horizontalBoxMemoryType);
        contentBox.add(horizontalBoxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    // ГЛАВНЫЙ МЕТОД КЛАССА
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
