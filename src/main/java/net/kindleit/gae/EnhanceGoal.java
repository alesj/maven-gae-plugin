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
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.selectors.AndSelector;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.types.selectors.NotSelector;

import com.google.appengine.tools.enhancer.EnhancerTask;

/** Goal to .
 *
 * @author rhansen@kindleit.net
 *
 * @goal enhance
 */
public class EnhanceGoal extends EngineGoalBase {

  /** Fileset to augment.
   * @parameter
   * @required
   */
  protected List<String> includes;

  /** Fileset to augment.
   * @parameter
   */
  protected List<String> excludes;

  public void execute() throws MojoExecutionException, MojoFailureException {

    getLog().info("Running Google App Engine Server...");

    final FileSet fs = new FileSet();
    fs.setDir(new File(project.getBuild().getDirectory()));

    final AndSelector fsIncludes = new AndSelector();

    for (final String inc : includes) {
      final FilenameSelector fns = new FilenameSelector();
      fns.setName(inc);
      fsIncludes.add(fns);
    }

    final NotSelector fsExcludes = new NotSelector();

    for (final String exc : excludes) {
      final FilenameSelector fns = new FilenameSelector();
      fns.setName(exc);
      fsExcludes.add(fns);
    }

    fs.addNot(fsExcludes);
    fs.addAnd(fsIncludes);

    final EnhancerTask ehTask = new EnhancerTask();
    ehTask.addFileSet(fs);
    ehTask.setEnhancerName("enhance");
    ehTask.execute();
  }
}


