
procedure 'Get Flow Environments', {
  description = ''
  jobNameTemplate = 'Get_Flow_Environments_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'Custom003'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'Configuration', defaultValue: '', {
    description = 'EC-Rest configuration that defines the Flow endpoint (e.g. https://localhost/rest/v1.0)'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'ProjectName', defaultValue: '', {
    description = 'Name of the project to retrieve Environments from'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'retValProp', defaultValue: '', {
    description = 'Property to store returned value (e.g. /myJob/env_list)'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  step 'Call Flow REST API', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = null
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = null
    parallel = '0'
    postProcessor = null
    precondition = ''
    projectName = 'Custom003'
    releaseMode = 'none'
    resourceName = ''
    shell = null
    subprocedure = 'runRest'
    subproject = '/plugins/EC-Rest/project'
    timeLimit = ''
    timeLimitUnits = 'minutes'
    workingDirectory = null
    workspaceName = ''
    actualParameter 'connection_config', '$[Configuration]'
    actualParameter 'contentType', ''
    actualParameter 'filePath', ''
    actualParameter 'formContent', ''
    actualParameter 'headers', ''
    actualParameter 'pathUrl', 'projects/$[ProjectName]/environments'
    actualParameter 'requestType', 'GET'
    actualParameter 'response_outpp', '/myJob/envs_json'
  }

  step 'Create Simple Env List', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''use JSON;
use ElectricCommander;
use ElectricCommander::PropMod;
use ElectricCommander::PropDB;
use Data::Dumper;

my $cmdr = new ElectricCommander();
my $json = $cmdr->getProperty(\"/myJob/envs_json\")->findvalue(\'//value\');
my $decode_json = decode_json ($json->value());

print Dumper($decode_json);

print \"\\n\\nEnvironment List:\\n\\n\";

$cmdr->createProperty(\'$[retValProp]\', {propertyType => sheet});

for my $envnames (@{ $decode_json->{environment} }){
    my $thisenvname = $envnames->{environmentName};
    print \"Environment Name: \".$thisenvname.\"\\n\";
    my $propertyname = \"$[retValProp]/\".$thisenvname;
    print \"Adding property: \".$propertyname.\"\\n\";
    $cmdr->createProperty($propertyname);
} '''
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = ''
    parallel = '0'
    postProcessor = ''
    precondition = ''
    projectName = 'Custom003'
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

      property 'Configuration', {

        // Custom properties
        formType = 'standard'
      }

      property 'Project', {

        // Custom properties
        formType = 'standard'
      }

      property 'ProjectName', {

        // Custom properties
        formType = 'standard'
      }

      property 'retValProp', {

        // Custom properties
        formType = 'standard'
      }
    }
  }
  ec_parameterForm = '''<editor>
  <formElement>
    <property>Configuration</property>
    <label>Configuration</label>
    <documentation>Select which configuration to use from the EC-Rest plugin</documentation>
    <propertyReference>/plugins/EC-Rest/project/rest_cfgs</propertyReference>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>ProjectName</property>
    <label>ProjectName</label>
    <documentation>Name of the project to retrieve Environments from</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>retValProp</property>
    <label>retValProp</label>
    <documentation>Property to store returned value (e.g. /myJob/env_list)</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
}
