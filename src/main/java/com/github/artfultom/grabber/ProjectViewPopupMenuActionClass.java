package com.github.artfultom.grabber;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.impl.file.PsiDirectoryFactory;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProjectViewPopupMenuActionClass extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        GenerateDialog dialog = new GenerateDialog(event.getProject(), model -> {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(model.getUrl()))
                    .build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(s -> {
                        String fileStr = GeneratorUtils.generateFileStr(s);

                        System.out.println(fileStr);    // TODO
                    }).join();
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
