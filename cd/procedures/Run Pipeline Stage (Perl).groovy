
procedure 'Run Pipeline Stage (Perl)', {
  description = ''
  jobNameTemplate = 'RunPipelineStagePerl_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'My Tests'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  step 'RunPipelineStage', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''use ElectricCommander;
$cmdr = ElectricCommander->new();

$res = $cmdr->runPipeline({ projectName => \"My Tests\",
                    pipelineName => \"Security pipeline\",
                    stagesToRun => \"Prod\" });

print \"Return data from runPipeline:\\n\".$res->findnodes_as_string (\"/\").\"\\n\";

my $flowruntimeid = $res->findvalue(\'//flowRuntime/flowRuntimeId\');

print \"Flowruntimeid is \'$flowruntimeid\'\\n\";

$cmdr->waitForFlowRuntime($flowruntimeid);'''
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = ''
    parallel = '0'
    postProcessor = ''
    precondition = ''
    projectName = 'My Tests'
    releaseMode = 'none'
    resourceName = ''
    shell = 'ec-perl'
    subprocedure = null
    subproject = null
    timeLimit = ''
    timeLimitUnits = 'minutes'
    workingDirectory = ''
    workspaceName = ''
  }
}
