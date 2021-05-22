var props = ""
var args = {
    propertyName: "/myApplication/all_ips_$[/myApplication/jobCounter]"
}
var propObject = api.getProperties(args).propertySheet.property;
for (i = 0; i < propObject.length; i++) {
    props += propObject[i].propertyName + ", ";
}
props.substring(0, props.length-2);