package com.github.artfultom.grabber;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;

public class GenerateDialog extends DialogWrapper {

    private Model model = new Model();

    public GenerateDialog(Project project) {
        super(project, true);

        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Source url:");

        final JTextField textField = new JTextField();
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                model.setUrl(textField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                model.setUrl(textField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                model.setUrl(textField.getText());
            }
        });

        final JCheckBox getters = new JCheckBox("Getters");
        final JCheckBox constructor = new JCheckBox("Constructor");
        final JCheckBox setters = new JCheckBox("Setters");
        final JCheckBox serializable = new JCheckBox("Serializable");

        getters.addItemListener(e -> model.setGetters(getters.isSelected()));
        constructor.addItemListener(e -> model.setConstructor(constructor.isSelected()));
        setters.addItemListener(e -> model.setSetters(setters.isSelected()));
        serializable.addItemListener(e -> model.setSerializable(serializable.isSelected()));

        getters.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        constructor.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setters.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        serializable.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

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
                                        .addComponent(getters)
                                        .addComponent(setters))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(constructor)
                                        .addComponent(serializable))))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label)
                        .addComponent(textField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(getters)
                                        .addComponent(constructor))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(setters)
                                        .addComponent(serializable)))
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

    public Model getModel() {
        return model;
    }

    public static class Model {

        private String url;
        private boolean getters;
        private boolean constructor;
        private boolean setters;
        private boolean serializable;

        public Model() {
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isGetters() {
            return getters;
        }

        public void setGetters(boolean getters) {
            this.getters = getters;
        }

        public boolean isConstructor() {
            return constructor;
        }

        public void setConstructor(boolean constructor) {
            this.constructor = constructor;
        }

        public boolean isSetters() {
            return setters;
        }

        public void setSetters(boolean setters) {
            this.setters = setters;
        }

        public boolean isSerializable() {
            return serializable;
        }

        public void setSerializable(boolean serializable) {
            this.serializable = serializable;
        }
    }
}
