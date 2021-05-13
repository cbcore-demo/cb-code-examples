// These snippets are for the file which has the same name as the plugin and ends in groovy externsion
// e.g. For EC-AnsibleTower plugin, look for /dsl/properties/groovy/lib/AnsibleTower.groovy
// The snippets are typically used within each of the procedure functions.

// Store result into an outputParameter
// Store an id value from the result (which is JSON) into an outputParameter
// Store a clickable link to the external tool to access the launched job, this uses the endpoint value from the plugin config
    Config configValues = context.getConfigValues()
    def ep = configValues.getParameter('endpoint').value
    if (!ep.endsWith('/')) { ep = ep + "/" }

    // set OutputParameters
    sr.setOutputParameter('result', response.toString())
    sr.setOutputParameter('id', response.id.toString())
    sr.setOutputParameter('link', '<html><a href="' + ep + '#/jobs/playbook/' + response.id.toString() + '" target="_blank">Ansible Launched Job ' + response.id.toString() +'</a></html>')
    sr.apply()

