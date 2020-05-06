
procedure 'Property Load From File', {
  description = ''
  jobNameTemplate = 'Property_Load_From_File_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'My Tests'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  step 'Set Properties', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''use ElectricCommander;
use ElectricCommander::PropMod;
use ElectricCommander::PropDB;

my $cmdr = new ElectricCommander();

my $file=\"/tmp/properties.yaml\";
open(mf, \"<\", $file) or die(\"Could not open file $file.\");

while (<mf>) {   
    chomp($_);
    my ($prop, $val) = split /: /, $_;
    print \"Setting property [\".$prop.\"] to value [\".$val.\"]\";
    $cmdr->setProperty($prop, $val);
}
close($mf);'''
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
