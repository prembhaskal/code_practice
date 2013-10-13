GEDCOM Parser Challenge

Chose the xmlparser, since I never wrote a xml parser before.
But since I have worked with java JAXB, so it should help and doing the marshaling part myself should be interesting.

The following are the details of the parser.
>> input -- passed by command line.
   input_file.
   output_file.

>> Data Partition Class:
   Also converts a chunk of data (BufferedReader) having multiple id elements into parts, each having just one id element.

>> the parser is divided into following parts.
  1. RawData Converter:
        Convert the raw data line to a Node, which has XmlElement.
        These objects will represent the tree in the raw form.

  2. Tree Creator:
        This part will take as input, the list of Nodes representing a tree and then link them to each other, thus
        forming a tree structure.
        The tree is a collection of Node, where each node in turn have child nodes.

  3. Post Tree Creation:
        All the nodes having one or more child, value of xml-element should be converted xmlAttributes.

  3. XmlMarshaller:
        The marshaller will convert a given object to its xml representation.
        In this case, it will convert a tree to its xml representation.

>> ERROR HANDLING:
  InvalidDataException: raw data given is not one of the predefined formats.

  TreeException: the TreeElements representing the tree and not proper.

  MarshalException: The Element to String conversion failure due to various reasons like.
   invalid Element, (What can be the other reason for this failure??).

 STOP PROCESSING at error.
 Continue processing, skipping the tree with the error data.
 (parser will try its best to skip the error and come up with a xml representation).
 Max error tree counts:
 (Configurable property)
 IGNORE the blank lines.

 TEST for null names and values.

 CREATING TREE Structure:
 use Stacks to create the tree structure.

 Program should be robust enough to handle changes.
 changes : data format.
           other tree format.


 JAVA DOCS at only at the appropriate places.

 comment as minimal as possible. Code should be self explanatory.

 Bundle the thing in a jar, so that they could easily run it.