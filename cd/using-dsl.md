# Using DSL #

Return values from DSL procedures behave different from groovy.  In groovy you would be able to do something like:

    def result = ef.getGroups()
    println result

and you would see the response string.

But with DSL (using the DSLIDE), you don't see any value.  Firstly, because it is placed inside another element.  The element name is typically based on the procedure so in the case of getGroups, the result would be in a 'groups' element.

    def result = getGroups().groups
    println result

But that still doesn't show anything.  Instead you can write it like this:

    groups = []
    getGroups().each {groups.push(it)}
    groups

To capture all the output, or to just capture the name of each result (not the it.name refers to the collection and not any of the values in the result):

    groups = []
    getGroups().each {groups.push(it.name)}
    groups

The following also works:

    def result = getGroups().groups
    println result
    result.each { Result ->
    	println Result.groupName
    }

If you want to look for a specific match in the result (e.g. for a parameter validation) then the following works:

    groups = []
    getGroups().each {groups.push(it.name)}
    groups
    if (groups.any { it == "greadonly" }) {
    println "Yes"
    } else {
    println "No"
    }

As does this:

    if ( getGroups().groups.name.any { it == "greadonly"  } ) {
    println "Yes"
    } else {
    println "No"
    }
