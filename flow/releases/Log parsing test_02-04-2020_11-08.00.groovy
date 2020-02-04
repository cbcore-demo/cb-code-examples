release 'Log parsing test', {
  description = ''
  disableMultipleActiveRuns = '0'
  plannedEndDate = '2020-02-03'
  plannedStartDate = '2020-01-20'
  projectName = 'My Tests'
  timeZone = null

  pipeline 'pipeline_Log parsing test', {
    disableMultipleActiveRuns = '0'
    disableRestart = '0'
    enabled = '1'
    overrideWorkspace = '0'
    pipelineRunNameTemplate = null
    releaseName = 'Log parsing test'
    skipStageMode = 'ENABLED'
    templatePipelineName = null
    templatePipelineProjectName = null
    type = null
    workspaceName = null

    formalParameter 'ec_stagesToRun', defaultValue: null, {
      expansionDeferred = '1'
      label = null
      orderIndex = null
      required = '0'
      type = null
    }

    stage 'Stage 1', {
      colorCode = null
      completionType = 'auto'
      condition = null
      duration = null
      parallelToPrevious = null
      pipelineName = 'pipeline_Log parsing test'
      plannedEndDate = null
      plannedStartDate = null
      precondition = null
      resourceName = null
      waitForPlannedStartDate = '0'

      gate 'PRE', {
        condition = null
        precondition = null
        }

      gate 'POST', {
        condition = null
        precondition = null
        task 'Is important val >50', {
          description = ''
          actionLabelText = null
          advancedMode = '0'
          afterLastRetry = null
          allowOutOfOrderRun = '0'
          allowSkip = null
          alwaysRun = '0'
          condition = null
          customLabel = null
          deployerExpression = null
          deployerRunType = null
          disableFailure = null
          duration = null
          emailConfigName = null
          enabled = '1'
          environmentName = null
          environmentProjectName = null
          environmentTemplateName = null
          environmentTemplateProjectName = null
          errorHandling = 'stopOnError'
          gateCondition = '''$[/javascript
myStageRuntime.returnedVal > 50
setProperty("/myTaskRuntime/evidence", "returnedVal of $[/myStageRuntime/returnedVal] is > 50")
true;
]'''
          gateType = 'POST'
          groupName = null
          groupRunType = null
          insertRollingDeployManualStep = '0'
          instruction = null
          notificationEnabled = null
          notificationTemplate = null
          parallelToPrevious = null
          plannedEndDate = null
          plannedStartDate = null
          precondition = null
          requiredApprovalsCount = null
          resourceName = ''
          retryCount = null
          retryInterval = null
          retryType = null
          rollingDeployEnabled = null
          rollingDeployManualStepCondition = null
          skippable = '0'
          snapshotName = null
          stageSummaryParameters = null
          startingStage = null
          subErrorHandling = null
          subapplication = null
          subpipeline = null
          subpluginKey = null
          subprocedure = null
          subprocess = null
          subproject = 'My Tests'
          subrelease = null
          subreleasePipeline = null
          subreleasePipelineProject = null
          subreleaseSuffix = null
          subservice = null
          subworkflowDefinition = null
          subworkflowStartingState = null
          taskProcessType = null
          taskType = 'CONDITIONAL'
          triggerType = null
          useApproverAcl = '0'
          waitForPlannedStartDate = '0'
        }
      }

      task 'Execute Jenkins job', {
        description = ''
        actionLabelText = null
        actualParameter = [
          'config_name': 'core-gke.pcherry.uk',
          'jenkins_enable_parallel_mode': '0',
          'job_name': 'Flow/ParseTest1',
          'parameters': '',
        ]
        advancedMode = '0'
        afterLastRetry = null
        allowOutOfOrderRun = '0'
        allowSkip = null
        alwaysRun = '0'
        condition = null
        customLabel = null
        deployerExpression = null
        deployerRunType = null
        disableFailure = null
        duration = null
        emailConfigName = null
        enabled = '0'
        environmentName = null
        environmentProjectName = null
        environmentTemplateName = null
        environmentTemplateProjectName = null
        errorHandling = 'stopOnError'
        gateCondition = null
        gateType = null
        groupName = null
        groupRunType = null
        insertRollingDeployManualStep = '0'
        instruction = null
        notificationEnabled = null
        notificationTemplate = null
        parallelToPrevious = null
        plannedEndDate = null
        plannedStartDate = null
        precondition = null
        requiredApprovalsCount = null
        resourceName = ''
        retryCount = null
        retryInterval = null
        retryType = null
        rollingDeployEnabled = null
        rollingDeployManualStepCondition = null
        skippable = '0'
        snapshotName = null
        stageSummaryParameters = null
        startingStage = null
        subErrorHandling = null
        subapplication = null
        subpipeline = null
        subpluginKey = 'EC-Jenkins'
        subprocedure = 'RunAndWait'
        subprocess = null
        subproject = null
        subrelease = null
        subreleasePipeline = null
        subreleasePipelineProject = null
        subreleaseSuffix = null
        subservice = null
        subworkflowDefinition = null
        subworkflowStartingState = null
        taskProcessType = null
        taskType = 'PLUGIN'
        triggerType = null
        useApproverAcl = '0'
        waitForPlannedStartDate = '0'
      }

      task 'Extract data from Jenkins', {
        description = ''
        actionLabelText = null
        actualParameter = [
          'build_number': '2',
          'host': 'http://core-gke.pcherry.uk/master-b',
          'job_path': 'Flow/job/ParseTest1',
          'retValProp': '/myStageRuntime/returnedVal',
          'user': '/projects/My Tests/credentials/core-gce.pcherry.uk',
        ]
        advancedMode = '0'
        afterLastRetry = null
        allowOutOfOrderRun = '0'
        allowSkip = null
        alwaysRun = '0'
        condition = null
        customLabel = null
        deployerExpression = null
        deployerRunType = null
        disableFailure = null
        duration = null
        emailConfigName = null
        enabled = '1'
        environmentName = null
        environmentProjectName = null
        environmentTemplateName = null
        environmentTemplateProjectName = null
        errorHandling = 'stopOnError'
        gateCondition = null
        gateType = null
        groupName = null
        groupRunType = null
        insertRollingDeployManualStep = '0'
        instruction = null
        notificationEnabled = null
        notificationTemplate = null
        parallelToPrevious = null
        plannedEndDate = null
        plannedStartDate = null
        precondition = null
        requiredApprovalsCount = null
        resourceName = ''
        retryCount = null
        retryInterval = null
        retryType = null
        rollingDeployEnabled = null
        rollingDeployManualStepCondition = null
        skippable = '0'
        snapshotName = null
        stageSummaryParameters = null
        startingStage = null
        subErrorHandling = null
        subapplication = null
        subpipeline = null
        subpluginKey = null
        subprocedure = 'Parse Jenkins Log'
        subprocess = null
        subproject = 'My Tests'
        subrelease = null
        subreleasePipeline = null
        subreleasePipelineProject = null
        subreleaseSuffix = null
        subservice = null
        subworkflowDefinition = null
        subworkflowStartingState = null
        taskProcessType = null
        taskType = 'PROCEDURE'
        triggerType = null
        useApproverAcl = '0'
        waitForPlannedStartDate = '0'

        attachCredential {
          credentialName = '/projects/My Tests/credentials/core-gce.pcherry.uk'
        }
      }
    }

    stage 'Stage 2', {
      description = ''
      colorCode = '#ff7f0e'
      completionType = 'auto'
      condition = null
      duration = null
      parallelToPrevious = null
      pipelineName = 'pipeline_Log parsing test'
      plannedEndDate = null
      plannedStartDate = null
      precondition = null
      resourceName = null
      waitForPlannedStartDate = '0'

      gate 'PRE', {
        condition = null
        precondition = null
        }

      gate 'POST', {
        condition = null
        precondition = null
        }

      task 'Success', {
        description = ''
        actionLabelText = null
        advancedMode = '0'
        afterLastRetry = null
        allowOutOfOrderRun = '0'
        allowSkip = '0'
        alwaysRun = '0'
        condition = null
        customLabel = null
        deployerExpression = null
        deployerRunType = null
        disableFailure = '0'
        duration = null
        emailConfigName = null
        enabled = '1'
        environmentName = null
        environmentProjectName = null
        environmentTemplateName = null
        environmentTemplateProjectName = null
        errorHandling = 'stopOnError'
        gateCondition = null
        gateType = null
        groupName = null
        groupRunType = null
        insertRollingDeployManualStep = '0'
        instruction = 'We got here!'
        notificationEnabled = '1'
        notificationTemplate = 'ec_default_pipeline_manual_task_notification_template'
        parallelToPrevious = null
        plannedEndDate = null
        plannedStartDate = null
        precondition = null
        requiredApprovalsCount = null
        resourceName = ''
        retryCount = null
        retryInterval = null
        retryType = null
        rollingDeployEnabled = null
        rollingDeployManualStepCondition = null
        skippable = '0'
        snapshotName = null
        stageSummaryParameters = null
        startingStage = null
        subErrorHandling = null
        subapplication = null
        subpipeline = null
        subpluginKey = null
        subprocedure = null
        subprocess = null
        subproject = 'My Tests'
        subrelease = null
        subreleasePipeline = null
        subreleasePipelineProject = null
        subreleaseSuffix = null
        subservice = null
        subworkflowDefinition = null
        subworkflowStartingState = null
        taskProcessType = null
        taskType = 'MANUAL'
        triggerType = null
        useApproverAcl = '0'
        waitForPlannedStartDate = '0'
        approver = [
          'SuperUsers',
        ]
      }
    }

    // Custom properties

    property 'ec_counters', {

      // Custom properties
      pipelineCounter = '19'
    }
  }
}