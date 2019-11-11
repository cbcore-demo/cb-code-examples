# Flow Property Manipulation #

## Retrieve property ##
### Property named ResultVal stored at the component level ###
$[/javascript myComponent.ResultVal]

## Use parameter (called SourceFileName) defined in Procedure within a procedure step ##
$[SourceFileName]

## Extract single value from JSON ##
### JSON sample ###
"{"project":{"projectId":"11a6a613-d878-11e9-8df7-000c297e1ec0","projectName":"My Project","createTime":"2019-09-16T11:49:41.506Z","lastModifiedBy":"admin","modifyTime":"2019-09-16T11:49:41.506Z","owner":"admin","processCount":"0","propertySheetId":"11a6cd25-d878-11e9-8df7-000c297e1ec0","resourceName":"local","stageCount":"0","tracked":"1","workspaceName":"default"}}"

### Example - retrieve project.workspaceName ###
$[/javascript var obj=JSON.parse('$[/myComponent/ResultVal]'); obj.project.workspaceName]
