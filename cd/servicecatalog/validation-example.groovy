catalogItem 'Validation Example', {
  description = '''<xml>
  <title>
    Does nothing except validate the input
  </title>

  <htmlData>
    <![CDATA[
      
    ]]>
  </htmlData>
</xml>'''
  allowScheduling = '0'
  buttonLabel = 'Create'
  catalogName = 'Phil Test'
  dslParamForm = ''
  dslString = 'println "Item ran"'
  iconUrl = 'logo-cloudbees.svg'
  projectName = 'Default'
  useFormalParameter = '1'

  formalParameter 'groupName', {
    expansionDeferred = '0'
    label = 'Group Name'
    orderIndex = '1'
    required = '1'
    type = 'entry'
    validationDsl = '''// Get the list of groups
groups = []
getGroups().each {groups.push(it.name)}
groups

// Check if the supplied groupName is in that list
if (groups.any { it == args.parameters[\'groupName\'] }) {
  // an empty or null response is construed as a validation success
  return null
} else {
  return "Group Name supplied is not valid"
}'''
  }
}