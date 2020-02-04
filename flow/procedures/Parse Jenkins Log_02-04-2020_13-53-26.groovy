
procedure 'Parse Jenkins Log', {
  description = ''
  jobNameTemplate = 'Parse_Jenkins_Log_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'My Tests'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'build_number', defaultValue: '', {
    description = 'Build number to parse the log of.'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'host', defaultValue: '', {
    description = 'Jenkins host (e.g. http://core-gke.pcherry.uk/master-b)'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'job_path', defaultValue: '', {
    description = 'Job path e.g. MyJob1 or if in folder, Folder1/job/MyJob1'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'retValProp', defaultValue: '', {
    description = 'Property to store returned value'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'user', defaultValue: '', {
    description = 'Credentials to access Jenkins'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'credential'
  }

  step 'ParseJenkinsLog', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''# -------------------------------------------------------------------------
# Includes
# -------------------------------------------------------------------------
use strict;
use warnings;
use ElectricCommander;
use ElectricCommander::PropMod;
use LWP::UserAgent;
use HTTP::Request::Common;
use ElectricCommander::PropDB;

my $ec = new ElectricCommander();
my $username = $ec->getFullCredential(\"user\", {value => \"userName\"})->findnodes(\"//userName\")->string_value();
my $password = $ec->getFullCredential(\"user\", {value => \"password\"})->findnodes(\"//password\")->string_value();
my $myurl = \"$[host]/job/$[job_path]/$[build_number]/consoleText\";

my $browser = LWP::UserAgent->new();
my $request = HTTP::Request->new;

$browser->timeout(10);
$request->authorization_basic($username, $password);
$request->method(\"GET\");
$request->url($myurl);
 
my $response = $browser->request($request);

die \"Request URL: $myurl\\nError: \", $response->status_line
   unless $response->is_success;

print $response->as_string;'''
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = ''
    parallel = '0'
    postProcessor = 'postp --loadProperty /myProcedure/postp_matchers'
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

    attachParameter {
      formalParameterName = '/projects/My Tests/procedures/Parse Jenkins Log/formalParameters/user'
    }
  }

  // Custom properties

  property 'ec_customEditorData', {

    // Custom properties

    property 'parameters', {

      // Custom properties

      property 'build_number', {

        // Custom properties
        formType = 'standard'
      }

      property 'config_name', {

        // Custom properties
        formType = 'standard'
      }

      property 'host', {

        // Custom properties
        formType = 'standard'
      }

      property 'job_name', {

        // Custom properties
        formType = 'standard'
      }

      property 'job_path', {

        // Custom properties
        formType = 'standard'
      }

      property 'password', {

        // Custom properties
        formType = 'standard'
      }

      property 'result_outpp', {

        // Custom properties
        formType = 'standard'
      }

      property 'retValProp', {

        // Custom properties
        formType = 'standard'
      }

      property 'user', {

        // Custom properties
        formType = 'standard'
      }
    }
  }
  ec_ParameterForm = '''<editor>
  <formElement>
    <property>host</property>
    <label>Jenkins Host</label>
    <documentation>Jenkins host (e.g. http://core-gke.pcherry.uk/master-b)</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>job_path</property>
    <label>Job Path</label>
    <documentation>Job path e.g. MyJob1 or if in folder, Folder1/job/MyJob1</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>build_number</property>
    <label>Build Number</label>
    <documentation>Build number to parse the log of.</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

    <formElement>
    <property>user</property>
    <label>Jenkins Credentials</label>
    <documentation>Credentials to access Jenkins</documentation>
    <type>credential</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>retValProp</property>
    <label>returnedVal Property</label>
    <documentation>Property to store returnedVal</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
  postp_matchers = '''use ElectricCommander;
push (@::gMatchers,
{
   id => \"returnedVal\",
   pattern => q{important: (.*)},
   action => q{
   setProperty(\"/myJob/returnedVal\", \"$1\");
   setProperty(\"$[retValProp]\", \"$1\");
   setProperty(\"/myStageRuntime/ec_summary/returnedVal\", \"$1\");
   }
});'''
}
