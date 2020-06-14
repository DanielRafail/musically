# Musically

Musically Store is an e-commerce web site for the purpose of selling music to consumers. The consumer can search for music, browse for music, give their opinions and reviews on the music, and purchase music using the shopping cart pattern.

## Optional features:

1. Alphabetical filtering of selection
2. See more for selection (dont show all the albums and tracks one shot)

## Bugs

1. I (Daniel) have realized that changing the color of a button when it is in focus or active only changes its color when the page is in focus (compared to if it is in the background). A remedy to this would be to add in javascript the button to a class with a color of x when it is clicked or hovered or active or focused but that is a very low priority thing as there are more urgent features to work on


## To Run the Project

1. Make sure your Sql server is started
2. Run MusicallyDBTablesCreation.sql script from OtherTestSource Folder
3. Build the project (It should pass all test)
4. Run data.sql script from OtherTestSource Folder
5. Run the project!
6. Optional: remove @Ignore from selenium test and Run the test (It is required for a deployed project with test data)
7. Enjoy our best WebSite =)

** run MusicallyDBTablesCreation.sql script before every build