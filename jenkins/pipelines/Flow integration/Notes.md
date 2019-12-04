From: https://github.com/skures/jpetstore/blob/master/Jenkinsfile

The last steps in the Jenkinsfile do:
- Create artefact
- Set parameter in pipeline to snapshot version

To make this work you need to have created a release and added the application with version to the bill of materials.  Then go to DSL and edit the snapshot version to be a parameter.  This is the parameter you set from Jenkinsfile.