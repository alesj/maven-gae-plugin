package net.kindleit.gae;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.settings.Settings;

/** Base MOJO class for working with the Google App Engine SDK.
 *
 * @author rhansen@kindleit.net
 */
public abstract class EngineGoalBase extends AbstractMojo {

  /** The Maven project reference.
   *
   * @parameter expression="${settings}"
   * @required
   * @readonly
   */
  protected Settings settings;

  /** Overrides where the Project War Directory is located.
   *
   * @parameter expression="${project.build.directory}/${project.build.finalName}"
   * @required
   */
  protected String appDir;

  /** Specifies where the Google App Engine SDK is located.
   *
   * @parameter expression="${appengine.sdk.root}"
   * @required
   */
  protected String sdkDirectory;

  /** Split large jar files (> 10M) into smaller fragments.
   *
   * @parameter expression="${gae.deps.split}" default-value="false"
   */
  protected boolean splitJars;

  /** The username to use. Will prompt if omitted.
   *
   * @parameter expression="${gae.email}"
   */
  protected String emailAccount;

  /** The server to connect to.
   *
   * @parameter expression="${gae.server}"
   */
  protected String uploadServer;

  /** Overrides the Host header sent with all RPCs.
   *
   * @parameter expression="${gae.host}"
   */
  protected String hostString;

  /** Do not delete temporary directory used in uploading.
   *
   * @parameter expression="${gae.keepTemps}" default-value="false"
   */
  protected boolean keepTempUploadDir;

  /** Always read the login password from stdin.
   *
   * @parameter expression="${gae.passin}" default-value="false"
   */
  protected boolean passIn;

  /** Generate all common Google AppEngine Task Parameters for use in all the
   * goals.
   *
   * @return List of arguments to add.
   */
  protected final List<String> getCommonArgs () {
    final List<String> args = new ArrayList<String>(6);

    args.add("--sdk_root=" + sdkDirectory);

    addBooleanOption(args, "--disable_prompt", !settings.getInteractiveMode());

    addStringOption(args, "--email=", emailAccount);
    addStringOption(args, "--server=", uploadServer);
    addStringOption(args, "--host=", hostString);
    addBooleanOption(args, "--passin", passIn);
    addBooleanOption(args, "--enable_jar_splitting", splitJars);
    addBooleanOption(args, "--retain_upload_dir", keepTempUploadDir);

    return args;
  }

  private final void addBooleanOption(final List<String> args, final String key,
      final boolean var) {
    if (var) {
      args.add(key);
    }
  }

  private final void addStringOption(final List<String> args, final String key,
      final String var) {
    if (var != null && var.length() > 0) {
      args.add(key + var);
    }
  }

}
