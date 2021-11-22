# Properties #

## Pipeline ##

Name of pipeline runtime

    /myPipelineRuntime/name

Get task status from task in same stage

	$[/javascript  myPipelineRuntime.stages["DEV"].tasks["A Task"].outcome]
	$[/javascript  myStageRuntime.tasks["A Task"].outcome]

Get group status within same stage

	$[/javascript  myPipelineRuntime.stages["DEV"].tasks["Group1"].outcome]
	$[/javascript  myStageRuntime.tasks["Group1"].outcome]

Get task status within a group within same stage

	$[/javascript  myPipelineRuntime.stages["DEV"].tasks["Group1"].tasks["A Task"].outcome]
	$[/javascript  myStageRuntime.tasks["Group1"].tasks["A Task"].outcome]

Get task status from a different pipelineRuntime

    $[/javascript projects["Custom016"].flowRuntimes["Microservices Release Train_pipeline_Microservices Release Train_2_20201214115204"].stages["Integration Test"].tasks["Deploy"].outcome]

Get PipelineRuntime name

    $[/myPipelineRuntime/name]

 Reference the task in a particular stage entry gate (eg for setting correlation id for ServiceNow Create CR)

    /flowRuntime/$[/myPipelineRuntime/id]/stage/Production/gate/PRE/taskName/ServiceNow Approval

Generate link to a pipeline run

https://flow.cloudbees.guru/flow/?s=Flow+Tools&ss=Flow#pipeline-run/$[/myPipeline/id]/$[/myPipelineRuntime/id]'

## Stage ##

Reference result of JIRA Get Issues (multiple property sheets)

    $[/javascript myStageRuntime.getIssuesResult.issues["$[jiraticket]"].status]
    /myStageRuntime/getIssuesResult/issues[$[jiraticket]]/status

Get an element from a JSON result that is stored in an outputParameter from a different step in a stage

    $[/javascript JSON.parse(myPipelineStagetasks["The task Name"].job.outputParameters)["JSON field Path"]]

Access outputParameter called ChangeRequestSysID from a different step, called Create CR, in the same pipeline stage

    $[/myStageRuntime/tasks/Create CR/job/outputParameters/ChangeRequestSysID]


## Job ##

Access outputParameter called restResult from a different step in the current Process

    $[/myJob/steps/Get Route Details/steps/Get a Route in a namespace/outputParameters/restResult]

Access outputParameter of a procedure step from another step in that procedure

    $[/myJob/jobSteps[Call Subproc]/outputParameters/myoutput]
## Procedure ##

 Use parameter (called SourceFileName) defined in Procedure within a procedure step

    $[SourceFileName]

## Snapshot ##

Get name of snapshot being deployed

    /myJob/ec_snapshot-name

## Workspace ##

Workspace unix path

    /myWorkspace/agentUnixPath

## Component ##

Get artifact version from component

    /myComponent/ec_content_details/version

Get EC-Nexus retrievedArtifactVersion version, requires referencing the resource this step is running on

    $[/javascript myJob.retrievedArtifactVersions["$[/myResource/name]"].version]

## JSON ##

Extract single value from JSON
### JSON sample ###

```json
"{
	"project":{
		"projectId":"11a6a613-d878-11e9-8df7-000c297e1ec0",
		"projectName":"My Project",
		"createTime":"2019-09-16T11:49:41.506Z",
		"lastModifiedBy":"admin",
		"modifyTime":"2019-09-16T11:49:41.506Z",
		"owner":"admin",
		"processCount":"0",
		"propertySheetId":"11a6cd25-d878-11e9-8df7-000c297e1ec0",
		"resourceName":"local",
		"stageCount":"0",
		"tracked":"1",
		"workspaceName":"default"
	}
}"
```
### Example - retrieve project.workspaceName ###
```javascript
$[/javascript var obj=JSON.parse('$[/myComponent/ResultVal]'); obj.project.workspaceName]
```

Groovy example:
```groovy
import com.electriccloud.client.groovy.ElectricFlow
import groovy.json.JsonSlurper
ElectricFlow ef = new ElectricFlow()

def jsonSlurper = new JsonSlurper()
def obj = ef.getProperty propertyName: "/myComponent/ResultVal"

def objval = jsonSlurper.parseText(obj.property.value)

println objval

println objval.project.workspaceName
```

## DSL IDE ##

 Get an element from a JSON result that is stored in an outputParameter of a different step, run it in the context of a previously run step

```groovy
expandString jobStepId : "72411ebf-a73f-11ea-afaa-42010a840017", value : '''\
$[/javascript
	JSON.parse(
		myJob.steps["Get Route Details"].steps["Get a Route in a namespace"].outputParameters.restResult
	).spec.to.kind
]
'''
```

Similar to above but from a job in a stage

```groovy
expandString jobId : "job_168573_20200622125324", value : '''\
$[/javascript
	myStageRuntime.tasks["Create CR"].job.outputParameters.ChangeRequestSysID
]
'''
```

expandString to a workflow run (called Workflow), the definition is called WorkflowDefinition

```groovy
expandString workflowName : "workflow_14_20200608104314", projectName: "pollServiceNow", value : '''\
$[/javascript
	myWorkflow.recordID
]
'''
```