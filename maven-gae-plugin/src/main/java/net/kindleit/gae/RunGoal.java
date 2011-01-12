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
package net.kindleit.gae;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Runs the WAR project locally on the Google App Engine development server.
 *
 * You can specify jvm flags via the jvmFlags in the configuration section.
 *
 * @author rhansen@kindleit.net
 * @goal run
 * @requiresDependencyResolution runtime
 * @execute phase="package"
 */
public class RunGoal extends StartGoal {

  /** Will always run in the foreground.
   *
   * @see net.kindleit.gae.StartGoal#execute()
   */
  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    wait = true;
    super.execute();
  }

}
