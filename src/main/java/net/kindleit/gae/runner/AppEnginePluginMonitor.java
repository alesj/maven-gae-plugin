/* Copyright 2010 Kindleit.net Software Development
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
package net.kindleit.gae.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.maven.plugin.logging.Log;

/**
 * Listens for stop commands e.g. via mvn gae:stop and causes the development server to stop.
 *
 * @author tmoore@incrementalism.net
 * @since 0.5.8
 */
public final class AppEnginePluginMonitor implements Runnable {

  public enum Commands {
    STOP;
  }

  private final String key;
  private final KickStartRunner kickStart;
  private final Log log;

  private ServerSocket serverSocket;

  private AppEnginePluginMonitor(final int port, final String key, final KickStartRunner kickStart, final Log log) throws IOException {
    if (port <= 0) {
      throw new IllegalStateException("Bad stop port");
    }
    if (key == null) {
      throw new IllegalStateException("Bad stop key");
    }

    this.key = key;
    this.kickStart = kickStart;
    this.log = log;

    serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    serverSocket.setReuseAddress(true);
  }

  public void run() {
    while (serverSocket != null) {
      Socket socket = null;
      try {
        socket = serverSocket.accept();
        socket.setSoLinger(false, 0);
        final BufferedReader lin =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));

        if (!key.equals(lin.readLine())) {
          continue;
        }

        final Commands cmd = Commands.valueOf(lin.readLine());
        if (cmd == null) {
          getLog().info("Unsupported monitor operation");
          continue;
        }

        switch (cmd) {
        case STOP:
          executeStop(socket);
          break;
        }

      } catch (final Exception e) {
        getLog().error(e);
      } finally {
        if (socket != null) {
          try {
            socket.close();
          } catch (final Exception e) {
            getLog().debug(e);
          }
        }
      }
    }
  }

  private void executeStop(final Socket socket) {
    kickStart.stop();

    try {
      socket.close();
    } catch (final Exception e) {
      getLog().debug(e);
    }

    try {
      serverSocket.close();
    } catch (final Exception e) {
      getLog().debug(e);
    }

    serverSocket = null;
  }

  private Log getLog() {
    return log;
  }

  /**
   * Starts a monitor that can stop a {@code KickStartRunner}. The monitor opens a socket that listens on the specified
   * port of localhost for the specified stop key. When it receives this stop key, followed by a CRLF, followed by
   * the command "stop" and another CRLF, it will call {@link KickStartRunner#stop()} on the the specified runner.
   *
   * @param stopPort the port to listen on
   * @param stopKey the string to listen for
   * @param kickStart the {@code KickStartRunner} to stop
   * @param log the Maven plugin logger to direct output to
   * @throws IOException if an I/O error occurs while opening the server socket
   */
  public static void monitor(final int stopPort, final String stopKey, final KickStartRunner kickStart, final Log log) throws IOException {
    final Thread monitor = new Thread(new AppEnginePluginMonitor(stopPort, stopKey, kickStart, log));
    monitor.setDaemon(true);
    monitor.setName("StopAppEnginePluginMonitor");
    monitor.start();
  }
}
