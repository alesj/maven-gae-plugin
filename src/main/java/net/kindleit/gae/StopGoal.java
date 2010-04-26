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
 *
 * Includes contributions adapted from the Jetty Maven Plugin
 * Copyright 2000-2004 Mort Bay Consulting Pty. Ltd.
 */
package net.kindleit.gae;

import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import net.kindleit.gae.runner.AppEnginePluginMonitor.Commands;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Stops a running instance of the Google App Engine development server.
 *
 * This is intended to be included in your project's POM and runs in the
 * post-integration-test phase.
 *
 * @author tmoore@incrementalism.net
 * @goal stop
 * @phase post-integration-test
 * @since 0.5.8
 */
public class StopGoal extends AbstractMojo {

  private static final String SPECIFY_KEY = "Please specify a valid monitorKey";
  private static final String SPECIFY_PORT = "Please specify a valid port";
  private static final String CONNECTION_ERROR = "App Engine is not running!";
  private static final String NL = "\r\n";

  /** Port to listen for stop requests on.
   *
   * @parameter expression="${gae.monitor.port}"
   * @required
   */
  protected int monitorPort;

  /** Key to provide when making stop requests.
   *
   * @parameter expression="${gae.monitor.key}"
   * @required
   */
  protected String monitorKey;

  public void execute() throws MojoExecutionException, MojoFailureException {
    if (monitorPort <= 0) {
      throw new MojoExecutionException(SPECIFY_PORT);
    }
    if (monitorKey == null) {
      throw new MojoExecutionException(SPECIFY_KEY);
    }

    try {
      final Socket s = new Socket(InetAddress.getByName("127.0.0.1"), monitorPort);
      s.setSoLinger(false, 0);

      final OutputStream out = s.getOutputStream();
      out.write((monitorKey + NL + Commands.STOP + NL).getBytes());
      out.flush();
      s.close();
    } catch (final ConnectException e) {
      getLog().info(CONNECTION_ERROR);
    } catch (final Exception e) {
      getLog().error(e);
    }
  }

}
