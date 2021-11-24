$[/javascript
    var str = api.getProperty({propertyName: "/myPipeline/testStr"}).property.value;
    var match= str.match(/Value: 'true[^]*/)?true:false;
]