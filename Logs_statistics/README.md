## Statistics from website logs

The project consist of 4 files : LogAnalyzer, WebLogParser, LogEntry, Tester.

LogEmtry represent log as a String with ipAddress, accessTime, request, statusCode and bytesReturned.
WebLogParser parses the log from the specified file as LogEntry.
Tester is used to test the project and it also sets the path to the log file.

LogAnalyzer counts how many unique IP`s in log file, shows the list of uniqueIp on the specified day, with status code in range from X to Y, or just IP`s with status code higher than specified number.
Also possible to count the number of times Ip visited the website, the maximum number of website visits by single IP, IP`s with most website visits, list of unique IP`s that visited the website on specified day.
Shows the day with most IP visits and shows the IP with most visits on the given day.

Default logs file in test-files folder.
