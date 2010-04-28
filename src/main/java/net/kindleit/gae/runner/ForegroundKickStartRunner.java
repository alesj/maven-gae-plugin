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

  Log log;

  /**
   * Creates a new {@code ForegroundKickStartRunner}.
   *
   * @param log the Maven plugin logger to direct output to
   */
  public ForegroundKickStartRunner(final Log log) {
    this.log = log;
  }

  /**
   * Synchronously starts a {@code KickStart} instance with the specified arguments.
   * This method method will block until the server exits.
   *
   * @param monitorPort unused.
   * @param monitorKey unused.
   * @param args the arguments to pass to {@code KickStart}
   */
  @Override
  public void start(final int monitorPort, final String monitorKey,
      final List<String> args) {
    KickStart.main(args.toArray(new String[args.size()]));
  }

}
