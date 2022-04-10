# progchallenge
MS programming challenge
<li> Dependencies
<br>
Spring Web (for the on-demand text file printer), Spring Data JDBC and H2 Database(1.3.148) for data access layer was exported from spring initalzr. Apache common math3 (3:3.2) was also sourced for functions related to normal distribution. The application was built and tested using spring tool suite 4.9.0.<br>
 
<a>https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.6.6&packaging=jar&jvmVersion=1.8&groupId=com.candidate.dannychung&artifactId=progchallege&name=progchallege&description=Programming%20challenge%20by%20Danny%20Chung&packageName=com.candidate.dannychung.progchallege&dependencies=web,data-jdbc,h2"</a>
 <br><br>The build.gradle file was run by gradled tasks - build dependencies , build and boot run before using Boot Dashboard in STS for test and development and can be found in the root of this git repository.
</li>
<br>
<li>Input and ouput file locations can be specified in application.properties (under src\main\resources) the default settings are :<br>csvinput.path=C:/temp/inputfile.csv and csvoutput.path=C:/temp/outfile.csv </li>
<br>
<li>The example for inputfile.csv and outfile.csv can be found in the root of this git repositor.y</li>
<br>
<li>The inputfile.csv will be the pre-requsite of running the application. It is comma-seperated by Ticker (e.g. 0005.HK) , Type (1=Common Stock,2=Call Option , 3=Put Option), Long Volume and Short Volume (0 must be entered if no long position / short position is held).</li>
 <br>
 <li>schema.sql and dml.sql (under src\main\resources) will be triggered during the startup. 1 table - Stock_and_option_details will be created storing prices and related fixed parameter (e.g. expected return,annualized_sd,etc). 9 securities ('0005.HK','50001.HK','50002.HK','3988.HK','60001.HK','60002.HK','0023.HK','70001.HK','70002.HK') and their correesponding details will be added by the dml queries. If the inputfile contains records of which their tickers are other than these nine, these records' NAV calculations will be ignored.</li>
 <br>
<li>After ApplicationReadyEvent , a change in time event will be triggered randomly between 0.5 to 2 seconds. Total NAV for the whole portfolio will be outputted to the console.</li>
<br>
<li>On-demand detailed Report will be exported by calling the controller "/textfileprinter" Trigger the url request with get in any internet browser i.e. localhost:8080/textfileprinter or curl localhost:8080/textfileprinter in command line tool. </li>
<br>
<li>Pacakge Structure and purposes</li>
<ol>
 <li>com.candidate.dannychung.progchallenge - boot application (start-up of spring application context)</li>
 <li>com.candidate.dannychung.progchallenge.configuration - (mainly for datasource and bean of JDBC template)</li>
 <li>com.candidate.dannychung.progchallenge.constant - the only constant : riskfree interest rate</li>
 <li>com.candidate.dannychung.progchallenge.controller - for triggering on-demand report output with restcontroller</li>
 <li>com.candidate.dannychung.progchallenge.dao and com.candidate.dannychung.progchallenge.dao.impl - data access interface and implementation</li>
 <li>com.candidate.dannychung.progchallenge.event - the class for the change-in-time event</li>
 <li>com.candidate.dannychung.progchallenge.listener - two subscribers to subscribe the object for updated prices</li>
 <li>com.candidate.dannychung.progchallenge.model - POJOs for the program</li>
 <li>com.candidate.dannychung.progchallenge.publisher - to get the update prices and publish events to the listeners / (subscribers)</li>
 <li>com.candidate.dannychung.progchallenge.service and com.candidate.dannychung.progchallenge.service.impl - arithimetic functions for updated prices for the three types of securities</li>
 <li>com.candidate.dannychung.progchallenge.util - File input and output and the implemenation of updated prices before publisher publising events</li>
 </ol>
