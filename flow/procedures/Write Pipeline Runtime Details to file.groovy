
procedure 'Write Pipeline Runtime Details to file', {
  description = 'Write the Pipeline Runtime Details to flow as xml'
  jobNameTemplate = 'Write_Pipeline_Runtime_Details_To_File_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'Default'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'filename', defaultValue: '', {
    description = ''
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  step 'WritePipelineRunTimeDetailsToFile', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''use ElectricCommander;
use Data::Dumper;

my $ec = new ElectricCommander();

print \"RuntimeId = $[/myPipelineRuntime/id]\";
my $rundetails = $ec->getFlowRuntimeDetails({flowRuntimeId => \"$[/myPipelineRuntime/id]\"});
my $rundetailsxml = $rundetails->findnodes_as_string (\"//flowRuntime\");

open(FH, \'>\', \'$[filename]\') or die $!;
print FH $rundetailsxml;
close(FH);

print \"\\nRuntime Details XML written to $[filename]\";'''
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = ''
    parallel = '0'
    postProcessor = ''
    precondition = ''
    projectName = 'Default'
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

  // Custom properties

  property 'ec_customEditorData', {

    // Custom properties

    property 'parameters', {

      // Custom properties

      property 'filename', {

        // Custom properties
        formType = 'standard'
      }
    }
  }
}
