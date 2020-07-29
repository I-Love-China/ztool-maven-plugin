package com.aliyun.code.typist;

import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.apache.maven.project.MavenProject;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TodoWarnerTest  {
    @Rule
    public MojoRule rule = new MojoRule();

    @Rule
    public TestResources resources = new TestResources();

    @Test
    public void testTodoWarner() throws Exception {
        File baseDir = this.resources.getBasedir("");
        assertNotNull(baseDir);
        assertTrue(baseDir.exists());

        File pom = new File(baseDir, "pom.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        TodoWarner todoWarner = (TodoWarner)this.rule.lookupMojo("work-to-go", pom);
        assertNotNull(todoWarner);

        final MavenProject mvnProject = new MavenProject();
        mvnProject.setFile(pom);
        this.rule.setVariableValueToObject(todoWarner, "project", mvnProject);

        todoWarner.execute();
    }
}
