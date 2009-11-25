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
package net.kindleit.gae.testUtils;
/*
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.handler.ArtifactHandler;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.model.Build;
import org.apache.maven.model.CiManagement;
import org.apache.maven.model.Contributor;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Developer;
import org.apache.maven.model.DistributionManagement;
import org.apache.maven.model.IssueManagement;
import org.apache.maven.model.License;
import org.apache.maven.model.MailingList;
import org.apache.maven.model.Model;
import org.apache.maven.model.Organization;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.model.Prerequisites;
import org.apache.maven.model.Reporting;
import org.apache.maven.model.Resource;
import org.apache.maven.model.Scm;
import org.apache.maven.plugin.testing.stubs.DefaultArtifactHandlerStub;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.artifact.InvalidDependencyVersionException;
import org.codehaus.plexus.PlexusTestCase;
import org.codehaus.plexus.util.xml.Xpp3Dom;

/**
 * very simple stub of maven project, going to take a lot of work to make it
 * useful as a stub though
 */
public class GoalProjectStub
    extends MavenProject
{
    private String groupId;

    private String artifactId;

    private String name;

    private Model model;

    private MavenProject parent;

    private List dependencies;

    private File file;

    private List collectedProjects;

    private List attachedArtifacts;

    private List compileSourceRoots;

    private List testCompileSourceRoots;

    private List scriptSourceRoots;

    private List pluginArtifactRepositories;

    // private ArtifactRepository releaseArtifactRepository;

    // private ArtifactRepository snapshotArtifactRepository;

    private List activeProfiles;

    private Set dependencyArtifacts;

    private DependencyManagement dependencyManagement;

    private Artifact artifact;

    // private Map artifactMap;

    private Model originalModel;

    // private Map pluginArtifactMap;

    // private Map reportArtifactMap;

    // private Map extensionArtifactMap;

    // private Map projectReferences;

    // private Build buildOverlay;

    private boolean executionRoot;

    private List compileArtifacts;

    private List compileDependencies;

    private List systemDependencies;

    private List testClasspathElements;

    private List testDependencies;

    private List systemClasspathElements;

    private List systemArtifacts;

    private List testArtifacts;

    private List runtimeArtifacts;

    private List runtimeDependencies;

    private List runtimeClasspathElements;

    private String modelVersion;

    private String packaging;

    private String inceptionYear;

    private String url;

    private String description;

    private String version;

    private String defaultGoal;

    private Set artifacts;

    public GoalProjectStub()
    {
        super( (Model) null );
    }

    // kinda dangerous...
    public GoalProjectStub( final Model model )
    {
        // super(model);
        super( (Model) null );
    }

    // kinda dangerous...
    public GoalProjectStub( final MavenProject project )
    {
        // super(project);
        super( (Model) null );
    }

    @Override
    public String getModulePathAdjustment( final MavenProject mavenProject )
        throws IOException
    {
        return "";
    }

    @Override
    public Artifact getArtifact()
    {
        if ( artifact == null )
        {
            final ArtifactHandler ah = new DefaultArtifactHandlerStub( "jar", null );

            final VersionRange vr = VersionRange.createFromVersion( "1.0" );
            final Artifact art = new DefaultArtifact( "group", "artifact", vr, Artifact.SCOPE_COMPILE, "jar", null, ah, false );
            setArtifact( art );
        }
        return artifact;
    }

    @Override
    public void setArtifact( final Artifact artifact )
    {
        this.artifact = artifact;
    }

    @Override
    public Model getModel()
    {
        return model;
    }

    @Override
    public MavenProject getParent()
    {
        return parent;
    }

    @Override
    public void setParent( final MavenProject mavenProject )
    {
        parent = mavenProject;
    }

    @Override
    public void setRemoteArtifactRepositories( final List list )
    {

    }

    @Override
    public List getRemoteArtifactRepositories()
    {
        return Collections.singletonList( "" );
    }

    @Override
    public boolean hasParent()
    {
        if ( parent != null )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public File getFile()
    {
        return file;
    }

    @Override
    public void setFile( final File file )
    {
        this.file = file;
    }

    @Override
    public File getBasedir()
    {
        return new File( PlexusTestCase.getBasedir() );
    }

    @Override
    public void setDependencies( final List list )
    {
        dependencies = list;
    }

    @Override
    public List getDependencies()
    {
        if ( dependencies == null )
        {
            dependencies = Collections.EMPTY_LIST;
        }
        return dependencies;
    }

    public void setDependencyManagement(final DependencyManagement depMgt)
    {
        dependencyManagement = depMgt;
    }
    @Override
    public DependencyManagement getDependencyManagement()
    {
        if ( dependencyManagement == null )
        {
            dependencyManagement = new DependencyManagement();
        }

        return dependencyManagement;
    }

    @Override
    public void addCompileSourceRoot( final String string )
    {
        if ( compileSourceRoots == null )
        {
            compileSourceRoots = Collections.singletonList( string );
        }
        else
        {
            compileSourceRoots.add( string );
        }
    }

    @Override
    public void addScriptSourceRoot( final String string )
    {
        if ( scriptSourceRoots == null )
        {
            scriptSourceRoots = Collections.singletonList( string );
        }
        else
        {
            scriptSourceRoots.add( string );
        }
    }

    @Override
    public void addTestCompileSourceRoot( final String string )
    {
        if ( testCompileSourceRoots == null )
        {
            testCompileSourceRoots = Collections.singletonList( string );
        }
        else
        {
            testCompileSourceRoots.add( string );
        }
    }

    @Override
    public List getCompileSourceRoots()
    {
        return compileSourceRoots;
    }

    @Override
    public List getScriptSourceRoots()
    {
        return scriptSourceRoots;
    }

    public List getTestCompileSourceRoots()
    {
        return testCompileSourceRoots;
    }

    public List getCompileClasspathElements()
        throws DependencyResolutionRequiredException
    {
        return compileSourceRoots;
    }

    public void setCompileArtifacts( final List compileArtifacts )
    {
        this.compileArtifacts = compileArtifacts;
    }

    public List getCompileArtifacts()
    {
        return compileArtifacts;
    }

    public List getCompileDependencies()
    {
        return compileDependencies;
    }

    public List getTestClasspathElements()
        throws DependencyResolutionRequiredException
    {
        return testClasspathElements;
    }

    public List getTestArtifacts()
    {
        return testArtifacts;
    }

    public List getTestDependencies()
    {
        return testDependencies;
    }

    public List getRuntimeClasspathElements()
        throws DependencyResolutionRequiredException
    {
        return runtimeClasspathElements;
    }

    public List getRuntimeArtifacts()
    {
        return runtimeArtifacts;
    }

    public List getRuntimeDependencies()
    {
        return runtimeDependencies;
    }

    public List getSystemClasspathElements()
        throws DependencyResolutionRequiredException
    {
        return systemClasspathElements;
    }

    public List getSystemArtifacts()
    {
        return systemArtifacts;
    }

    public void setRuntimeClasspathElements( final List runtimeClasspathElements )
    {
        this.runtimeClasspathElements = runtimeClasspathElements;
    }

    public void setAttachedArtifacts( final List attachedArtifacts )
    {
        this.attachedArtifacts = attachedArtifacts;
    }

    public void setCompileSourceRoots( final List compileSourceRoots )
    {
        this.compileSourceRoots = compileSourceRoots;
    }

    public void setTestCompileSourceRoots( final List testCompileSourceRoots )
    {
        this.testCompileSourceRoots = testCompileSourceRoots;
    }

    public void setScriptSourceRoots( final List scriptSourceRoots )
    {
        this.scriptSourceRoots = scriptSourceRoots;
    }

    public void setArtifactMap( final Map artifactMap )
    {
        // this.artifactMap = artifactMap;
    }

    public void setPluginArtifactMap( final Map pluginArtifactMap )
    {
        // this.pluginArtifactMap = pluginArtifactMap;
    }

    public void setReportArtifactMap( final Map reportArtifactMap )
    {
        // this.reportArtifactMap = reportArtifactMap;
    }

    public void setExtensionArtifactMap( final Map extensionArtifactMap )
    {
        // this.extensionArtifactMap = extensionArtifactMap;
    }

    public void setProjectReferences( final Map projectReferences )
    {
        // this.projectReferences = projectReferences;
    }

    public void setBuildOverlay( final Build buildOverlay )
    {
        // this.buildOverlay = buildOverlay;
    }

    public void setCompileDependencies( final List compileDependencies )
    {
        this.compileDependencies = compileDependencies;
    }

    public void setSystemDependencies( final List systemDependencies )
    {
        this.systemDependencies = systemDependencies;
    }

    public void setTestClasspathElements( final List testClasspathElements )
    {
        this.testClasspathElements = testClasspathElements;
    }

    public void setTestDependencies( final List testDependencies )
    {
        this.testDependencies = testDependencies;
    }

    public void setSystemClasspathElements( final List systemClasspathElements )
    {
        this.systemClasspathElements = systemClasspathElements;
    }

    public void setSystemArtifacts( final List systemArtifacts )
    {
        this.systemArtifacts = systemArtifacts;
    }

    public void setTestArtifacts( final List testArtifacts )
    {
        this.testArtifacts = testArtifacts;
    }

    public void setRuntimeArtifacts( final List runtimeArtifacts )
    {
        this.runtimeArtifacts = runtimeArtifacts;
    }

    public void setRuntimeDependencies( final List runtimeDependencies )
    {
        this.runtimeDependencies = runtimeDependencies;
    }

    public void setModel( final Model model )
    {
        this.model = model;
    }

    public List getSystemDependencies()
    {
        return systemDependencies;
    }

    public void setModelVersion( final String string )
    {
        modelVersion = string;
    }

    public String getModelVersion()
    {
        return modelVersion;
    }

    public String getId()
    {
        return "";
    }

    public void setGroupId( final String string )
    {
        groupId = string;
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setArtifactId( final String string )
    {
        artifactId = string;
    }

    public String getArtifactId()
    {
        return artifactId;
    }

    public void setName( final String string )
    {
        name = string;
    }

    public String getName()
    {
        return name;
    }

    public void setVersion( final String string )
    {
        version = string;
    }

    public String getVersion()
    {
        return version;
    }

    public String getPackaging()
    {
        return packaging;
    }

    public void setPackaging( final String string )
    {
        packaging = string;
    }

    public void setInceptionYear( final String string )
    {
        inceptionYear = string;
    }

    public String getInceptionYear()
    {
        return inceptionYear;
    }

    public void setUrl( final String string )
    {
        url = string;
    }

    public String getUrl()
    {
        return url;
    }

    public Prerequisites getPrerequisites()
    {
        return null;
    }

    public void setIssueManagement( final IssueManagement issueManagement )
    {

    }

    public CiManagement getCiManagement()
    {
        return null;
    }

    public void setCiManagement( final CiManagement ciManagement )
    {

    }

    public IssueManagement getIssueManagement()
    {
        return null;
    }

    public void setDistributionManagement( final DistributionManagement distributionManagement )
    {

    }

    public DistributionManagement getDistributionManagement()
    {
        return null;
    }

    public void setDescription( final String string )
    {
        description = string;
    }

    public String getDescription()
    {
        return description;
    }

    public void setOrganization( final Organization organization )
    {

    }

    public Organization getOrganization()
    {
        return null;
    }

    public void setScm( final Scm scm )
    {

    }

    public Scm getScm()
    {
        return null;
    }

    public void setMailingLists( final List list )
    {

    }

    public List getMailingLists()
    {
        return Collections.singletonList( "" );
    }

    public void addMailingList( final MailingList mailingList )
    {

    }

    public void setDevelopers( final List list )
    {

    }

    public List getDevelopers()
    {
        return Collections.singletonList( "" );
    }

    public void addDeveloper( final Developer developer )
    {

    }

    public void setContributors( final List list )
    {

    }

    public List getContributors()
    {
        return Collections.singletonList( "" );
    }

    public void addContributor( final Contributor contributor )
    {

    }

    public void setBuild( final Build build )
    {

    }

    public Build getBuild()
    {
        return null;
    }

    public List getResources()
    {
        return Collections.singletonList( "" );
    }

    public List getTestResources()
    {
        return Collections.singletonList( "" );
    }

    public void addResource( final Resource resource )
    {

    }

    public void addTestResource( final Resource resource )
    {

    }

    public void setReporting( final Reporting reporting )
    {

    }

    public Reporting getReporting()
    {
        return null;
    }

    public void setLicenses( final List list )
    {

    }

    public List getLicenses()
    {
        return Collections.singletonList( "" );
    }

    public void addLicense( final License license )
    {

    }

    public void setArtifacts( final Set set )
    {
        artifacts = set;
    }

    public Set getArtifacts()
    {
        if ( artifacts == null )
        {
            return Collections.EMPTY_SET;
        }
        else
        {
            return artifacts;
        }
    }

    public Map getArtifactMap()
    {
        return Collections.singletonMap( "", "" );
    }

    public void setPluginArtifacts( final Set set )
    {

    }

    public Set getPluginArtifacts()
    {
        return Collections.singleton( "" );
    }

    public Map getPluginArtifactMap()
    {
        return Collections.singletonMap( "", "" );
    }

    public void setReportArtifacts( final Set set )
    {

    }

    public Set getReportArtifacts()
    {
        return Collections.singleton( "" );
    }

    public Map getReportArtifactMap()
    {
        return Collections.singletonMap( "", "" );
    }

    public void setExtensionArtifacts( final Set set )
    {

    }

    public Set getExtensionArtifacts()
    {
        return Collections.singleton( "" );
    }

    public Map getExtensionArtifactMap()
    {
        return Collections.singletonMap( "", "" );
    }

    public void setParentArtifact( final Artifact artifact )
    {

    }

    public Artifact getParentArtifact()
    {
        return null;
    }

    public List getRepositories()
    {
        return Collections.singletonList( "" );
    }

    public List getReportPlugins()
    {
        return Collections.singletonList( "" );
    }

    public List getBuildPlugins()
    {
        return Collections.singletonList( "" );
    }

    public List getModules()
    {
        return Collections.singletonList( "" );
    }

    public PluginManagement getPluginManagement()
    {
        return null;
    }

    public void addPlugin( final Plugin plugin )
    {

    }

    public void injectPluginManagementInfo( final Plugin plugin )
    {

    }

    public List getCollectedProjects()
    {
        return collectedProjects;
    }

    public void setCollectedProjects( final List list )
    {
        collectedProjects = list;
    }

    public void setPluginArtifactRepositories( final List list )
    {
        pluginArtifactRepositories = list;
    }

    public List getPluginArtifactRepositories()
    {
        return pluginArtifactRepositories;
    }

    public ArtifactRepository getDistributionManagementArtifactRepository()
    {
        return null;
    }

    public List getPluginRepositories()
    {
        return Collections.singletonList( "" );
    }

    public void setActiveProfiles( final List list )
    {
        activeProfiles = list;
    }

    public List getActiveProfiles()
    {
        return activeProfiles;
    }

    public void addAttachedArtifact( final Artifact theArtifact )
    {
        if ( attachedArtifacts == null )
        {
            attachedArtifacts = Collections.singletonList( theArtifact );
        }
        else
        {
            attachedArtifacts.add( theArtifact );
        }
    }

    public List getAttachedArtifacts()
    {
        return attachedArtifacts;
    }

    public Xpp3Dom getGoalConfiguration( final String string, final String string1, final String string2, final String string3 )
    {
        return null;
    }

    public Xpp3Dom getReportConfiguration( final String string, final String string1, final String string2 )
    {
        return null;
    }

    public MavenProject getExecutionProject()
    {
        return null;
    }

    public void setExecutionProject( final MavenProject mavenProject )
    {

    }

    public void writeModel( final Writer writer )
        throws IOException
    {

    }

    public void writeOriginalModel( final Writer writer )
        throws IOException
    {

    }

    public Set getDependencyArtifacts()
    {
        return dependencyArtifacts;
    }

    public void setDependencyArtifacts( final Set set )
    {
        dependencyArtifacts = set;
    }

    public void setReleaseArtifactRepository( final ArtifactRepository artifactRepository )
    {
        // this.releaseArtifactRepository = artifactRepository;
    }

    public void setSnapshotArtifactRepository( final ArtifactRepository artifactRepository )
    {
        // this.snapshotArtifactRepository = artifactRepository;
    }

    public void setOriginalModel( final Model model )
    {
        originalModel = model;
    }

    public Model getOriginalModel()
    {
        return originalModel;
    }

    public List getBuildExtensions()
    {
        return Collections.singletonList( "" );
    }

    public Set createArtifacts( final ArtifactFactory artifactFactory, final String string, final ArtifactFilter artifactFilter )
        throws InvalidDependencyVersionException
    {
        return Collections.EMPTY_SET;
    }

    public void addProjectReference( final MavenProject mavenProject )
    {

    }

    public void attachArtifact( final String string, final String string1, final File theFile )
    {

    }

    public Properties getProperties()
    {
        return new Properties();
    }

    public List getFilters()
    {
        return Collections.singletonList( "" );
    }

    public Map getProjectReferences()
    {
        return Collections.singletonMap( "", "" );
    }

    public boolean isExecutionRoot()
    {
        return executionRoot;
    }

    public void setExecutionRoot( final boolean b )
    {
        executionRoot = b;
    }

    public String getDefaultGoal()
    {
        return defaultGoal;
    }

    public Artifact replaceWithActiveArtifact( final Artifact theArtifact )
    {
        return null;
    }
}
