import com.electriccloud.domain.FormalParameterOptionsResult

def options = new FormalParameterOptionsResult()

// Apply your custom logic to build the list of options to display

groups = []
getGroups().each {
	options.add(/*value*/ it.name, /*displayString*/ it.name)
}

return options