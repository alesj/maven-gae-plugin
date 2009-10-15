package net.kindleit.gae;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

public class RunGoalTest extends AbstractMojoTestCase {

  RunGoal mojo;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    final File pom = new File(getBasedir(), "target/test-classes/testPom.xml");
    assertTrue(pom.exists());

    mojo = (RunGoal) lookupMojo("run", pom);
    assertNotNull(mojo);
    assertNotNull(mojo.getProject());
  }

  public void testGetEngineClass() throws MojoExecutionException {
    mojo.gaeVersion = "1.2.1";
    mojo.getEngineClass(EngineGoalBase.APPCFG_CLASS,
        EngineGoalBase.SDK_APPCFG_NOTFOUND);
  }

}

