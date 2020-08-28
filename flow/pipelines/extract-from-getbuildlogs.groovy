
pipeline 'Ephemeral Jenkins Example', {
  description = ''
  disableMultipleActiveRuns = '0'
  disableRestart = '0'
  enabled = '1'
  overrideWorkspace = '0'
  projectName = 'Default'
  skipStageMode = 'ENABLED'

  formalParameter 'ec_stagesToRun', defaultValue: null, {
    expansionDeferred = '1'
    required = '0'
  }

  stage 'Build', {
    description = ''
    colorCode = '#00adee'
    completionType = 'auto'
    pipelineName = 'Ephemeral Jenkins Example'
    projectName = 'Default'
    waitForPlannedStartDate = '0'

    gate 'PRE', {
      projectName = 'Default'
    }

    gate 'POST', {
      projectName = 'Default'

      task 'Check for 0 test failures', {
        description = ''
        advancedMode = '0'
        allowOutOfOrderRun = '0'
        alwaysRun = '0'
        enabled = '1'
        errorHandling = 'stopOnError'
        gateCondition = '$[/javascript myPipelineRuntime.testsFailures == 0]'
        gateType = 'POST'
        insertRollingDeployManualStep = '0'
        projectName = 'Default'
        resourceName = ''
        skippable = '0'
        subproject = 'Default'
        taskType = 'CONDITIONAL'
        useApproverAcl = '0'
        waitForPlannedStartDate = '0'
      }
    }

    task 'Create Jenkins job', {
      description = ''
      actualParameter = [
        'config': 'CloudBees Core Red Team server',
        'jobName': '$[/myPipelineRuntime/name]',
        'parentPath': '/',
        'xmlDefinition': '''<flow-definition plugin=\"workflow-job@2.39\">
<description/>
<keepDependencies>false</keepDependencies>
<properties/>
<definition class=\"org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition\" plugin=\"workflow-cps@2.80\">
<scm class=\"hudson.plugins.git.GitSCM\" plugin=\"git@4.2.1\">
<configVersion>2</configVersion>
<userRemoteConfigs>
<hudson.plugins.git.UserRemoteConfig>
<url>https://github.com/cloudbees-guru/sonatype-cloudbees-petclinic</url>
<credentialsId>github-cloudbees-guru</credentialsId>
</hudson.plugins.git.UserRemoteConfig>
</userRemoteConfigs>
<branches>
<hudson.plugins.git.BranchSpec>
<name>*/master</name>
</hudson.plugins.git.BranchSpec>
</branches>
<doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>
<submoduleCfg class=\"list\"/>
<extensions/>
</scm>
<scriptPath>Jenkinsfile</scriptPath>
<lightweight>true</lightweight>
</definition>
<triggers/>
<disabled>false</disabled>
</flow-definition>''',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      projectName = 'Default'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Jenkins-Job-CRUD'
      subprocedure = 'Create Job'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Run Jenkins Job', {
      description = ''
      actualParameter = [
        'config_name': 'jpb-test',
        'jenkins_enable_parallel_mode': '0',
        'job_name': '$[/myPipelineRuntime/name]',
        'parameters': '',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      projectName = 'Default'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Jenkins'
      subprocedure = 'RunAndMonitorBuild'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Get Build Log', {
      description = ''
      actualParameter = [
        'build_number': '$[/myPipelineRuntime/tasks/Run Jenkins job/job/outputParameters/jenkinsbuildnumber]',
        'config_name': 'jpb-test',
        'job_name': '$[/myPipelineRuntime/name]',
        'result_outpp': '/myPipelineRuntime/buildOutput',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      projectName = 'Default'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Jenkins'
      subprocedure = 'GetBuildLog'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Extract Test Results', {
      description = ''
      actualParameter = [
        'commandToRun': '''import com.electriccloud.client.groovy.ElectricFlow
import java.util.regex.Pattern

ElectricFlow ef = new ElectricFlow()

def buidLog = ef.getProperty propertyName: \"/myPipelineRuntime/buildOutput\"

// pattern explanation
// ?m means to allow matching across multipe lines
// ie to include beginning (^) and end ($) of the lines
// ?s allows new lines to be matched by . (dot)
// \\d+ any number of numbers
def pattern = /(?ms)Results:.*Tests run: (\\d+), Failures: (\\d+), Errors: (\\d+), Skipped: (\\d+)/
def partial = buidLog =~ pattern

println \"Run value is  [\" + partial[0][1] + \"]\"
println \"Failures value is: [\" + partial[0][2] + \"]\"
println \"Errors value is [\" + partial[0][3] + \"]\"
println \"Skipped value is [\" + partial[0][4] + \"]\"

ef.setProperty propertyName: \"/myPipelineRuntime/testsRun\", value: partial[0][1]
ef.setProperty propertyName: \"/myPipelineRuntime/testsFailures\", value: partial[0][2]
ef.setProperty propertyName: \"/myPipelineRuntime/testsErrors\", value: partial[0][3]
ef.setProperty propertyName: \"/myPipelineRuntime/testsSkipped\", value: partial[0][4]
''',
        'shellToUse': 'ec-groovy',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      projectName = 'Default'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Delete Jenkins job', {
      description = ''
      actualParameter = [
        'config': 'CloudBees Core Red Team server',
        'jobName': '$[/myPipelineRuntime/name]',
        'jobPath': '/',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'stopOnError'
      insertRollingDeployManualStep = '0'
      projectName = 'Default'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Jenkins-Job-CRUD'
      subprocedure = 'Delete Job'
      taskType = 'PLUGIN'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }
  }

  // Custom properties

  property 'ec_counters', {

    // Custom properties
    pipelineCounter = '28'
  }
}
