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

/** Output plugin and sdk version information.
 *
 * @author rhansen@kindleit.net
 *
 * @goal version
 */
public class VersionGoal extends EngineGoalBase {

  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("maven-gae-plugin version: " + PLUGIN_VERSION);
    runAppCfg("version");
  }

}


