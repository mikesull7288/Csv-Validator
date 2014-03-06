# CSV Validator


## Overview
“Pre Screen” a csv file to check for compatibility with API and current Data Migration tools.

## Process
1. Read in params
2. Read in Data file and template
3. Run Validation
3.a. compare headers
3.b. run rules

## Object Model
- Rule  
-- Header_name  
-- Format (string/date/number)  
-- allowed values/ comparator operation name  
-- tempate, if required (need to see if same header_name is used in multiple templates)  

### SAMPLE

csv_validator dataFile.csv template.csv rules.csv account

*rules.csv*
Header_name, Format, vales/function, template  
Account ID,  String, validateLength(String str), account