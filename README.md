6# cm3110-coursework-o-souter
cm3110-coursework-o-souter created by GitHub Classroom

# App Plan
# need to redo
Oliver Souter 2004076

**Where ISS it? - The app to track the ISS and find asteroids**

# Functionality

## Opening Page
* The App logo
* A Button which will take the user to a page to locate the ISS, 
* Another button which will take the user to another page to find the most recent asteroid that has fallen

## ISS Locator Page
* A graphic of the country the ISS is currently over
* A text display of the (as close to as possible) exact address/location the ISS is over
* A back button to return back to the Opening page

## Meteor Locator Page
* A graphic of the country the most recent meteor has fallen on
* Text display of the (as close to as possible) exact address/location the meteor landed on.
* A back button to return back to the Opening page




# App Design (500 words)

## Home page

<image align="left" src="https://user-images.githubusercontent.com/73543366/205923412-a9fe51cb-d2fd-4ec0-8918-2164cc79f2b2.png"/>

The home page uses the following and layout managers:
* A ImageView to display the App's Logo
* A Simple TextView ...
* A Button to navigate to the ISS Locator page on the user's input  
* A Button to navigate to the Meteor Information page on the user's input
* This page uses a ConstraintLayout layout manager in order to position the widgets in a neat and professional manner
---
<br clear="left">


# ISS Locator page

<image align="left" src="https://user-images.githubusercontent.com/73543366/205923407-6ff0a4cd-f466-413d-80f0-5a1891b5f92a.png"/>

The ISS Locator page uses the following widgets and layout managers:
* A simple TextView (txtISSAbove) with the text, "The ISS is currently above..." in it.
* A TextView (txtCountry) which is used to display the Address and/or Country that the ISS is above
* A TextView (coordinateTextView) which is used to display the ISS's precise coordinates
* A "Reload" button (btnRefresh) which allows the user to reload the data from the API to get the latest coordinates. (user input)
* A TextView (distanceTxtV) which, when location permissions are granted, will display the distance between the user and the ISS using the coordinates above.
* A "How far from me?" button (locationTrackBtn) which the user can use to give location access, and then to load the distance between themselves and the ISS in the TextView above. (user input)
* A "Back" button (btnBackISS) which allows the user to navigate back to the Home page.
* This page uses a ConstraintLayout layout manager in order to position the widgets in a neat and professional manner

---
<br clear="left">

# Meteor information page

<image align="left" src="https://user-images.githubusercontent.com/73543366/205923409-f839e666-ee98-4af1-8c9f-ea5de9b53d9a.png"/>

The Meteor Information Page uses the following and layout managers:
* A Simple TextView (txtMeteorTitle) which displays the text, "Top Meteor Sightings Today"
* A RecyclerView (recycleViewMeteors) which is used to show meteor information to the user
* A Loading bar (meteorLoadingBar) which is used to indicate to the user that the RecyclerView is loading. It hides when the data is loaded.
* A "Back" button (btnBackMeteor) which allows the user to navigate back to the Home page.
* This page uses a ConstraintLayout layout manager in order to position the widgets in a neat and professional manner
---
<br clear="left">

# Navigation
The following graph indicates how a user can navigate through the app. Starting off at the Home page, the user can navigate to either the ISS Locator page (iss_locator_frag) or the Meteor Information page (meteor_locator_frag).
From either of these pages the user can simply navigate back to the home page using the Back button and from there they may access the other page - The ISS Locator and Meteor information pages are not directly linked but can both be accessed through the Home Page
![image](https://user-images.githubusercontent.com/73543366/205931686-588d8952-64fc-4721-9886-ffb8f1491f8a.png)



# WireFrames
63
Below are the first prototype wireframes of the app and its 3 pages to the description above
64
​
65
![image](https://user-images.githubusercontent.com/73543366/198712846-4a850b3e-3573-4e87-b42a-fa2564df473a.png)
66
​
67
# API Use
68
* The App will use *Where The ISS At?*'s public api to gather the coordinates of the ISS when needed 
69
* The app will also use NASA's public NeoWs (Near Earth Object Web Service) API to gather information about meteors
70
* It will also use OpenCage's Free Geocoding API to process coordinates to get a location.
71
​
72
​
# WireFrames
Below are the first prototype wireframes of the app and its 3 pages to the description above

![image](https://user-images.githubusercontent.com/73543366/198712846-4a850b3e-3573-4e87-b42a-fa2564df473a.png)

# API Use
* The App will use *Where The ISS At?*'s public api to gather the coordinates of the ISS when needed 
* The app will also use NASA's public NeoWs (Near Earth Object Web Service) API to gather information about meteors
* It will also use OpenCage's Free Geocoding API to process coordinates to get a location.


