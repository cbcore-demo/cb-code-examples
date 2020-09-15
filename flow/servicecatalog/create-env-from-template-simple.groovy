catalog 'Phil Test', {
  iconUrl = null
  projectName = 'Default'

  catalogItem 'Create Env from Template', {
    description = '''<xml>
  <title>
    Create Environment from a template.
  </title>

  <htmlData>
    <![CDATA[
      Create Environment from Template
    ]]>
  </htmlData>
</xml>'''
    allowScheduling = '0'
    buttonLabel = 'Create'
    catalogName = 'Phil Test'
    dslParamForm = '''{
  "sections": {
    "section": [{
      "name": "Environment details",
      "instruction": "Provide Environment details",
      "ec_parameterForm": "<editor><formElement><label>Environment Name</label> <property>envName</property> <documentation>Name of the Environment to be created.</documentation> <type>entry</type> <required>1</required></formElement>  <formElement><label>Project Name</label> <property>projectName</property> <documentation>Name of the project to create the Environment in.</documentation> <type>project</type> <required>1</required></formElement></editor>"
    }],

    "endTarget": {
      "source": "form",
      "object": "environment",
      "objectName": "envName",
      "objectProjectName": "projectName"
    }
  }
}'''
    dslString = '''environment args.envName, {
  environmentEnabled = \'1\'
  projectName = args.projectName
  reservationRequired = \'0\'

  environmentTier \'App Tier\', {
    resourceName = [
    ]
  }
}'''
    endTargetJson = null
    iconUrl = 'logo-cloudbees.svg'
    subpluginKey = null
    subprocedure = null
    subproject = null
    useFormalParameter = '0'
  }
}