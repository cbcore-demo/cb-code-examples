// Author Phil Cherry
// This groovy code has been written to be run inside a CloudBees CD/RO Procedure
// It requires 4 input parameters for the procedure, as follows:
// version = the version of Ci to check
// envelope = the envelope to check e.g. envelope-core-cm, envelope-core-oc
// groupResults = A true/false flag to decide if plugins are listed in their original order or grouped into their tiers for the output
// pluginsToCheck = the list of plugins to be checked

import groovy.json.JsonSlurper

// Initialise variables

def updateCenterURL = "https://jenkins-updates.cloudbees.com/update-center/$[envelope]/update-center.json?version=$[version]"
def groupResults = "$[groupResults]"
def matchedVerified = []
def matchedCompatible = []
def notMatched = []
def jsonSlurper = new JsonSlurper()

// Download updatecenter.json
def get = new URL(updateCenterURL).openConnection(); 

def postResponseCode = get.getResponseCode();
def postResponseData = get.getInputStream().getText();

if (postResponseCode != 200) {
	println "update-center.json could not be retrieved from URL " + updateCenterURL
    assert false
}

// Extract only the json line from the response
def lines = postResponseData.readLines()
def extractedResponseData = lines[1]

// Convert to Json
def responseAsJson = jsonSlurper.parseText(extractedResponseData)

// Get list of plugins to check and iterate through them
def pluginsToCheck = '''$[pluginsToCheck]'''
def totalPluginsNumber = pluginsToCheck.readLines().size()

pluginsToCheck.readLines().each { plugin ->
	// On each iteration extract the plugin id    
    def pluginId = plugin.split(":")

    // try to match the plugin in the Json
	def matched = responseAsJson.offeredEnvelope.plugins[pluginId[0]]
    
    // if plugin not matched
    if (matched == null)
    {
        // record it
	    notMatched << pluginId[0]
		// if results should not be grouped then display this plugin
        if (groupResults == 'false') {
        	println pluginId[0]
        }
    } else {
        // if plugin is matched
        // if plugin is verified
		if (matched.tier == 'verified') {
            // if version was supplied then check to see if version matches
			if (pluginId.size() > 1 && matched.version != pluginId[1]) {
                // record without version warning
                matchedVerified << matched.artifactId + " (CAP version " + matched.version + " supplied plugin version " + pluginId[1] + ")"
            } else {
                // record with version warning
            	matchedVerified << matched.artifactId
            }
        // if plugin is compatible
	    } else if (matched.tier == 'compatible') {
            // if version was supplied then check to see if version matches
   			if (pluginId.size() > 1 && matched.version != pluginId[1]) {
                // record without version warning
		      	matchedCompatible << matched.artifactId + " (CAP version " + matched.version + " supplied plugin version  " + pluginId[1] + ")"
            } else {
                // record with version warning
		      	matchedCompatible << matched.artifactId
            }
	    }
        // if results should not be grouped then display this plugin
		if (groupResults == 'false') {
            // if version was supplied then check to see if version matches
			if (pluginId.size() > 1 && matched.version != pluginId[1]) {
                // display without version warning
				println matched.artifactId + ' | ' + matched.tier + " (CAP version " + matched.version + " supplied plugin version " + pluginId[1] + ")"
            } else {
                // display with version warning
            	println matched.artifactId + ' | ' + matched.tier
            }
        }
    }
}

// if results should be grouped then display them
if (groupResults == 'true') {
	println "Verified\n--------\n"
	matchedVerified.each { println it }
	
	println "\nCompatible\n----------\n"
	matchedCompatible.each { println it }
	
	println "\nTier 3\n------\n"
	notMatched.each { println it }
}

// Display some statistics
println "\nStatistics\n"
println "Total number of plugins = " + totalPluginsNumber
println "Number of verified plugins = " + matchedVerified.size() + " (" + Math.rint((matchedVerified.size() / totalPluginsNumber)*100) + "%)"
println "Number of compatible plugins = " + matchedCompatible.size() + " (" + Math.rint((matchedCompatible.size() / totalPluginsNumber)*100) + "%)"
println "Number of Tier 3 plugins = " + notMatched.size() + " (" + Math.rint((notMatched.size() / totalPluginsNumber)*100) + "%)"
