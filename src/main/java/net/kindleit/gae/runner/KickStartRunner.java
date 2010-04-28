/*
 * Copyright 2010 Kindleit Technologies. All rights reserved. This file, all
 * proprietary knowledge and algorithms it details are the sole property of
 * Kindleit Technologies unless otherwise specified. The software this file
 * belong with is the confidential and proprietary information of Kindleit
 * Technologies. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Kindleit.
 */


package net.kindleit.gae.runner;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.logging.Log;

/** KickStartRunner is responsible of
 * @author rhansen@kitsd.com
 */
public abstract class KickStartRunner {

  /**
   * @param monitorPort
   * @param monitorKey
   * @param args
   * @throws KickStartExecutionException
   */
  public abstract void start(final int monitorPort, final String monitorKey, final
      List<String> args) throws KickStartExecutionException;

  /**
   * @param wait
   * @param artifacts
   * @param gaeProperties
   * @param log
   * @return
   * @throws KickStartExecutionException
   */
  public static KickStartRunner createRunner(final boolean wait,
      final Set<Artifact> artifacts, final Properties gaeProperties, final Log log)
  throws KickStartExecutionException {

    return wait ? new ForegroundKickStartRunner(log)
        : new BackgroundKickStartRunner(artifacts, gaeProperties, log);
  }

}
