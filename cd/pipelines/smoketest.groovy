pipeline 'SmokeTest', {
  description = 'This pipeline runs a series of tests to check the health of this CloudBees CD installation.'
  disableMultipleActiveRuns = '0'
  disableRestart = '0'
  enabled = '1'
  overrideWorkspace = '0'
  projectName = 'Examples Library'
  skipStageMode = 'ENABLED'

  formalParameter 'ec_stagesToRun', {
    expansionDeferred = '1'
    required = '0'
  }

  stage 'Resources', {
    description = ''
    colorCode = '#607d8b'
    completionType = 'auto'
    pipelineName = 'SmokeTest'
    waitForPlannedStartDate = '0'

    gate 'PRE', {
      task 'Default Pool has >1 Resources?', {
        description = ''
        advancedMode = '0'
        allowOutOfOrderRun = '0'
        alwaysRun = '0'
        enabled = '1'
        errorHandling = 'stopOnError'
        gateCondition = '''$[/javascript
	var resources = api.getResourcePool({resourcePoolName: "default"});
	var resourceCount = resources.resourcePool.resourceCount;
	if ( resourceCount > 0) {
		true;
	} else {
		false;
	}
]'''
        gateType = 'PRE'
        insertRollingDeployManualStep = '0'
        resourceName = ''
        skippable = '0'
        subproject = 'Examples Library'
        taskType = 'CONDITIONAL'
        useApproverAcl = '0'
        waitForPlannedStartDate = '0'
      }
    }

    gate 'POST', {
      }

    task 'Run a simple command', {
      description = '''This step test several things:

1. Whether the default resource pool contains an active, reachable resource
2. Whether the current license allows that resource to be used
3. Whether that resource is able to write to it\'s workspace
4. Whether the server can retrieve logs from the agent'''
      actualParameter = [
        'commandToRun': 'echo "Hello World"',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'continueOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }
  }

  stage 'Components', {
    description = ''
    colorCode = '#607d8b'
    completionType = 'auto'
    pipelineName = 'SmokeTest'
    waitForPlannedStartDate = '0'

    gate 'PRE', {
      }

    gate 'POST', {
      }

    task 'Is Analytics available?', {
      description = 'Check whether the Analytics component is available by attempting to retrieve a dashboard.'
      actualParameter = [
        'commandToRun': '''import com.electriccloud.client.groovy.ElectricFlow

ElectricFlow ef = new ElectricFlow()

def result = ef.getDashboard(projectName: "Electric Cloud",
             			   dashboardName: "Application Deployments")

println result''',
        'shellToUse': 'ec-groovy',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'continueOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Is Repo available?', {
      description = 'Check whether the EC-Repository component is available by attempting to retrieve the list of connected repositories and displaying the name of the first one.'
      actualParameter = [
        'commandToRun': '''import com.electriccloud.client.groovy.ElectricFlow
import groovy.json.JsonOutput;

ElectricFlow ef = new ElectricFlow()

def allrepos = ef.getRepositories()
def allreposjson = JsonOutput.toJson(allrepos)
println "==================== All Repos ===================="
def pretty = JsonOutput.prettyPrint(allreposjson)
println pretty
println "========================================"

def firstrepo = allrepos.repository[0].repositoryName
println "First Repo name is " + firstrepo

if(firstrepo  == null) {
	println "No Repos found, throwing error"
	assert false
} else {
  	println "At least 1 repo was found, no error"
  	assert true
}''',
        'shellToUse': 'ec-groovy',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'continueOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }
  }

  stage 'Settings', {
    description = ''
    colorCode = '#607d8b'
    completionType = 'auto'
    pipelineName = 'SmokeTest'
    waitForPlannedStartDate = '0'

    gate 'PRE', {
      }

    gate 'POST', {
      }

    task 'Check webHostName Server Setting', {
      description = 'This steps checks whether the server webHostName setting is different to the hostname.  By default they are the same, but this means any generated links (such as those in emails sent by CD) are not usable.  The webHostName setting should be set to the public DNS name.'
      actualParameter = [
        'commandToRun': '''import com.electriccloud.client.groovy.ElectricFlow

ElectricFlow ef = new ElectricFlow()

def wshost = ef.getProperty(propertyName: "/server/webServerHost").property.value
println "webServerHost value = " + wshost
def hostname = ef.getProperty(propertyName: "/server/hostName").property.value
println "hostName value = " + hostname

if(wshost == hostname) {
	println "\\nwebServerHost and hostName server settings are the same.  This could mean that you have not set the webServerHost value to the public domain name,"
  	println "which means links (such as those in emails sent by CloudBeesCD) would not work.  Consider setting this value in Adminstration->Server Settings.\\n"
  	assert false
}''',
        'shellToUse': 'ec-groovy',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'continueOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }
  }

  stage 'Plugin Configs', {
    description = ''
    colorCode = '#607d8b'
    completionType = 'auto'
    pipelineName = 'SmokeTest'
    waitForPlannedStartDate = '0'

    gate 'PRE', {
      }

    gate 'POST', {
      }

    task 'Set ACL\'s', {
      description = 'This step is not a test, it sets permission for the Examples Library project to be able to access the plugins.  This is needed to allow the next step to attempt to create a plugin config.'
      actualParameter = [
        'commandToRun': '''aclEntry \'user\', principalName: \'project: Examples Library\',
    	objectType: \'server\',
	systemObjectName: \'server\',
        readPrivilege: \'allow\',
        modifyPrivilege: \'allow\',
        executePrivilege: \'allow\',
        changePermissionsPrivilege: \'allow\'
''',
        'shellToUse': 'ectool evalDsl --dslFile',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'continueOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }

    task 'Create Plugin Config', {
      description = 'This step attempts to create a config in the EC-Nexus plugin.'
      actualParameter = [
        'configname': 'smoketest',
        'endpoint': 'https://mynexus.com',
        'password': 'test',
        'pluginname': 'EC-Nexus',
        'userName': 'test',
      ]
      advancedMode = '0'
      allowOutOfOrderRun = '0'
      alwaysRun = '0'
      enabled = '1'
      errorHandling = 'continueOnError'
      insertRollingDeployManualStep = '0'
      resourceName = ''
      skippable = '0'
      subprocedure = 'CreatePluginConfig'
      subproject = 'Examples Library'
      taskType = 'PROCEDURE'
      useApproverAcl = '0'
      waitForPlannedStartDate = '0'
    }
  }

  // Custom properties

  property 'ec_counters', {

    // Custom properties
    pipelineCounter = '19'
  }
}