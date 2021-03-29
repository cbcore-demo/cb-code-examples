release 'Triggered by Jenkins', {
  description = ''
  disableMultipleActiveRuns = '0'
  plannedEndDate = '2021-04-12'
  plannedStartDate = '2021-03-29'

  pipeline 'pipeline_Triggered by Jenkins', {
    disableMultipleActiveRuns = '0'
    disableRestart = '0'
    enabled = '1'
    overrideWorkspace = '0'
    skipStageMode = 'ENABLED'

    formalParameter 'ec_stagesToRun', {
      expansionDeferred = '1'
      required = '0'
    }

    stage 'Post Build', {
      description = ''
      colorCode = '#289ce1'
      completionType = 'auto'
      waitForPlannedStartDate = '0'

      gate 'PRE'

      gate 'POST'

      task 'Identify Build', {
        description = ''
        actualParameter = [
          'commandToRun': '''import com.electriccloud.client.groovy.ElectricFlow
ElectricFlow ef = new ElectricFlow()
import java.util.regex.Pattern

def result = ef.getProperty(propertyName: "/myPipelineRuntime/ciBuildDetails").property.value

//println result

def pattern = /(?ms)\\{(.*)#(\\d+)=/
def partial = result =~ pattern

def newname = partial[0][1].replace(" » ", "/job/").trim()

println "Job name is [" + newname+ "]"
println "Build number is  [" + partial[0][2] + "]"

ef.setProperty(propertyName: "/myPipelineRuntime/buildName", value: newname)
ef.setProperty(propertyName: "/myPipelineRuntime/buildNumber", value: partial[0][2])''',
          'shellToUse': 'ec-groovy',
        ]
        advancedMode = '0'
        allowOutOfOrderRun = '0'
        alwaysRun = '0'
        enabled = '1'
        errorHandling = 'stopOnError'
        insertRollingDeployManualStep = '0'
        resourceName = ''
        skippable = '0'
        subpluginKey = 'EC-Core'
        subprocedure = 'RunCommand'
        taskType = 'COMMAND'
        useApproverAcl = '0'
        waitForPlannedStartDate = '0'
      }

      task 'Get Build Details', {
        description = ''
        actualParameter = [
          'build_number': '14',
          'config_name': 'core-shared-demos',
          'job_name': 'cd-examples-library/job/pipeline-triggers-cd-release',
          'result_outpp': '/myJobStep/buildDetails',
        ]
        advancedMode = '0'
        allowOutOfOrderRun = '0'
        alwaysRun = '0'
        enabled = '1'
        errorHandling = 'stopOnError'
        insertRollingDeployManualStep = '0'
        resourceName = ''
        skippable = '0'
        subpluginKey = 'EC-Jenkins'
        subprocedure = 'GetBuildDetails'
        taskType = 'PLUGIN'
        useApproverAcl = '0'
        waitForPlannedStartDate = '0'
      }

      task 'Get Change Log', {
        description = ''
        actualParameter = [
          'build_number': '$[/myPipelineRuntime/buildNumber]',
          'config_name': 'core-shared-demos',
          'job_name': '$[/myPipelineRuntime/buildName]',
          'result_outpp': '/myJobStep/changelog',
        ]
        advancedMode = '0'
        allowOutOfOrderRun = '0'
        alwaysRun = '0'
        enabled = '1'
        errorHandling = 'stopOnError'
        insertRollingDeployManualStep = '0'
        resourceName = ''
        skippable = '0'
        subpluginKey = 'EC-Jenkins'
        subprocedure = 'GetChangeLog'
        taskType = 'PLUGIN'
        useApproverAcl = '0'
        waitForPlannedStartDate = '0'
      }

      task 'Manual', {
        description = ''
        advancedMode = '0'
        allowOutOfOrderRun = '0'
        allowSkip = '0'
        alwaysRun = '0'
        disableFailure = '0'
        enabled = '1'
        errorHandling = 'stopOnError'
        insertRollingDeployManualStep = '0'
        notificationEnabled = '1'
        notificationTemplate = 'ec_default_pipeline_manual_task_notification_template'
        resourceName = ''
        skippable = '0'
        taskType = 'MANUAL'
        useApproverAcl = '0'
        waitForPlannedStartDate = '0'
        approver = [
          'gadmin',
        ]
      }
    }

    // Custom properties

    property 'ec_counters', {

      // Custom properties
      pipelineCounter = '3'
    }
  }
}