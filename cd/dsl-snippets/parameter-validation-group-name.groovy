// Get the list of groups
groups = []
getGroups().each {groups.push(it.name)}
groups

// Check if the supplied groupName is in that list
if (groups.any { it == args.parameters['groupName'] }) {
  return "Group Name supplied is not valid"
} else {
  // an empty or null response is construed as a validation success
  return null
}