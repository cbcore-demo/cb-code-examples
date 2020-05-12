import com.cloudbees.hudson.plugins.folder.*;
import jenkins.model.*;
import java.util.logging.Logger;

Logger logger = Logger.getLogger("create-cluster-op.groovy")

def j = Jenkins.instance
def name = "OC from code"

logger.info("creating $name job")
def job = j.getItem(name)
if (job != null) {
  logger.info("job $name already existed so aborting")
  throw new RuntimeException("Job $name already existed so aborting")
}

def configXml = """
<com.cloudbees.opscenter.server.clusterops.ClusterOpProject plugin="operations-center-clusterops@2.222.0.2">
  <description></description>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <scm class="hudson.scm.NullSCM"/>
  <canRoam>true</canRoam>
  <disabled>false</disabled>
  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
  <triggers/>
  <concurrentBuild>true</concurrentBuild>
  <builders/>
  <publishers/>
  <buildWrappers/>
  <operations>
    <com.cloudbees.opscenter.server.clusterops.operations.MasterClusterOperation>
      <itemSource class="com.cloudbees.opscenter.server.clusterops.sources.JenkinsRootItemSource"/>
      <filters class="linked-list"/>
      <clusterOpSteps>
        <com.cloudbees.opscenter.server.clusterops.steps.backup.BackupClusterOpStep>
          <format class="com.infradna.hudson.plugins.backup.format.ZipFormat" plugin="infradna-backup@3.38.18"/>
          <store class="com.infradna.hudson.plugins.backup.store.LocalFileStore" plugin="infradna-backup@3.38.18">
            <dir>/tmp</dir>
          </store>
          <subjects>
            <com.infradna.hudson.plugins.backup.subject.JobConfigurationSubject plugin="infradna-backup@3.38.18"/>
          </subjects>
          <retentionPolicy class="com.infradna.hudson.plugins.backup.retention.NoRetentionPolicy" plugin="infradna-backup@3.38.18"/>
          <safeDelaySeconds>0</safeDelaySeconds>
        </com.cloudbees.opscenter.server.clusterops.steps.backup.BackupClusterOpStep>
      </clusterOpSteps>
      <noRetries>0</noRetries>
      <inParallel>0</inParallel>
      <timeoutSeconds>0</timeoutSeconds>
      <failureMode>IMMEDIATELY</failureMode>
      <failAs>
        <name>FAILURE</name>
        <ordinal>2</ordinal>
        <color>RED</color>
        <completeBuild>true</completeBuild>
      </failAs>
    </com.cloudbees.opscenter.server.clusterops.operations.MasterClusterOperation>
  </operations>
</com.cloudbees.opscenter.server.clusterops.ClusterOpProject>
"""

println "Creating job $name"
def p = j.createProjectFromXML(name, new ByteArrayInputStream(configXml.getBytes("UTF-8")));