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
 * Runs the App Engine {@link KickStart} class.
 *
 * @author tmoore@incrementalism.net
 * @since 0.5.8
 */
public abstract class KickStartRunner {
  private boolean started = false;
  private final Log log;

  protected KickStartRunner(Log log) {
    this.log = log;
  }

  /**
   * Starts a {@code KickStart} instance with the specified arguments.
   *
   * @param args the arguments to pass to {@code KickStart}
   * @throws KickStartExecutionException if an exception is thrown while trying to execute {@code KickStart}
   */
  public abstract void start(List<String> args) throws KickStartExecutionException;

  /**
   * Stops the {@code KickStart} instance started by this runner. Does nothing if there isn't one currently running.
   */
  public abstract void stop();

  public final synchronized boolean isStarted() {
    return started;
  }

  protected final synchronized void setStarted(boolean started) {
    this.started = started;
    notifyAll();
  }

  protected final Log getLog() {
    return log;
  }

  /**
   * Returns an implementation of {@code KickStartRunner} that either synchronously invokes {@link KickStart} in the
   * current process (if {@code wait} is {@code true}), or executes a new {@code java} process to run {@code KickStart}
   * in the background (if {@code wait} is {@code false}).
   *
   * @param wait whether to run in the foreground ({@code true}) or background ({@code false})
   * @param log the Maven plugin logger to direct output to
   * @return an implementation of {@code KickStartRunner} that uses the specified execution strategy
   */
  public static KickStartRunner createRunner(boolean wait, Log log) {
    return wait ? new ForegroundKickStartRunner(log) : new BackgroundKickStartRunner(log);
  }

}
