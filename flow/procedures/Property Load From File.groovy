
procedure 'Property Load From File', {
  description = ''
  jobNameTemplate = 'Property_Load_From_File_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'My Tests'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'filename', defaultValue: '', {
    description = 'Name of the properties file.'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  step 'Set Properties', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''use ElectricCommander;
use ElectricCommander::PropMod;
use ElectricCommander::PropDB;

my $cmdr = new ElectricCommander();

my $file=\"$[filename]\";
open(mf, \"<\", $file) or die(\"Could not open file $file.\");

while (<mf>) {
    if (substr($_,0,1) ne \"#\")
    {
      chomp($_);
      my ($prop, $val) = split /: /, $_;
      print \"Setting property [/myProject\".$prop.\"] to value [\".$val.\"]\";
      $cmdr->setProperty(\"/myProject\".$prop, $val);
    }
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
  ec_parameterForm = '''<editor>
  <formElement>
    <property>filename</property>
    <label>Properties file</label>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
}
