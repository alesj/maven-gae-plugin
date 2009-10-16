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

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.google.appengine.tools.admin.AppCfg;

/**
 * @author jpeynado@kindleit.net
 * Goal for run a WAR project on the GAE dev server.
 * @goal logs
 */
public class LogsGoal extends EngineGoalBase {

  /** Log report output file.
   *
   * @parameter expression="${project.build.outputDirectory}/gae.log"
   */
  protected File outputFile;

  /** Number of days to retrieve from the log.
   *
   * @parameter expression="${gae.log.days}" default-value="1"
   */
  protected int days;

  /** Severity Log level to retrieve.
   *
   * @parameter expression="${gae.log.severity}"
   */
  protected Integer severity;

  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("Getting application logs...");

    final List<String> args = getCommonArgs();
    args.add("--num_days=" + days);
    if (severity != null) {
      args.add("severity=" + severity);
    }
    args.add("request_logs");
    args.add(appDir);
    args.add(outputFile.toString());

    assureSystemProperties();
    AppCfg.main(args.toArray(ARG_TYPE));
  }
}


