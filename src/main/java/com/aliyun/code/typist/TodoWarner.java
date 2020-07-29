package com.aliyun.code.typist;

import com.aliyun.code.typist.components.checker.TodoChecker;
import com.aliyun.code.typist.components.content.Comment;
import com.aliyun.code.typist.components.extractor.CommentExtractor;
import com.aliyun.code.typist.components.extractor.ExtractorFactory;
import lombok.Setter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.List;

@Mojo(name = "work-to-go", defaultPhase = LifecyclePhase.INITIALIZE)
public class TodoWarner extends AbstractMojo {
    @Parameter(defaultValue = "${project}", readonly = true)
    @Setter
    private MavenProject project;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        checkDir(project.getBasedir());
    }


    private void checkDir(File dir) {
        if (dir.isFile()) {
            checkJava(dir);
            return;
        }

        File[] files = dir.listFiles();
        for (File file : files) {
            checkDir(file);
        }

    }

    private void checkJava(File java) {
        if (!Util.isJavaFile(java)) {
            return;
        }
        CommentExtractor commentExtractor = ExtractorFactory.getCommentExtractor(java);
        if (commentExtractor == null) {
            return;
        }

        List<Comment> comments = commentExtractor.extractComments(java);

        TodoChecker todoChecker = TodoChecker.getInstance();
        todoChecker.check(comments, getLog());
    }


}
