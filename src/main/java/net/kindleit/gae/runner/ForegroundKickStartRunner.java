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
package net.kindleit.gae.runner;

import java.util.List;

import org.apache.maven.plugin.logging.Log;

import com.google.appengine.tools.KickStart;

/**
 * An implementation of {@code KickStartRunner} that synchronously invokes {@link KickStart} in the current process.
 *
 * @author tmoore@incrementalism.net
 * @since 0.5.8
 */
final class ForegroundKickStartRunner extends KickStartRunner {

  /**
   * Creates a new {@code ForegroundKickStartRunner}.
   *
   * @param log the Maven plugin logger to direct output to
   */
  public ForegroundKickStartRunner(Log log) {
    super(log);
  }

  /**
   * Synchronously starts a {@code KickStart} instance with the specified arguments.
   * This method method will block until the server exits.
   *
   * @param args the arguments to pass to {@code KickStart}
   */
  public void start(final List<String> args) {
    setStarted(true);
    KickStart.main(args.toArray(new String[args.size()]));
  }

  /**
   * Stops the {@code KickStart} instance started by this runner by exiting the JVM.
   */
  public void stop() {
    if (isStarted()) {
      getLog().info("Killing App Engine");
      System.exit(0);
    }
  }
}
