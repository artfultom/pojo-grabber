package com.github.artfultom.grabber;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class GenerateDialog extends DialogWrapper {

    public GenerateDialog() {
        super(true);

        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Source url:");

        JTextField textField = new JTextField();
        JCheckBox checkBox1 = new JCheckBox("CheckBox 1");
        JCheckBox checkBox2 = new JCheckBox("CheckBox 2");
        JCheckBox checkBox3 = new JCheckBox("CheckBox 3");
        JCheckBox checkBox4 = new JCheckBox("CheckBox 4");

        checkBox1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBox2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBox3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBox4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(checkBox1)
                                        .addComponent(checkBox3))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(checkBox2)
                                        .addComponent(checkBox4))))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label)
                        .addComponent(textField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(checkBox1)
                                        .addComponent(checkBox2))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(checkBox3)
                                        .addComponent(checkBox4)))
                )
        );

        setTitle("Generate POJOs");
        setResizable(false);
        pack();

        return panel;
    }


    @NotNull
    @Override
    protected Action[] createActions() {
        DialogWrapperAction generateAction = new DialogWrapperAction("Generate") {
            @Override
            protected void doAction(ActionEvent e) {
                close(OK_EXIT_CODE);
            }
        };

        DialogWrapperAction cancelAction = new DialogWrapperAction("Cancel") {
            @Override
            protected void doAction(ActionEvent e) {
                close(CANCEL_EXIT_CODE);
            }
        };

        return new Action[]{generateAction, cancelAction};
    }
}
