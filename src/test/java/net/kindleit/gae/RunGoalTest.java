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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

public class RunGoalTest extends AbstractMojoTestCase {

  RunGoal mojo;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    final File pom = new File(getBasedir(), "target/test-classes/testPom.xml");
    assertTrue(pom.exists());

    mojo = (RunGoal) lookupMojo("run", pom);
    assertNotNull(mojo);
    assertNotNull(mojo.getProject());
  }

  public void testGetEngineClass() throws MojoExecutionException {
    mojo.gaeVersion = "1.2.1";
    mojo.getEngineClass(EngineGoalBase.APPCFG_CLASS,
        EngineGoalBase.SDK_APPCFG_NOTFOUND);
  }

}

