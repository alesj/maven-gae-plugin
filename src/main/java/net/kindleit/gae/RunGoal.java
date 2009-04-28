/*
 * Copyright 2008 Kindleit Technologies. All rights reserved. This file, all
 * proprietary knowledge and algorithms it details are the sole property of
 * Kindleit Technologies unless otherwise specified. The software this file
 * belong with is the confidential and proprietary information of Kindleit
 * Technologies. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Kindleit.
 */
package net.kindleit.gae;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author jpeynado@kindleit.net
 * @author rhansen@kindleit.net Goal for run a WAR project on the GAE dev
 *         server.
 * @goal run
 * @requiresDependencyResolution runtime
 * @execute phase="test-compile"
 */
public class RunGoal extends EngineGoalBase {

  /** Port to run in.
   *
   * @parameter expression="${gae.port}" default-value="8080"
   */
  private String port;

  /** Address to bind to.
   *
   * @parameter expression="${gae.address}" default-value="0.0.0.0"
   */
  private String address;

  /** Do not check for new SDK versions.
  *
  * @parameter expression="${gae.disableUpdateCheck}" default-value="false"
  */
  protected boolean disableUpdateCheck;

  public void execute() throws MojoExecutionException, MojoFailureException {

    if (disableUpdateCheck) {
      runKickStart("com.google.appengine.tools.development.DevAppServerMain",
        "--address=" + address, "--port=" + port, "--disable_update_check",
        appDir);

    } else {
      runKickStart("com.google.appengine.tools.development.DevAppServerMain",
          "--address=" + address, "--port=" + port, appDir);
    }
  }

}
