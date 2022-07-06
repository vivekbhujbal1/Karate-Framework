Feature: Test

Scenario: Create Testing
Given url 'http://www.webservicex.com/globalweather.asmx?wsdl'
When method Get
Then status 200
Then print(response)

Scenario: Modify Testing
Given url 'http://www.webservicex.com/globalweather.asmx?wsdl'
When method Put
Then status 404
Then print(response)