/* Copyright 2009 Kindleit.net Software Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kindleit.gae;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.selectors.AndSelector;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.types.selectors.NotSelector;

/** Goal to .
 *
 * @author rhansen@kindleit.net
 *
 * @goal enhance
 */
public class EnhanceGoal extends EngineGoalBase {

  protected static String ENHANCE_CLS =
    "com.google.appengine.tools.enhancer.EnhancerTask";
  protected static final String SDK_ENHANCE_NOTFOUND =
    "Enhance class not found in gae classpath";


  /** Fileset to augment.
   * @parameter
   */
  protected List<String> includes;

  /** Fileset to augment.
   * @parameter
   */
  protected List<String> excludes;

  /**
   * @parameter expression="${project.build.outputDirectory}"
   */
  protected File enhanceFolder;

  public void execute() throws MojoExecutionException, MojoFailureException {

    getLog().info("Enhancing DataNucleus Clases...");

    final Project prj = new Project();
    prj.init();

    final FileSet fs = new FileSet();
    fs.setDir(enhanceFolder);
    addExcludes(fs);
    addIncludes(fs);

    final Class<?> ehClass = getEngineClass(ENHANCE_CLS, SDK_ENHANCE_NOTFOUND);

    Object eh;
    try {
      eh = ehClass.getConstructor().newInstance();
      ehClass.getMethod("setProject", Project.class).invoke(eh, prj);
      ehClass.getMethod("addFileSet", FileSet.class).invoke(eh, fs);
      ehClass.getMethod("setEnhancerName", String.class).invoke(eh, "enhance");
      ehClass.getMethod("execute").invoke(eh);
    } catch (final Exception e) {
      throw new MojoFailureException("Could not enhance libraries", e);
    }
  }

  private void addIncludes(final FileSet fs) {
    final AndSelector fsIncludes = new AndSelector();

    if (includes == null) {
      includes = new ArrayList<String>(1);
    }

    if (includes.size() == 0) {
      includes.add("**/*");
    }

    for (final String inc : includes) {
      final FilenameSelector fns = new FilenameSelector();
      fns.setName(inc);
      fsIncludes.add(fns);
    }

    fs.addAnd(fsIncludes);
  }

  private void addExcludes(final FileSet fs) {
    if (excludes != null ) {
      final NotSelector fsExcludes = new NotSelector();

      for (final String exc : excludes) {
        final FilenameSelector fns = new FilenameSelector();
        fns.setName(exc);
        fsExcludes.add(fns);
      }

      fs.addNot(fsExcludes);
    }
  }
}


