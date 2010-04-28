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
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.appengine.tools.KickStart;

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
  private ServerSocket serverSocket;

  private AppEnginePluginMonitor(final int port, final String key)
  throws IOException {
    if (port <= 0) {
      throw new IllegalStateException("Bad stop port");
    }
    if (key == null) {
      throw new IllegalStateException("Bad stop key");
    }

    this.key = key;

    serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    serverSocket.setReuseAddress(true);
  }

  public void run() {
    while (serverSocket != null) {
      Socket socket = null;
      try {
        socket = serverSocket.accept();
        socket.setSoLinger(false, 0);
        final BufferedReader in =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final OutputStream out = socket.getOutputStream();

        if (!key.equals(in.readLine())) {
          continue;
        }

        final Commands cmd = Commands.valueOf(in.readLine());
        if (cmd == null) {
          out.write("Unsupported monitor operation".getBytes());
          continue;
        }

        switch (cmd) {
        case STOP:
          executeStop(socket, out);
          break;
        }

      } catch (final Exception e) {

      } finally {
        if (socket != null) {
          try {
            socket.close();
          } catch (final Exception e) {
            System.err.println(e.getMessage());
          }
        }
      }
    }
  }

  private void executeStop(final Socket socket, final OutputStream out) {
    try {
      out.write("Engine stopped".getBytes());
      socket.close();
      try {
        serverSocket.close();
      } catch (final Exception e) {
        System.err.println(e.getMessage());
      }
      serverSocket = null;
      System.exit(0);
    } catch (final Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
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
  public static void main(final String[] args) throws IOException {
    final int stopPort = Integer.valueOf(System.getProperty("monitor.port"));
    final String stopKey = System.getProperty("monitor.key");

    final Thread monitor = new Thread(new AppEnginePluginMonitor(stopPort, stopKey));
    monitor.setName("StopAppEnginePluginMonitor");
    monitor.start();
    KickStart.main(args);
  }
}
