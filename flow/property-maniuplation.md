# Flow Property Manipulation #

## Retrieve property ##
### Property named ResultVal stored at the component level ###
$[/javascript myComponent.ResultVal]

### Get an element from a JSON result that is stored in an outputParameter from a different step in a stage ###
$[/javascript JSON.parse(myPipelineStagetasks["The task Name"].job.outputParameters)["JSON field Path"]]

### Access outputParameter called restResult from a different step in the current Process ###
$[/myJob/steps/Get Route Details/steps/Get a Route in a namespace/outputParameters/restResult]

### Use parameter (called SourceFileName) defined in Procedure within a procedure step ###
$[SourceFileName]

### Get PipelineRuntime name ###
$[/myPipelineRuntime/name]


## Extract single value from JSON ##
### JSON sample ###
"{"project":{"projectId":"11a6a613-d878-11e9-8df7-000c297e1ec0","projectName":"My Project","createTime":"2019-09-16T11:49:41.506Z","lastModifiedBy":"admin","modifyTime":"2019-09-16T11:49:41.506Z","owner":"admin","processCount":"0","propertySheetId":"11a6cd25-d878-11e9-8df7-000c297e1ec0","resourceName":"local","stageCount":"0","tracked":"1","workspaceName":"default"}}"

### Example - retrieve project.workspaceName ###
$[/javascript var obj=JSON.parse('$[/myComponent/ResultVal]'); obj.project.workspaceName]

##  Get an element from a JSON result that is stored in an outputParameter of a different step, run it in the context of a previously run step ##
expandString jobStepId : "72411ebf-a73f-11ea-afaa-42010a840017", value : '''\
$[/javascript
JSON.parse(
	myJob.steps["Get Route Details"].steps["Get a Route in a namespace"].outputParameters.restResult
).spec.to.kind
]
'''

## Other ##

### Reference the task in a particular stage entry gate (eg for setting correlation id for ServiceNow Create CR ###)
/flowRuntime/$[/myPipelineRuntime/id]/stage/Production/gate/PRE/taskName/ServiceNow Approval

### Generate link to a pipeline run ###
https://flow.cloudbees.guru/flow/?s=Flow+Tools&ss=Flow#pipeline-run/$[/myPipeline/id]/$[/myPipelineRuntime/id]'
