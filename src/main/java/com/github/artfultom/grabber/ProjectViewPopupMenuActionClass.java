package com.github.artfultom.grabber;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.impl.file.PsiDirectoryFactory;
import org.jetbrains.annotations.NotNull;

public class ProjectViewPopupMenuActionClass extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        GenerateDialog dialog = new GenerateDialog(event.getProject(), model -> {
            System.out.println(model.getUrl()); // TODO
        });

        dialog.show();
    }

    @Override
    public void update(@NotNull AnActionEvent event) {
        Presentation presentation = event.getPresentation();
        presentation.setEnabledAndVisible(false);

        Project project = event.getData(CommonDataKeys.PROJECT);
        if (project == null) {
            return;
        }

        IdeView view = event.getData(LangDataKeys.IDE_VIEW);
        if (view == null) {
            return;
        }

        PsiDirectory directory = view.getOrChooseDirectory();
        if (directory == null) {
            return;
        }

        PsiDirectoryFactory factory = PsiDirectoryFactory.getInstance(project);
        boolean isPackage = factory.isPackage(view.getOrChooseDirectory());
        if (isPackage) {
            presentation.setEnabledAndVisible(true);
        }
    }
}
