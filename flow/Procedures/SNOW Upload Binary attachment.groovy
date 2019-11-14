
procedure 'SNOW Upload Binary attachment', {
  description = 'Upload binary attachment to ServiceNow'
  jobNameTemplate = 'SNOW_Binary_Upload_Attachment_$[/increment /myproject/jobCounter]_$[/timestamp]'
  projectName = 'Default'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'FileName', defaultValue: '', {
    description = 'Location and name of file to upload.  Location is relative to the agent where this step will run., e.g. /tmp/WordDoc1.docx'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Host', defaultValue: '', {
    description = 'Service now host, e.g. ven01735.service-now.com'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Password', defaultValue: '', {
    description = ''
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'TableName', defaultValue: '', {
    description = 'Table Name to upload attachment to, e.g. change_request'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'TableSysId', defaultValue: '', {
    description = 'Table Sys Id to upload attachment to, e.g. 12037612087361287351823'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  formalParameter 'Username', defaultValue: '', {
    description = ''
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'entry'
  }

  step 'RunBinaryRest', {
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

my $self = LWP::UserAgent->new();
$self->timeout(10);

my $url = \"https://$[Host]/api/now/attachment/upload\";
my $tablesysid = \"$[TableSysId]\";
my $tablename = \"$[TableName]\";
my $fname = \"$[FileName]\";

$self->credentials(
  \'$[Host]:443\',
  \'Service-now\',
  \'$[Username]\' => \'$[Password]\'
);
 
my $req = POST $url, Content_Type => \'multipart/form-data\',
        Content => [
            table_name => $tablename,
            table_sys_id => $tablesysid,
            uploadFile => [ $fname ]
        ];

my $response = $self->request($req);
 
if ($response->is_success()) {
    print \"OK: \", $response->content;
} else {
    print $response->as_string;
}'''
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

      property 'FileName', {

        // Custom properties
        formType = 'standard'
      }

      property 'Host', {

        // Custom properties
        formType = 'standard'
      }

      property 'Password', {

        // Custom properties
        formType = 'standard'
      }

      property 'TableName', {

        // Custom properties
        formType = 'standard'
      }

      property 'TableSysId', {

        // Custom properties
        formType = 'standard'
      }

      property 'username', {

        // Custom properties
        formType = 'standard'
      }
    }
  }
  ec_parameterForm = '''<editor>
  <formElement>
    <property>Host</property>
    <label>Host</label>
    <documentation>Service now host, e.g. ven01735.service-now.com</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>TableName</property>
    <label>TableName</label>
    <documentation>Table Name to upload attachment to, e.g. change_request</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>TableSysId</property>
    <label>TableSysId</label>
    <documentation>Table Sys Id to upload attachment to, e.g. 12037612087361287351823</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>Username</property>
    <label>Username</label>
    <type>entry</type>
    <required>1</required>
  </formElement>

  <formElement>
    <property>Password</property>
    <label>Password</label>
    <type>entry</type>
    <required>1</required>
  </formElement>

    <formElement>
    <property>FileName</property>
    <label>FileName</label>
    <documentation>Location and name of file to upload.  Location is relative to the agent where this step will run., e.g. /tmp/WordDoc1.docx</documentation>
    <type>entry</type>
    <required>1</required>
  </formElement>
</editor>'''
}
