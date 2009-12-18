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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author jpeynado@kindleit.net
 * @author rhansen@kindleit.net Goal for run a WAR project on the GAE dev
 *         server.
 * @goal run
 * @requiresDependencyResolution runtime
 * @execute phase="package"
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
