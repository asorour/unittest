# sand box for unit testing
I use it for teaching or training on unit testing, I typically
- Remove some tests and ask about missing or needed tests
- Change test names and structure and work with teams on "cleaning" the test code again

I added mutation testing to measure the quality of unit test rather than the typical line coverage. It is an effective teaching tool as well. 

PIT is included in POM, to run mutation test, use command:
  mvn org.pitest:pitest-maven:mutationCoverage

This will output an html report to target/pit-reports/YYYYMMDDHHMI.

Use the withHistory parameter to speed-up repeated analysis of the same codebase:
  mvn -DwithHistory org.pitest:pitest-maven:mutationCoverage

For more about PIT, go to https://pitest.org/quickstart/maven/
