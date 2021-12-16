// Get the list of releases
releases = []
getReleases().each {releases.push(it.releaseName)}

// Check if the supplied releaseName is in that list
if (releases.any { it == args.parameters['releaseName'] }) {
  return "A Release with this name already exists"
} else {
  // an empty or null response is construed as a validation success
  return null
}