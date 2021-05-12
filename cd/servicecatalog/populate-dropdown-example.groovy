catalogItem 'Populated DropDown Example', {
  description = '''<xml>
  <title>
    Does nothing but shows how to populate Parameter dropdown using DSL.
  </title>

  <htmlData>
    <![CDATA[
      
    ]]>
  </htmlData>
</xml>'''
  allowScheduling = '0'
  buttonLabel = 'Create'
  catalogName = 'Phil Test'
  dslString = 'println "Does nothing"'
  iconUrl = 'logo-cloudbees.svg'
  projectName = 'Default'
  useFormalParameter = '1'

  formalParameter 'groupName', {
    expansionDeferred = '0'
    label = 'Group Name'
    optionsDsl = '''import com.electriccloud.domain.FormalParameterOptionsResult

def options = new FormalParameterOptionsResult()

// Apply your custom logic to build the list of options to display

groups = []
getGroups().each {
	options.add(/*value*/ it.name, /*displayString*/ it.name)
}

return options'''
    orderIndex = '1'
    required = '1'
    type = 'select'
  }
}