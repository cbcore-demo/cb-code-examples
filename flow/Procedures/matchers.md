# Example matchers #

## Load matcher code ##
Specify the following in 'postprocessor' field in procedure step:

```
postp --loadProperty /myProcedure/matcher
```

## Match string and return rest of line ##
Command: echo -e "Somestring=Blahblahblah\nSomething else"
Matcher:
```
use ElectricCommander;
push (@::gMatchers,
{
   id => "returnedURL",
   pattern => q{Somestring=(.*)},
   action => q{
   setProperty("/myJob/returnedURL", "Matcher $matcher->{id} found the following output\n\n$1");
   }
});
```

Notes
- In this example I deliberately set the property at the job level, so another step could access it.
- If you want to keep it at the job step level, then just do setProperty("returnedURL"...
