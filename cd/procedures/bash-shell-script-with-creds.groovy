
procedure 'oc login bash', {
  description = ''
  jobNameTemplate = ''
  projectName = 'Custom012'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  formalParameter 'credentials', defaultValue: '', {
    description = 'Openshift credentials'
    expansionDeferred = '0'
    label = null
    orderIndex = null
    required = '1'
    type = 'credential'
  }

  step 'oc login', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = '''userName=$(ectool getFullCredential \"credentials\" --value userName)
password=$(ectool getFullCredential \"credentials\" --value password)

echo \"Starting\"

set
MY_VAR=\"yes\"
echo $MY_VAR
oc login myhost.bar.com --username $userName --password $password

echo \"Finishing\"
'''
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = ''
    parallel = '0'
    postProcessor = ''
    precondition = ''
    projectName = 'Custom012'
    releaseMode = 'none'
    resourceName = ''
    shell = 'bash'
    subprocedure = null
    subproject = null
    timeLimit = ''
    timeLimitUnits = 'minutes'
    workingDirectory = ''
    workspaceName = ''

    attachParameter {
      formalParameterName = '/projects/Custom012/procedures/oc login bash/formalParameters/credentials'
    }
  }

  // Custom properties

  property 'ec_customEditorData', {

    // Custom properties

    property 'parameters', {

      // Custom properties

      property 'credentials', {

        // Custom properties
        formType = 'standard'
      }

      property 'host', {

        // Custom properties
        formType = 'standard'
      }
    }
  }
}
