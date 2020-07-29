## Step condition based on value of property in previous step ##
$[/javascript myStageRuntime.tasks["Run Proc (Fail)"].job.s3bucketexists == "FALSE"]

## Run step 5 times only ##
$[/javascript $[/increment /myPipelineRuntime/count] < 3;]