def resourcelist = getProperties(propertyName: "/myStageRuntime/instances").property.propertyName
println resourcelist
resourcelist.each { resourceitem ->
resource '$[envname]-' + resourceitem, {
  description = ''
  artifactCacheDirectory = ''
  hostName = resourceitem
  hostType = 'CONCURRENT'
  proxyCustomization = '''setSSHKeyFiles(\'/home/cloudbeescd/pcherry-keypair.pub\',\'/home/cloudbeescd/pcherry-keypair.pem\');
setSSHUser(\'ec2-user\');'''
  proxyHostName = 'cbcd-preview-server.c.cloudbees-sa-emea-demo.internal'
  repositoryNames = ''
  resourceDisabled = '0'
  shell = ''
  trusted = '0'
  useSSL = '1'
  workspaceName = 'phils-proxy-ws'
  zoneName = 'default'
}
}

def newlist = []
resourcelist.each { newlist.push("$[envname]-" + it)}
println newlist

environment '$[envname]', {
  environmentEnabled = '1'
  projectName = '$[/myProject/name]'
  reservationRequired = '0'

  environmentTier 'Tier 1', {
    resourceName = newlist
    }
    
  tag 'aws-cf-provisioned'

  // Custom properties
  cfstackname = '$[stackname]'
}

application 'ProxyTestApp', {
  projectName = '$[/myProject/name]'
  tierMap '$[envname]', {
    applicationName = 'ProxyTestApp'
    environmentName = '$[envname]'
    environmentProjectName = '$[/myProject/name]'
    tierMapping '$[envname]-mapping', {
      applicationTierName = 'Tier 1'
      environmentTierName = 'Tier 1'
      tierMapName = '$[envname]'
    }
  }
}