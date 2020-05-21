
procedure 'MyPostPTest', {
  description = ''
  jobNameTemplate = ''
  projectName = 'Default'
  resourceName = ''
  timeLimit = ''
  timeLimitUnits = 'minutes'
  workspaceName = ''

  step 'Run Command', {
    description = ''
    alwaysRun = '0'
    broadcast = '0'
    command = 'echo -e \"Somestring=Blahblahblah\\nSomething else\"'
    condition = ''
    errorHandling = 'failProcedure'
    exclusiveMode = 'none'
    logFileName = ''
    parallel = '0'
    postProcessor = 'postp --loadProperty /myProcedure/matcher'
    precondition = ''
    projectName = 'Default'
    releaseMode = 'none'
    resourceName = ''
    shell = 'bash'
    subprocedure = null
    subproject = null
    timeLimit = ''
    timeLimitUnits = 'minutes'
    workingDirectory = ''
    workspaceName = ''
  }

  // Custom properties
  matcher = '''use ElectricCommander;
push (@::gMatchers,
{
   id => \"returnedURL\",
   pattern => q{Somestring=(.*)},
   action => q{
   setProperty(\"/myJob/returnedURL\", \"Matcher $matcher->{id} found the following output\\n\\n$1\");
   }
});
'''
}
