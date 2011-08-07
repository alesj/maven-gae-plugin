/* Copyright 2011 Kindleit.net Software Development
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
package net.kindleit.gae.backends;

import static org.codehaus.plexus.util.StringUtils.isEmpty;
import net.kindleit.gae.EngineGoalBase;

import org.apache.maven.plugin.MojoExecutionException;

/** BackendGoalBase used by all Backends commands.
 * @author rhansen@kitsd.com
 */
public abstract class BackendGoalBase extends EngineGoalBase {

  private static final String BACKENDS_COMMAND = "backends";
  /**
   * Overrides where the Project War Directory is located.
   * @parameter expression="${gae.backendDir}" default-value="${project.build.directory}/${project.build.finalName}"
   * @required
   */
  protected String backendDir;

  /**
   * Overrides where the Project War Directory is located.
   * @parameter expression="${gae.backend}"
   */
  protected String backend;


  /** Passes a backend command to the Google App Engine AppCfg runner.
  *
  * @param command command to run through AppCfg
   * @throws MojoExecutionException If {@link #assureSystemProperties()} fails
  */
  protected final void runBackend(final String command) throws MojoExecutionException {
    if (isEmpty(command))
      throw new MojoExecutionException("Must supply backend command");
    if (isEmpty(backend))
      runAppCfg(BACKENDS_COMMAND, backendDir, command);
    else
      runAppCfg(BACKENDS_COMMAND, backendDir, command, backend);
  }
}
