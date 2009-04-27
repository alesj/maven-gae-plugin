package net.kindleit.gae;

import org.apache.maven.plugin.AbstractMojo;

public abstract class EngineGoalBase extends AbstractMojo {

  /**
   * @parameter expression="${project.build.directory}/${project.build.finalName}"
   * @required
   */
  protected String appDir;

  /**
   * @parameter expression="${appengine.sdk.root}"
   * @required
   */
  protected String sdkDirectory;

}
