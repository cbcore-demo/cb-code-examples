application 'collect-details-from-tier', {
  description = ''
  applicationType = 'traditional'
  projectName = 'Custom022'

  applicationTier 'Tier 1', {
    applicationName = 'collect-details-from-tier'
    component 'Artifact1', pluginName: null, {
      applicationName = 'collect-details-from-tier'
      pluginKey = 'EC-Nexus'
      reference = '0'
      sourceComponentName = null
      sourceProjectName = null

      process 'collect-details', {
        applicationName = null
        exclusiveEnvironment = null
        microserviceName = null
        processType = 'DEPLOY'
        serviceName = null
        smartUndeployEnabled = '0'
        timeLimitUnits = 'minutes'
        workingDirectory = null
        workspaceName = null

        processStep 'Capture IP address', {
          actionLabelText = null
          actualParameter = [
            'commandToRun': '''# Collect IP address
ip=$(hostname -I)

# Display details
echo "IP address is $ip"
echo "Resource name is $[/myResource/name]"

# Add details to propertySheet
ectool setProperty "/myApplication/all_ips_$[/myApplication/jobCounter]/$[/myResource/name]_$ip" "$[/myResource/name]_$ip"''',
            'shellToUse': 'bash',
          ]
          afterLastRetry = null
          allowSkip = null
          alwaysRun = '0'
          applicationTierName = null
          componentRollback = null
          dependencyJoinType = 'and'
          disableFailure = null
          emailConfigName = null
          errorHandling = 'abortJob'
          instruction = null
          notificationEnabled = null
          notificationTemplate = null
          processStepType = 'command'
          retryCount = null
          retryInterval = null
          retryType = null
          rollbackSnapshot = null
          rollbackType = null
          rollbackUndeployProcess = null
          skipRollbackIfUndeployFails = null
          smartRollback = null
          subcomponent = null
          subcomponentApplicationName = null
          subcomponentProcess = null
          submicroservice = null
          submicroserviceProcess = null
          subprocedure = 'RunCommand'
          subproject = '/plugins/EC-Core/project'
          subservice = null
          subserviceProcess = null
          timeLimitUnits = null
          useUtilityResource = '0'
          utilityResourceName = null
          workingDirectory = null
          workspaceName = null
        }
      }

      process 'use-details', {
        applicationName = null
        exclusiveEnvironment = null
        microserviceName = null
        processType = 'DEPLOY'
        serviceName = null
        smartUndeployEnabled = '0'
        timeLimitUnits = 'minutes'
        workingDirectory = null
        workspaceName = null

        processStep 'Display property', {
          actionLabelText = null
          actualParameter = [
            'commandToRun': '''echo "$[/javascript
	var props = ""
	var args = {
  		propertyName: "/myApplication/all_ips_$[/myApplication/jobCounter]"
	}
	var propObject = api.getProperties(args).propertySheet.property;
	for (i = 0; i < propObject.length; i++) {
	  props += propObject[i].propertyName + ", ";
	}
	props.substring(0, props.length-2);
]"''',
            'shellToUse': 'bash',
          ]
          afterLastRetry = null
          allowSkip = null
          alwaysRun = '0'
          applicationTierName = null
          componentRollback = null
          dependencyJoinType = 'and'
          disableFailure = null
          emailConfigName = null
          errorHandling = 'abortJob'
          instruction = null
          notificationEnabled = null
          notificationTemplate = null
          processStepType = 'command'
          retryCount = null
          retryInterval = null
          retryType = null
          rollbackSnapshot = null
          rollbackType = null
          rollbackUndeployProcess = null
          skipRollbackIfUndeployFails = null
          smartRollback = null
          subcomponent = null
          subcomponentApplicationName = null
          subcomponentProcess = null
          submicroservice = null
          submicroserviceProcess = null
          subprocedure = 'RunCommand'
          subproject = '/plugins/EC-Core/project'
          subservice = null
          subserviceProcess = null
          timeLimitUnits = null
          useUtilityResource = '0'
          utilityResourceName = null
          workingDirectory = null
          workspaceName = null
        }
      }

      // Custom properties

      property 'ec_content_details', {

        // Custom properties

        property 'artifactId', value: 'jpetstore', {
          expandable = '1'
          suppressValueTracking = '0'
        }
        classifier = ''
        config = 'nexus.cloudbees.guru'

        property 'destination', value: '', {
          expandable = '1'
          suppressValueTracking = '0'
        }
        extension = 'war'

        property 'groupId', value: 'org.mybatis', {
          expandable = '1'
          suppressValueTracking = '0'
        }
        latestVersion = '0'

        property 'overwrite', value: '1', {
          expandable = '1'
          suppressValueTracking = '0'
        }
        pluginProcedure = 'Retrieve Artifact from Nexus'

        property 'pluginProjectName', value: 'EC-Nexus', {
          expandable = '1'
          suppressValueTracking = '0'
        }
        repoType = 'maven'
        repository = 'shared-demos'
        resultPropertySheet = ''

        property 'version', value: '1.0.0-10', {
          expandable = '1'
          suppressValueTracking = '0'
        }
      }
    }
  }

  process 'Deploy App', {
    applicationName = 'collect-details-from-tier'
    exclusiveEnvironment = '0'
    microserviceName = null
    processType = 'OTHER'
    serviceName = null
    smartUndeployEnabled = null
    timeLimitUnits = 'minutes'
    workingDirectory = null
    workspaceName = null

    formalParameter 'ec_Artifact1-run', defaultValue: '1', {
      checkedValue = null
      expansionDeferred = '1'
      label = null
      orderIndex = null
      required = '0'
      type = 'checkbox'
      uncheckedValue = null
    }

    formalParameter 'ec_Artifact1-version', defaultValue: '$[/projects/Custom022/applications/collect-details-from-tier/components/Artifact1/ec_content_details/version]', {
      expansionDeferred = '1'
      label = null
      orderIndex = null
      required = '0'
      type = 'entry'
    }

    formalParameter 'ec_enforceDependencies', defaultValue: '0', {
      checkedValue = null
      expansionDeferred = '1'
      label = null
      orderIndex = null
      required = '0'
      type = 'checkbox'
      uncheckedValue = null
    }

    formalParameter 'ec_smartDeployOption', defaultValue: '1', {
      checkedValue = null
      expansionDeferred = '1'
      label = null
      orderIndex = null
      required = '0'
      type = 'checkbox'
      uncheckedValue = null
    }

    formalParameter 'ec_stageArtifacts', defaultValue: '0', {
      checkedValue = null
      expansionDeferred = '1'
      label = null
      orderIndex = null
      required = '0'
      type = 'checkbox'
      uncheckedValue = null
    }

    processStep 'tier1-collect-details', {
      actionLabelText = null
      afterLastRetry = null
      allowSkip = null
      alwaysRun = '0'
      applicationTierName = 'Tier 1'
      componentRollback = null
      dependencyJoinType = 'and'
      disableFailure = null
      emailConfigName = null
      errorHandling = 'abortJob'
      instruction = null
      notificationEnabled = null
      notificationTemplate = null
      processStepType = 'process'
      retryCount = null
      retryInterval = null
      retryType = null
      rollbackSnapshot = null
      rollbackType = null
      rollbackUndeployProcess = null
      skipRollbackIfUndeployFails = null
      smartRollback = null
      subcomponent = 'Artifact1'
      subcomponentApplicationName = 'collect-details-from-tier'
      subcomponentProcess = 'collect-details'
      submicroservice = null
      submicroserviceProcess = null
      subprocedure = null
      subproject = null
      subservice = null
      subserviceProcess = null
      timeLimitUnits = null
      useUtilityResource = '0'
      utilityResourceName = null
      workingDirectory = null
      workspaceName = null

      // Custom properties

      property 'ec_deploy', {

        // Custom properties
        ec_notifierStatus = '0'
      }
    }

    processStep 'tier1-use-details', {
      actionLabelText = null
      afterLastRetry = null
      allowSkip = null
      alwaysRun = '0'
      applicationTierName = 'Tier 1'
      componentRollback = null
      dependencyJoinType = 'and'
      disableFailure = null
      emailConfigName = null
      errorHandling = 'abortJob'
      instruction = null
      notificationEnabled = null
      notificationTemplate = null
      processStepType = 'process'
      retryCount = null
      retryInterval = null
      retryType = null
      rollbackSnapshot = null
      rollbackType = null
      rollbackUndeployProcess = null
      skipRollbackIfUndeployFails = null
      smartRollback = null
      subcomponent = 'Artifact1'
      subcomponentApplicationName = 'collect-details-from-tier'
      subcomponentProcess = 'use-details'
      submicroservice = null
      submicroserviceProcess = null
      subprocedure = null
      subproject = null
      subservice = null
      subserviceProcess = null
      timeLimitUnits = null
      useUtilityResource = '0'
      utilityResourceName = null
      workingDirectory = null
      workspaceName = null

      // Custom properties

      property 'ec_deploy', {

        // Custom properties
        ec_notifierStatus = '0'
      }
    }

    processDependency 'tier1-collect-details', targetProcessStepName: 'tier1-use-details', {
      branchCondition = null
      branchConditionName = null
      branchConditionType = null
      branchType = 'ALWAYS'
    }

    // Custom properties

    property 'ec_deploy', {

      // Custom properties
      ec_notifierStatus = '0'
    }
  }

  tierMap '4ae0228a-b18d-11eb-a365-42010a840017', {
    applicationName = 'collect-details-from-tier'
    environmentName = 'Dev'
    environmentProjectName = 'Custom022'
    tierMapping '4afa3ad5-b18d-11eb-84b3-42010a840017', {
      applicationTierName = 'Tier 1'
      environmentTierName = 'Tier 1'
      resourceExpression = null
      tierMapName = '4ae0228a-b18d-11eb-a365-42010a840017'
    }
  }

  // Custom properties

  property 'all_ips_21', {

    // Custom properties

    property 'wildfly-1-Custom022-Dev_10.132.0.46 ', value: 'wildfly-1-Custom022-Dev_10.132.0.46 ', {
      expandable = '1'
      suppressValueTracking = '0'
    }

    property 'wildfly-2-Custom022-Dev_10.132.0.46 ', value: 'wildfly-2-Custom022-Dev_10.132.0.46 ', {
      expandable = '1'
      suppressValueTracking = '0'
    }
  }

  property 'ec_deploy', {

    // Custom properties
    ec_notifierStatus = '0'
  }

  property 'jobCounter', value: '21', {
    expandable = '1'
    suppressValueTracking = '1'
  }
}