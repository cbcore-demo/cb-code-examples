Date starttime = new Date()
def timeout = 10000
def interval = 5000
def endtime = new Date(starttime.time+timeout)
def currenttime = new Date()

println "Start time " + starttime
println "Timeout ends in " + timeout + " millisecs"
println "Which means end time is " + endtime
println "Checking every " + interval + " millisecs\n\n"

while (currenttime < new Date(starttime.time+timeout))
{
    println "Current time: " + currenttime
    println "Sleeping"
    sleep interval
    currenttime = new Date()
}

println "End time reached"