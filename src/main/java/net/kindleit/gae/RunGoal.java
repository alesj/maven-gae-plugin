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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import com.google.appengine.tools.admin.AppCfg;

/**
 * @author jpeynado@kindleit.net
 * @author rhansen@kindleit.net
 * Goal for run a WAR project on the GAE dev server.
 *
 * @goal run
 * @requiresDependencyResolution runtime
 * @execute phase="test-compile"
 *
 */
public class RunGoal extends AbstractMojo {

  /**
   * The Maven project reference.
   *
   * @parameter expression="${project}"
   * @required
   * @readonly
   */
  protected MavenProject project;

  public void execute() throws MojoExecutionException, MojoFailureException {
    final String[] args = new String[6];
    AppCfg.main(args);
  }

}


