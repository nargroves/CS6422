This library is used to parse input files and convert them into usable CSV format for later conversion into MySQL tables.
Each of the possible file types must be in a particular format:
	- Delimited Files - Simply delimited files like CSV or PSV. Nearly any delimiter is accepted.
	- Excel - An excel spreadsheet file. The first row is the headers, while the rest are tuples.
	- XML - An XML data file, where each node beyond the root represents an array of nodes with each node being a tuple (each sub-node being a value)
	- JSON - A JSON file that has an array of arrays where the first array of values is the header and the rest are tuples.
If the input files are not in the correct formatting, the parser execution will halt and close.