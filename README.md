# Open Oil Demo Program

## Introduction

This program allows the user to enter a collection of data about oil contract data and produce a model of that contract. Configurations of input variables can represent the conditions of the contract, and outputs are deduced from them.

In its current state, the program is a very simple but functional version containing only a subset of the contract elements produced to validate the approach.

## Use

There are currently three main parts to the program.

* Input form
* Main program
* Output file

### Input form

This (form.html) is a simple HTML page which you use to enter the input data. To start, open the page in your favourite browser (ensuring that JavaScript is enabled).

Some data are 'static', that is they have only one value you need to enter, while the rest are entered year-on-year. For the latter, you need to add as many columns as there are years being modelled. Then, enter each yearly value into the table.

When deducing some elements, the program must refer to a set of tranches provided by the user. There are three tables that enable the user to enter these tranches. For each element, add as many rows to the table as there are tranches in that element and fill in the rows.

Once you have entered all your data, press the 'Generate JSON' button. This packages all of your input data together into a JSON object. (JSON is a notation for representing structured data.) You should copy this output and paste it into a new file.

### Main program

The actual modelling is done by the file called OpenOil-Demo.jar. To execute this file, you must have the Java Runtime Environment installed (one place to obtain it is here: <https://www.java.com/en/download/index.jsp>).

To execute the file on your data, run this command in the directory containing the program and your JSON file (assuming it's called my_input.json):

    java -jar OpenOil-Demo.jar my_input.json

### Output file

If everything executes successfully, the program will have produced a set of output data in a newly-created file called 'output.ods'. This is an OpenDocument spreadsheet and you can open it in just about any modern spreadsheet application.


## Hints

At the moment, the program does not have robust parameter checking. Things like missing rows, entering strings where numbers are expected and so on are likely to crash the program.

You're advised *not* to tamper with the generated JSON text (unless you know what you're doing). If a problem occurs or you want to tweak the JSON values, you should as a minimum, make sure that each list of year-on-year values has the same number of elements as there are years, and ensure all required inputs are present.

The required inputs are:
* Year
* Price
* Production
* Capex
* Opex per barrel
* Cost recovery ceiling
