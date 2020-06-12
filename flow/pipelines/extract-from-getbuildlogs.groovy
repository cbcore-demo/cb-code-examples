pipeline 'Jenkins Example', {
  description = ''
  disableMultipleActiveRuns = '0'
  disableRestart = '0'
  enabled = '1'
  overrideWorkspace = '0'
  pipelineRunNameTemplate = null
  projectName = 'Default'
  releaseName = null
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
    colorCode = '#00adee'
    completionType = 'auto'
    condition = null
    duration = null
    parallelToPrevious = null
    pipelineName = 'Jenkins Example'
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
      task 'Check for 0 test failures', {
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
        gateCondition = '$[/javascript myPipelineRuntime.testsFailures == 0]'
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
        subproject = 'Default'
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

    task 'Get Build Log', {
      description = ''
      actionLabelText = null
      actualParameter = [
        'build_number': '16',
        'config_name': 'core-shared-demos',
        'job_name': 'jpetstore/job/master',
        'result_outpp': '/myPipelineRuntime/buildOutput',
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
      subpluginKey = 'EC-Jenkins'
      subprocedure = 'GetBuildLog'
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

    task 'Extract Test Results', {
      description = ''
      actionLabelText = null
      actualParameter = [
        'commandToRun': '''use ElectricCommander;
use XML::XPath;

my $cmdr = new ElectricCommander();

my $buildLog = $cmdr->getPropertyValue("/myPipelineRuntime/buildOutput");

my ($run, $failures, $errors, $skipped) = $buildLog =~ /Results:.*Tests\\srun:\\s(\\d+),\\sFailures:\\s(\\d+),\\sErrors:\\s(\\d+),\\sSkipped:\\s(\\d)/xms;

print "Run value is [".$run."]\\n";
print "Failures value is [".$failures."]\\n";
print "Errors value is [".$errors."]\\n";
print "Skipped value is [".$skipped."]\\n";

$cmdr->setProperty("/myPipelineRuntime/testsRun", $run);
$cmdr->setProperty("/myPipelineRuntime/testsFailures", $failures);
$cmdr->setProperty("/myPipelineRuntime/testsErrors", $errors);
$cmdr->setProperty("/myPipelineRuntime/testsSkipped", $skipped);''',
        'shellToUse': 'ec-perl',
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
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
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
      taskType = 'COMMAND'
      triggerType = null
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }
  }

  // Custom properties

  property 'ec_counters', {

    // Custom properties
    pipelineCounter = '10'
  }
}