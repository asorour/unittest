# sand box for unit testing
unit testing

PIT is included in POM, to run mutation test, use command:
  mvn org.pitest:pitest-maven:mutationCoverage
  
This will output an html report to target/pit-reports/YYYYMMDDHHMI.

Use the withHistory parameter to speed-up repeated analysis of the same codebase:
  mvn -DwithHistory org.pitest:pitest-maven:mutationCoverage
  
For more about PIT, go to https://pitest.org/quickstart/maven/
